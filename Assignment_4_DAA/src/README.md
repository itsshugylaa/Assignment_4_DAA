#  Assignment 4 — Design and Analysis of Algorithms  
### Topic: Strongly Connected Components, Condensation DAG, and Critical Path Analysis  
**Author:** Shugyla Turganbek  
**Group:** SE-2430

---

##  1. Project Overview
This project implements a complete workflow for analyzing directed weighted graphs as part of Smart City / Smart Campus scheduling problems.

Implemented algorithms:
1. **Strongly Connected Components (Tarjan’s Algorithm)**
2. **Condensation DAG construction**
3. **Topological Sorting (Kahn’s Algorithm)**
4. **Single-Source Shortest and Longest Paths (DAG)**
5. **Critical Path identification**

The program reads graph data from `data/tasks.json`, finds all SCCs, builds a condensed DAG, sorts it topologically, and computes both shortest and longest paths to determine the critical task sequence.

---

##  2. Input Graph Example
```json
{
  "directed": true,
  "n": 8,
  "edges": [
    {"u": 0, "v": 1, "w": 3},
    {"u": 1, "v": 2, "w": 2},
    {"u": 2, "v": 3, "w": 4},
    {"u": 3, "v": 1, "w": 1},
    {"u": 4, "v": 5, "w": 2},
    {"u": 5, "v": 6, "w": 5},
    {"u": 6, "v": 7, "w": 1}
  ],
  "source": 4,
  "weight_model": "edge"
}
````

---

##  3. Algorithm Workflow

1. **TarjanSCC** — performs DFS to find strongly connected components.
2. **Condensation** — builds a new DAG where each SCC becomes one node.
3. **TopologicalSort** — uses Kahn’s algorithm to determine valid execution order.
4. **DAGShortestPath** — computes shortest and longest paths from the source.
5. **Metrics** — measures DFS calls, edge visits, relaxations, and time.
6. **Derived Order** — produces final task execution order based on SCC + TopoSort.

---

##  4. Experimental Output

Example output for `data/tasks.json`:

```
=== Assignment_4_DAA ===
SCCs: 6 | dfsCalls=8 | edgeVisits=7 | time=0.153ms
Topo order: [1, 5, 0, 4, 3, 2] | pushes=6 | pops=6 | time=0.330ms
Derived order of original tasks: [0, 4, 1, 2, 3, 5, 6, 7]
Shortest relaxations=3 | Longest relaxations=3
Shortest distances: [Infinity, Infinity, 8.0, 7.0, 2.0, 0.0]
Critical path length: 8.00 (target comp=2)
Critical component path: [5, 4, 3, 2]
Expanded original node path: [4, 5, 6, 7]
=== Done ===
```

---

##  5. Results Summary

| Metric                           | Value         |
| -------------------------------- | ------------- |
| Number of SCCs                   | 6             |
| DFS Calls                        | 8             |
| Edge Visits                      | 7             |
| Topological Pushes / Pops        | 6 / 6         |
| Relaxations (Shortest / Longest) | 3 / 3         |
| Critical Path                    | 4 → 5 → 6 → 7 |
| Total Execution Time             | ~0.5 ms       |

---

##  6. Discussion

The implementation shows correct and efficient behavior for all graph analysis steps.

* Tarjan’s algorithm successfully detects cyclic groups.
* The condensation process ensures a valid acyclic structure.
* Topological sorting establishes a safe processing order.
* DAG shortest/longest path algorithms correctly compute both minimal and maximal task sequences.
  All operations run in **O(V + E)** time, confirmed by measured metrics.

---

##  7. Testing

JUnit 5 unit tests validate:

* Correct SCC detection (`SCCUnitTest`)
* Topological sorting correctness (`TopoUnitTest`)
* Shortest path accuracy in DAG (`DAGSPUnitTest`)

All tests pass successfully.

---

##  8. Conclusion

This assignment fully satisfies the project requirements:

* Implements SCC, condensation DAG, topological sort, and path algorithms.
* Includes timing metrics, counters, reconstruction, and dataset generator.
* Produces correct and interpretable results.

