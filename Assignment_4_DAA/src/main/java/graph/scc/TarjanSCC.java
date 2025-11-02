package graph.scc;
import java.util.*;

public class TarjanSCC {
    public final List<List<Integer>> sccs = new ArrayList<>();
    public long dfsCalls = 0, edgeVisits = 0;

    private final List<List<Integer>> g;
    private final int[] id, low; private final boolean[] st;
    private final Deque<Integer> stack = new ArrayDeque<>();
    private int time = 0;

    public TarjanSCC(List<List<Integer>> g) {
        this.g = g; int n = g.size();
        id = new int[n]; low = new int[n]; st = new boolean[n];
        Arrays.fill(id, -1);
        for (int i = 0; i < n; i++) if (id[i] == -1) dfs(i);
    }

    private void dfs(int v) {
        dfsCalls++;
        id[v] = low[v] = time++;
        stack.push(v); st[v] = true;
        for (int to : g.get(v)) {
            edgeVisits++;
            if (id[to] == -1) dfs(to);
            if (st[to]) low[v] = Math.min(low[v], low[to]);
        }
        if (low[v] == id[v]) {
            List<Integer> comp = new ArrayList<>();
            int x; do { x = stack.pop(); st[x] = false; comp.add(x); } while (x != v);
            sccs.add(comp);
        }
    }

    public int[] compIds() {
        int[] cid = new int[g.size()];
        for (int i = 0; i < sccs.size(); i++) for (int v : sccs.get(i)) cid[v] = i;
        return cid;
    }
}
