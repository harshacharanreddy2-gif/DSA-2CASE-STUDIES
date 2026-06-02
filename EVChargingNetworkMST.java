import java.util.*;

class UnionFindNaive {
    int[] parent;

    UnionFindNaive(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // No path compression
    int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    // No union by rank
    boolean union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx == ry)
            return false;

        parent[rx] = ry;
        return true;
    }
}

public class EVChargingNetworkMST {

    static List<String> vertices =
            Arrays.asList("C", "D", "A", "B", "P", "E", "F");

    static List<int[]> kruskalNaive(int n, int[][] edges) {

        Arrays.sort(edges, Comparator.comparingInt(e -> e[0]));

        UnionFindNaive uf = new UnionFindNaive(n);
        List<int[]> mst = new ArrayList<>();

        for (int[] e : edges) {
            int u = e[1];
            int v = e[2];

            if (uf.union(u, v)) {
                mst.add(e);
            }
        }

        return mst;
    }

    public static void main(String[] args) {

        int n = 7;

        // {cost, node1, node2}
        int[][] edges = {
                {4, 0, 1},   // C-D
                {5, 2, 3},   // A-B
                {6, 3, 0},   // B-C
                {7, 4, 1},   // P-D
                {8, 4, 2},   // P-A
                {9, 5, 6},   // E-F
                {9, 4, 6},   // P-F
                {10, 4, 0},  // P-C
                {11, 5, 4},  // E-P
                {12, 4, 3},  // P-B
                {14, 5, 2},  // E-A
                {15, 6, 3}   // F-B
        };

        List<int[]> mst = kruskalNaive(n, edges);

        int totalCost = 0;

        System.out.println("Electric Vehicle Charging Network - MST");
        System.out.println("----------------------------------------");
        System.out.println("Selected MST Edges:");

        for (int[] e : mst) {
            String u = vertices.get(e[1]);
            String v = vertices.get(e[2]);

            System.out.println(u + " - " + v + " : ₹" + e[0] + " crore");
            totalCost += e[0];
        }

        System.out.println("\nTotal MST Cost = ₹" + totalCost + " crore");

        System.out.println("\nReliability Check:");
        System.out.println("Only one path exists from P to B in MST.");
        System.out.println("Add edge P-A (₹8 crore) for a second edge-disjoint path.");

        System.out.println("Augmented Network Cost = ₹" + (totalCost + 8) + " crore");
    }
}