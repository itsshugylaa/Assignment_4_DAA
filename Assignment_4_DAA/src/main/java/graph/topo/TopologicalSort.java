package graph.topo;
import java.util.*;

public class TopologicalSort {
    public static class Result {
        public final List<Integer> order; public final int pushes, pops;
        public Result(List<Integer> o, int psh, int pp){ order=o; pushes=psh; pops=pp; }
    }

    public static Result kahnWithCounts(List<Set<Integer>> g){
        int n=g.size(); int[] indeg=new int[n];
        for(int i=0;i<n;i++) for(int v:g.get(i)) indeg[v]++;
        Deque<Integer> q=new ArrayDeque<>();
        int push=0,pop=0;
        for(int i=0;i<n;i++) if(indeg[i]==0){ q.add(i); push++; }
        List<Integer> order=new ArrayList<>();
        while(!q.isEmpty()){
            int u=q.poll(); pop++; order.add(u);
            for(int v:g.get(u)) if(--indeg[v]==0){ q.add(v); push++; }
        }
        return new Result(order,push,pop);
    }
}
