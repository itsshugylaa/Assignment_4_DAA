package app;

import graph.common.Edge;
import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import graph.dagsp.DAGShortestPath;
import io.GraphIO;
import metrics.Metrics;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "data/tasks.json";
        if (args.length > 0) path = args[0];
        System.out.println("=== Assignment_4_DAA ===");

        var data = GraphIO.load(path);
        var adj = GraphIO.unweighted(data);

        Metrics mScc = new Metrics().start();
        var scc = new TarjanSCC(adj);
        mScc.stop();
        System.out.printf("SCCs: %d | dfsCalls=%d | edgeVisits=%d | time=%.3fms%n",
                scc.sccs.size(), scc.dfsCalls, scc.edgeVisits, mScc.ms());

        int[] comp = scc.compIds();
        int k = scc.sccs.size();
        List<Set<Integer>> dag = new ArrayList<>();
        for (int i = 0; i < k; i++) dag.add(new HashSet<>());
        for (var e : data.edges)
            if (comp[e.u] != comp[e.v]) dag.get(comp[e.u]).add(comp[e.v]);

        Metrics mTopo = new Metrics().start();
        var topoRes = TopologicalSort.kahnWithCounts(dag);
        mTopo.stop();
        System.out.printf("Topo order: %s | pushes=%d | pops=%d | time=%.3fms%n",
                topoRes.order, topoRes.pushes, topoRes.pops, mTopo.ms());

        List<Integer> derived = new ArrayList<>();
        for (int c : topoRes.order) {
            List<Integer> nodes = new ArrayList<>(scc.sccs.get(c));
            Collections.sort(nodes);
            derived.addAll(nodes);
        }
        System.out.println("Derived order of original tasks: " + derived);

        var adjW = GraphIO.weightedCondensed(data, comp, k);
        int srcComp = comp[data.source];
        var sp = DAGShortestPath.shortest(adjW, topoRes.order, srcComp);
        var lp = DAGShortestPath.longest(adjW, topoRes.order, srcComp);

        System.out.printf("Shortest relaxations=%d | Longest relaxations=%d%n", sp.relaxations, lp.relaxations);
        System.out.println("Shortest distances: " + Arrays.toString(sp.dist));

        double max = Double.NEGATIVE_INFINITY; int target = -1;
        for (int i = 0; i < lp.dist.length; i++)
            if (lp.dist[i] > max) { max = lp.dist[i]; target = i; }
        System.out.printf("Critical path length: %.2f (target comp=%d)%n", max, target);

        var pathComp = DAGShortestPath.reconstruct(target, lp.parent);
        System.out.println("Critical component path: " + pathComp);
        List<Integer> expanded = new ArrayList<>();
        for (int c : pathComp) expanded.add(scc.sccs.get(c).get(0));
        System.out.println("Expanded original node path: " + expanded);

        System.out.println("=== Done ===");
    }
}
