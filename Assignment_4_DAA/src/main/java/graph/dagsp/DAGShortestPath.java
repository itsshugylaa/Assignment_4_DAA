package graph.dagsp;
import graph.common.Edge;
import java.util.*;

public class DAGShortestPath {
    public static class Result {
        public final double[] dist; public final int[] parent; public final long relaxations;
        public Result(double[] d,int[] p,long r){ dist=d; parent=p; relaxations=r;}
    }

    public static Result shortest(List<List<Edge>> g,List<Integer> topo,int s){
        int n=g.size(); double[] d=new double[n]; int[] p=new int[n];
        Arrays.fill(d,Double.POSITIVE_INFINITY); Arrays.fill(p,-1); d[s]=0; long relax=0;
        for(int u:topo){
            if(Double.isInfinite(d[u])) continue;
            for(Edge e:g.get(u)){
                if(d[u]+e.w<d[e.to]){d[e.to]=d[u]+e.w;p[e.to]=u;relax++;}
            }
        }
        return new Result(d,p,relax);
    }

    public static Result longest(List<List<Edge>> g,List<Integer> topo,int s){
        int n=g.size(); double[] d=new double[n]; int[] p=new int[n];
        Arrays.fill(d,Double.NEGATIVE_INFINITY); Arrays.fill(p,-1); d[s]=0; long relax=0;
        for(int u:topo){
            if(d[u]==Double.NEGATIVE_INFINITY) continue;
            for(Edge e:g.get(u)){
                if(d[u]+e.w>d[e.to]){d[e.to]=d[u]+e.w;p[e.to]=u;relax++;}
            }
        }
        return new Result(d,p,relax);
    }

    public static List<Integer> reconstruct(int t,int[] parent){
        if(t<0)return List.of();
        LinkedList<Integer> path=new LinkedList<>();
        for(int cur=t;cur!=-1;cur=parent[cur])path.addFirst(cur);
        return path;
    }
}
