public class HashTableLin {
    private int[] table;
    private int size;
    private double load;
    private int elems;

    public HashTableLin(int maxNum, double load){
        this.size = nextPrime(maxNum/load);
        this.table = new int[size];
        this.load = load;
        this.elems = 0;
    }
    public void insert(int n){
        if(((double)(elems+1))/((double) size) > load){ rehash(); }
        if(!isIn(n)){
            int index = n%size;
            while(table[index] != 0){
                index = (index+1)%size;
            }
            table[index] = n;
            elems++;
        }
    }

    public int insertCount(int n){
        if(((double)(elems+1))/((double) size) > load){ rehash(); }
        int probes = 1;
        if(!isIn(n)){
            int index = n%size;
            while(table[index] != 0){
                index = (index+1)%size;
                probes++;
            }
            table[index] = n;
            elems++;
        }
        return probes;
    }

    private void rehash(){
        int oldElems = elems;
        int biggerSize = nextPrime(this.size*2);
        size = biggerSize;
        int[] oldTable = deepCopy(table);
        table = new int[biggerSize];
        for (int i:oldTable)
            insert(i);
        elems = oldElems;
    }

    public boolean isIn(int n){
        int startIndex = n%size;
        for (int i = 0; i <= table.length; i++) {
            if(table[(startIndex+i)%size] == n)
                return true;
        }
        return false;
    }

    public void printKeys(){
        for (int i:table) {
            if(i!=0)
                System.out.print(i + ", ");
        }
    }
    public void printKeysAndIndexes(){
        for (int i = 0; i < table.length; i++) {
            if(table[i]!=0)
                System.out.println("Index: " + i + ", Key: " + table[i]);
        }
    }

    public int getSize(){
        return size;
    }
    public double getLoad(){
        return load;
    }
    public int getNumElements(){
        return elems;
    }

    private int[] deepCopy(int[] array){
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    private int nextPrime(double n){
        if (n <= 1){ return 2; }
        int currentVal = (int) n;
        boolean isPrime = false;
        while (!isPrime){
            currentVal++;
            if (prime(currentVal)){ isPrime = true; }
        }
        return currentVal;
    }
    private boolean prime(int val){
        if (val <= 1) return false;
        if (val <= 3) return true;
        if (val%2 == 0 || val%3 == 0) return false;
        for (int i=5; i*i <= val; i+=6)
            if (val%i == 0 || val%(i+2) == 0)
                return false;
        return true;
    }
}