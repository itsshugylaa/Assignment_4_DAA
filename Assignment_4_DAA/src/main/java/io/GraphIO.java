package io;
import com.google.gson.*;
import graph.common.Edge;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GraphIO {
    public static class EdgeJson { public int u,v; public double w; }
    public static class TaskData {
        public boolean directed; public int n; public List<EdgeJson> edges;
        public int source; public String weight_model;
    }

    public static TaskData load(String path)throws IOException{
        try(Reader r=new InputStreamReader(new FileInputStream(path),StandardCharsets.UTF_8)){
            return new Gson().fromJson(r,TaskData.class);
        }
    }

    public static void saveJson(String path,Object obj)throws IOException{
        try(Writer w=new OutputStreamWriter(new FileOutputStream(path),StandardCharsets.UTF_8)){
            new GsonBuilder().setPrettyPrinting().create().toJson(obj,w);
        }
    }

    public static List<List<Integer>> unweighted(TaskData t){
        List<List<Integer>> g=new ArrayList<>();
        for(int i=0;i<t.n;i++) g.add(new ArrayList<>());
        for(var e:t.edges) g.get(e.u).add(e.v);
        return g;
    }

    public static List<List<Edge>> weightedCondensed(TaskData t,int[] comp,int k){
        List<Map<Integer,Double>> tmp=new ArrayList<>();
        for(int i=0;i<k;i++) tmp.add(new HashMap<>());
        for(var e:t.edges){
            int a=comp[e.u],b=comp[e.v];
            if(a==b)continue;
            tmp.get(a).merge(b,e.w,Math::min);
        }
        List<List<Edge>> res=new ArrayList<>();
        for(int i=0;i<k;i++){
            List<Edge> list=new ArrayList<>();
            for(var en:tmp.get(i).entrySet())list.add(new Edge(en.getKey(),en.getValue()));
            res.add(list);
        }
        return res;
    }
}
