import java.math.BigInteger;

public class HugeIntTiming {
    public static void main(String[] args) {
        HugeInteger huge1, huge2, huge3;
        BigInteger big1, big2, big3;
        long startTimeHuge, startTimeBig, endTimeHuge, endTimeBig;
        double runTimeHuge = 0, runTimeBig = 0;
        int MAXRUN = 5;
        for (int n = 0; n < 5; n++) {
            for (int numInts = 0; numInts < MAXRUN; numInts++) {
                huge1 = new HugeInteger((int) (Math.pow(10, n)));
                huge2 = new HugeInteger((int) (Math.pow(10, n)));
                startTimeHuge = System.currentTimeMillis();
                for (int numRun = 0; numRun < MAXRUN; numRun++) {
                    huge3 = huge1.add(huge2);
                    System.out.println("Adding: " + Math.pow(10, n));
                }
                endTimeHuge = System.currentTimeMillis();
                runTimeHuge +=(double) (endTimeHuge - startTimeHuge)/((double) MAXRUN);
            }
            runTimeHuge = runTimeHuge/((double)MAXRUN);
            System.out.println(runTimeHuge);
        }
    }

}
