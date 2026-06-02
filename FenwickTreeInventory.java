public class FenwickTreeInventory {

    private int[] bit;
    private int n;

    // Constructor
    public FenwickTreeInventory(int n) {
        this.n = n;
        this.bit = new int[n + 1];
    }

    /**
     * Add delta to the stock at shelf i.
     */
    public void pointUpdate(int i, int delta) {
        while (i <= n) {
            bit[i] += delta;
            i += (i & -i);
        }
    }

    /**
     * Return stock[1] + stock[2] + ... + stock[i].
     */
    public long prefixSum(int i) {
        long sum = 0;

        while (i > 0) {
            sum += bit[i];
            i -= (i & -i);
        }

        return sum;
    }

    /**
     * Return total stock between shelves left and right.
     */
    public long rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    /**
     * Display BIT array.
     */
    public void printBIT() {
        System.out.println("\nBIT Array:");

        for (int i = 1; i <= n; i++) {
            System.out.print(bit[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // Shelf stock quantities (index 0 unused)
        int[] stock = {
                0,
                100, 80, 0, 250, 150,
                60, 0, 0, 300, 0,
                120, 90, 70, 0, 0
        };

        int n = 15;

        FenwickTreeInventory warehouse = new FenwickTreeInventory(n);

        // Build Fenwick Tree
        for (int i = 1; i <= n; i++) {
            warehouse.pointUpdate(i, stock[i]);
        }

        System.out.println("======================================");
        System.out.println("FLIPKART WAREHOUSE INVENTORY TRACKING");
        System.out.println("Using Fenwick Tree (Binary Indexed Tree)");
        System.out.println("======================================");

        System.out.println("\nOriginal Stock Data:");

        for (int i = 1; i <= n; i++) {
            System.out.println("Shelf " + i + " : " + stock[i] + " units");
        }

        // Display BIT array
        warehouse.printBIT();

        System.out.println("\n--------------------------------------");
        System.out.println("QUERY: Total Stock from Shelf 5 to 12");
        System.out.println("--------------------------------------");

        long prefix12 = warehouse.prefixSum(12);
        long prefix4 = warehouse.prefixSum(4);
        long answer = warehouse.rangeSum(5, 12);

        System.out.println("Prefix(12) = " + prefix12);
        System.out.println("Prefix(4)  = " + prefix4);
        System.out.println("Stock[5..12] = Prefix(12) - Prefix(4)");

        System.out.println("Total Stock = "
                + prefix12 + " - "
                + prefix4 + " = "
                + answer + " units");

        System.out.println("\nVerification:");

        int manual =
                stock[5] + stock[6] + stock[7] + stock[8]
                        + stock[9] + stock[10] + stock[11] + stock[12];

        System.out.println(
                stock[5] + " + " +
                        stock[6] + " + " +
                        stock[7] + " + " +
                        stock[8] + " + " +
                        stock[9] + " + " +
                        stock[10] + " + " +
                        stock[11] + " + " +
                        stock[12] + " = " +
                        manual + " units");

        System.out.println("\n--------------------------------------");
        System.out.println("POINT UPDATE OPERATION");
        System.out.println("--------------------------------------");

        System.out.println("Adding 50 units to Shelf 10...");

        warehouse.pointUpdate(10, 50);

        stock[10] += 50;

        long updatedStock = warehouse.rangeSum(5, 12);

        System.out.println("Updated Stock at Shelf 10 = "
                + stock[10] + " units");

        System.out.println("Updated Total Stock (Shelf 5-12) = "
                + updatedStock + " units");

        System.out.println("\nUpdated BIT Array:");
        warehouse.printBIT();

        System.out.println("\n--------------------------------------");
        System.out.println("TIME COMPLEXITY");
        System.out.println("--------------------------------------");
        System.out.println("Point Update : O(log n)");
        System.out.println("Prefix Sum   : O(log n)");
        System.out.println("Range Query  : O(log n)");

        System.out.println("\nProgram Executed Successfully.");
    }
}