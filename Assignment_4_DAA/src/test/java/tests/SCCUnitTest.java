package tests;
import graph.scc.TarjanSCC;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SCCUnitTest {
    @Test void simple() {
        List<List<Integer>> g = new ArrayList<>();
        g.add(new ArrayList<>(List.of(1)));
        g.add(new ArrayList<>(List.of(2)));
        g.add(new ArrayList<>(List.of(0)));
        g.add(new ArrayList<>()); // бос тізім

        var s = new TarjanSCC(g);
        assertTrue(s.sccs.size() >= 2);
    }
}
