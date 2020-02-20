import java.math.BigInteger;

public class HugeIntTiming {
    public static void main(String[] args) {
        HugeInteger huge1, huge2;
        BigInteger big1, big2;
        System.out.println("-----------------");

        long startTimeHuge, startTimeBig, endTimeHuge, endTimeBig;
        double runTimeHuge = 0, runTimeBig = 0;
        int MAXRUN = 300000000;
        int MAXNUMINTS = 100;
        int n = 10000;
            for (int numInts = 0; numInts < MAXNUMINTS; numInts++) {
                huge1 = new HugeInteger(n);
                huge2 = new HugeInteger(n);
                big1 = new BigInteger(huge1.toString());
                big2 = new BigInteger(huge2.toString());
                startTimeHuge = System.currentTimeMillis();
                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    huge1.compareTo(huge2);
                }
                endTimeHuge = System.currentTimeMillis();
                runTimeHuge +=(double) (endTimeHuge - startTimeHuge)/((double) MAXRUN);
                startTimeBig = System.currentTimeMillis();
                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    big1.compareTo(big2);
                }
                endTimeBig = System.currentTimeMillis();
//                System.out.println("Huge: " + (double) (endTimeHuge-startTimeHuge));
//                System.out.println("Big: " + (double) (endTimeBig-startTimeBig));
                runTimeBig +=(double) (endTimeBig - startTimeBig)/((double) MAXRUN);
            }
            runTimeHuge = runTimeHuge/((double)MAXRUN);
            runTimeBig = runTimeBig/((double)MAXRUN);
            System.out.println("HUGE: \t" + runTimeHuge + " ms");
            System.out.println("BIG: \t" + runTimeBig + " ms");
            System.out.println("--------------------------");




    }

}
