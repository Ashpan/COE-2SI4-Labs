import java.util.Arrays;

public class MaxHeap {
    private Integer[] heap;
    int maxSize; //2^n should be size
    int currSize;

    public MaxHeap(int treeSize){
        maxSize = treeSize;
        currSize = 0;
        heap = new Integer[treeSize];
    }

    public MaxHeap(Integer[] someArray){
        maxSize = someArray.length;
        currSize = 0;
        heap = new Integer[maxSize];
        for (int i:someArray) {
            insert(i);
        }
    }

    public void insert(int n){
        if(maxSize == currSize)
            Reheap();
        boolean empty = false;
        int i = currSize;
        heap[i] = n;
        boolean noSwap = false;
        while(!noSwap) { //swap until no longer needed
            int parentIndex = (i - 1) / 2;
            if (heap[parentIndex] < n) { //flip with parent
                int temp = heap[parentIndex];
                heap[parentIndex] = n;
                heap[i] = temp;
                i = parentIndex;
            }else{
                noSwap = true;
            }
        }
        currSize++;
    }
    private boolean isHeap(int index){
        int left = index*2+1;
        int right = index*2+2;
        return((heap[left] == null || heap[index] >= heap[left]) && (heap[right] == null || heap[index] >= heap[right]));
    }


    private int deleteMax(){
        int max = heap[0];
        heap[0] = heap[currSize-1];
        heap[currSize-1] = null;
        boolean dontChange = false;
        int current = 0;
        while(!isHeap(current)){
            
            int left = current*2+1;
            int right = current*2+2;
            if(left>currSize-2){
                dontChange = true;
            }else if(heap[left] != null && heap[left] > heap[current]){
                int temp = heap[left];
                heap[left] = heap[current];
                heap[current] = temp;
                current = current*2+1;
            }else if(heap[left] == null){
                dontChange = true;
            }else if(heap[right] != null && heap[right] > heap[current]){
                int temp = heap[right];
                heap[right] = heap[current];
                heap[current] = temp;
                current = current*2+2;
            }else if(heap[right] == null){
                dontChange = true;
            }else{
                dontChange = true;
            }
        }
        currSize--;
        return max;
    }
    public static void heapsort(Integer[] arrayToSort){
        MaxHeap sorted = new MaxHeap(arrayToSort);
        System.out.println(sorted.toString());
        for (int i = sorted.currSize-1; i >= 0; i--) {
            arrayToSort[i] = sorted.deleteMax();
            System.out.println(Arrays.toString(arrayToSort) + " " + arrayToSort[i]);
        }
    }
    public String toString(){
        String output = "";
        for(int i=0; i<currSize; i++) {
            if(i == currSize-1)
                output+=heap[i];
            else
                output += (heap[i] + ", ");
        }
    return output;
    }


    public int getCurrSize(){
        return currSize;
    }

    public int getMaxSize(){
        return maxSize;
    }
    private void Reheap(){
        Integer[] newHeap = new Integer[2*maxSize];
        maxSize *= 2;
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    public static void main(String[] args) {
//        MaxHeap heap = new MaxHeap(5);
//        System.out.println(heap.toString());
//        heap.insert(1);
//        heap.insert(2);
//        heap.insert(3);
//        heap.insert(4);
//        heap.insert(5);
//        System.out.println(heap.toString());
//        System.out.println(heap.deleteMax());
//        System.out.println(heap.toString());
        Integer[] test = new Integer[] {5,345,5,543,634,5,334,2,34,1234,74};
        System.out.println(Arrays.toString(test));
        System.out.println("--------------------");
//        MaxHeap newHeap = new MaxHeap(test);
//        System.out.println(newHeap.toString());
        heapsort(test);
//        System.out.println(Arrays.toString(test));


    }
}