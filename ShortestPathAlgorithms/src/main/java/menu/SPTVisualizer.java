package menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphDataStructure.DirectedEdge;

public class SPTVisualizer {
    public static void printShortestPathTree(DirectedEdge[] edgeTo, int source) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int v = 0; v < edgeTo.length; v++) {
            if (edgeTo[v] != null) {
                int parent = edgeTo[v].source();
                tree.putIfAbsent(parent, new ArrayList<>());
                tree.get(parent).add(v);
            }
        }

        printTree(source, tree, "", true);
    }

    private static void printTree(int node, Map<Integer, List<Integer>> tree, String prefix, boolean isLast) {
        System.out.println(prefix + (isLast ? "└── " : "├── ") + node);

        List<Integer> children = tree.getOrDefault(node, new ArrayList<>());

        for (int i = 0; i < children.size(); i++) {
            printTree(children.get(i), tree, prefix + (isLast ? "    " : "│   "), i == children.size() - 1);
        }
    }
}

//prints the shortest path tree
//0
//├── 1
//│   ├── 3
//│   └── 4
//└── 2
//├── 5
//└── 6