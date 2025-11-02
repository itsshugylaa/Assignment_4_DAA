package tests;
import graph.common.Edge;
import graph.dagsp.DAGShortestPath;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGSPUnitTest {
    @Test void sp(){
        List<List<Edge>> g = new ArrayList<>();
        g.add(new ArrayList<>(List.of(new Edge(1,1), new Edge(2,5))));
        g.add(new ArrayList<>(List.of(new Edge(2,1))));
        g.add(new ArrayList<>());

        List<Integer> topo = List.of(0,1,2);
        var r = DAGShortestPath.shortest(g, topo, 0);
        assertEquals(2.0, r.dist[2]);
    }
}
