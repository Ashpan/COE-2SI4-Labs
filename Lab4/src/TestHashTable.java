import java.util.Random;
public class TestHashTable {
    public void probing(HashTableLin lin){
        Random ran = new Random();
        int probes = 0;
        for (double load = 0.1; load < 1; load+=0.1) {
            for (int rep = 0; rep < 100; rep++) {
                for (int i = 0; i < 100000; i++) {
                    probes += lin.insertCount(ran.nextInt(1000000));
                }
            }
            System.out.println("Load: " + load + ". Average Probes: " + (probes/10000000));
        }
    }

    public void probing(HashTableQuad lin){

    }
    public static void main(String[] args) {
        HashTableLin lin = new HashTableLin(3, 0.5);
        System.out.println("Size: " + lin.getSize());
        System.out.println("Adding 4 elements to maxnum of 3");
        lin.insert(6);
        lin.insert(8);
        lin.insert(12);
        lin.insert(69);
        System.out.println("Size: " + lin.getSize());
        lin.printKeysAndIndexes();

        HashTableQuad quad = new HashTableQuad(3, 0.5);
        System.out.println("Size: " + quad.getSize());
        System.out.println("Adding 4 elements to maxnum of 3");
        quad.insert(6);
        quad.insert(8);
        quad.insert(86);
        quad.insert(69);
        System.out.println("Size: " + quad.getSize());
        quad.printKeysAndIndexes();

        HashTableLin linearHash = new HashTableLin(100000, 0.5);
        Random ran = new Random();
        int probes = 0;
        for (double load = 0.1; load < 1; load+=0.1) {
            for (int rep = 0; rep < 100; rep++) {
                for (int i = 0; i < 100000; i++) {
                    int ranVal = ran.nextInt(10000);
                    if(!linearHash.isIn(ranVal))
                        probes += linearHash.insertCount(ranVal);
                }
            }
            System.out.println("Load: " + load + ". Average Probes: " + (probes/10000000.0));
        }
    }
}
