package tests;
import graph.topo.TopologicalSort;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopoUnitTest {
    @Test void dag() {
        List<Set<Integer>> g = new ArrayList<>();
        g.add(new HashSet<>(Set.of(1)));
        g.add(new HashSet<>(Set.of(2)));
        g.add(new HashSet<>());

        var r = TopologicalSort.kahnWithCounts(g);
        assertEquals(3, r.order.size());
    }
}
