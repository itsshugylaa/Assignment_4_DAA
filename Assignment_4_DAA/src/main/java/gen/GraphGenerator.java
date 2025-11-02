package gen;
import io.GraphIO;
import java.io.IOException;
import java.util.*;

public class GraphGenerator {
    private final Random rnd=new Random(42);

    public GraphIO.TaskData gen(int n,double density,boolean cyclic){
        GraphIO.TaskData t=new GraphIO.TaskData();
        t.directed=true;t.n=n;t.edges=new ArrayList<>();t.source=0;t.weight_model="edge";
        for(int u=0;u<n;u++)for(int v=0;v<n;v++)if(u!=v&&rnd.nextDouble()<density){
            var e=new GraphIO.EdgeJson(); e.u=u;e.v=v;e.w=1+rnd.nextInt(9);t.edges.add(e);
        }
        if(cyclic&&n>=3){
            var a=new GraphIO.EdgeJson();a.u=0;a.v=1;a.w=1;
            var b=new GraphIO.EdgeJson();b.u=1;b.v=2;b.w=1;
            var c=new GraphIO.EdgeJson();c.u=2;c.v=0;c.w=1;
            t.edges.addAll(List.of(a,b,c));
        }
        return t;
    }

    public void genAll(String folder)throws IOException{
        int[] small={6,8,10},med={12,16,18},large={22,30,40};int i=1;
        for(int s:small) GraphIO.saveJson(folder+"/small_"+i+++".json",gen(s,0.12,true));
        for(int s:med) GraphIO.saveJson(folder+"/medium_"+i+++".json",gen(s,0.18,true));
        for(int s:large) GraphIO.saveJson(folder+"/large_"+i+++".json",gen(s,0.08,false));
    }
}
