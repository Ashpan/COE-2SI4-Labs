public class BSTSetTest {
    public static void main(String[] args) {
        System.out.println("-----Test Constructor 1---");
        BSTSet set1 = new BSTSet();
        set1.printBSTSet();
        System.out.println("-----Test Constructor 2---");
        System.out.println("Creating with 14,15,16,18,25,30,35,39,40,46,47,50,88,99,102");
        BSTSet set2 = new BSTSet(new int[] {14,15,16,18,25,30,35,39,40,46,47,50,88,99,102});
        System.out.println(set2.height());
        System.out.println(set2.size());
        set2.printBSTSet();
        System.out.println();
        System.out.println("Creating with empty array");
        set2 = new BSTSet(new int[] {});
        set2.printBSTSet();

        System.out.println("-----Test isIn-----");
        System.out.println("Testing element that is in");
        set2 = new BSTSet(new int[] {1,69,-1,420,5});
        System.out.println(set2.isIn(69));

        System.out.println("Testing element that is not in");
        System.out.println(set2.isIn(-48990));

        System.out.println("-----Test add-----");
        set2 = new BSTSet();
        System.out.println("Adding to empty set");
        set2.add(42);
        set2.printBSTSet();
        System.out.println("Adding existing value");
        set2.add(42);
        set2.printBSTSet();
        System.out.println("Adding more elements");
        set2.add(42);
        set2.add(-5435);
        set2.add(454353);
        set2.add(5432);
        set2.printBSTSet();

        System.out.println("-----Test Remove-----");
        System.out.println("removing value that doesnt exist");
        System.out.println(set2.remove(99999));
        set2.printBSTSet();
        System.out.println("removing value that does exist");
        System.out.println(set2.remove(42));
        set2.printBSTSet();

        set1.add(-4); set1.add(-674); set1.add(999);
        System.out.println("-----Test Union-----");
        System.out.println("Set 1");
        set1.printBSTSet();
        System.out.println("Set 2");
        set2.printBSTSet();
        System.out.println("Union:");
        set1.union(set2).printBSTSet();

        System.out.println("Union of 2 empty sets");
        BSTSet emptySet = new BSTSet();
        emptySet.union(emptySet).printBSTSet();

        System.out.println("-----Test Intersect-----");
        set2.add(-4); set2.add(999);
        System.out.println("Set 1");
        set1.printBSTSet();
        System.out.println("Set 2");
        set2.printBSTSet();
        System.out.println("Intersect:");
        set1.intersection(set2).printBSTSet();

        System.out.println("Intersection of 2 empty sets");
        emptySet.intersection(emptySet).printBSTSet();

        System.out.println("-----Test Intersect-----");
        set2.add(-4); set2.add(999);
        System.out.println("Set 1");
        set1.printBSTSet();
        System.out.println("Set 2");
        set2.printBSTSet();
        System.out.println("1 - 2:");
        set1.difference(set2).printBSTSet();
        System.out.println("2 - 1:");
        set2.difference(set1).printBSTSet();

        System.out.println("Difference of 2 empty sets");
        emptySet.difference(emptySet).printBSTSet();

        System.out.println("Size of set 2");
        System.out.println(set2.size());
        System.out.println("Height of set 2");
        System.out.println(set2.height());
        System.out.println("Regular print");
        set2.printBSTSet();
        System.out.println("Non recursive print");
        set2.printNonRec();


    }
}
