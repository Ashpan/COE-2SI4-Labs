import java.util.Arrays;

public class MaxHeap {
    private Integer[] heap;
    private int maxSize;
    private int currSize;

    public MaxHeap(int treeSize){  //init heap with 0 elements and an array of null values
        maxSize = treeSize;
        currSize = 0;
        heap = new Integer[treeSize];
    }

    public MaxHeap(Integer[] someArray){ //initialize heap with each element from someArray using insert method
        maxSize = someArray.length;
        currSize = 0;
        heap = new Integer[maxSize];
        for (int i:someArray) {
            insert(i);
        }
    }

    public void insert(int n){
        if(maxSize == currSize) //if it will exceed current size, resize the array and reinsert the elements
            Reheap();
        boolean empty = false;
        int i = currSize;
        heap[i] = n; //insert new element into last empty spot in array
        boolean noSwap = false;
        while(!noSwap) { //swap until children are smaller than parent
            int parentIndex = (i - 1) / 2; //value of parent index
            if (heap[parentIndex] < n) { //if not a heap, flip with parent
                int temp = heap[parentIndex];
                heap[parentIndex] = n;
                heap[i] = temp;
                i = parentIndex;
            }else{ //is a heap so set bool to true to end while loop
                noSwap = true;
            }
        }
        currSize++; //increment sizecounter
    }

    private int deleteMax(){
        int max = heap[0]; //delete max elem and replace with last added elem
        heap[0] = heap[currSize-1];
        heap[currSize-1] = null;
        int current = 0;
        while(!isHeap(current)){ //while current index isnot true for a heap
            int left = current*2+1; //left and right indices
            int right = current*2+2;
            if(heap[left] != null){ //if left child is not null
                if(heap[right] == null){ //but right is, flip right left child and parent
                    int temp = heap[left];
                    heap[left] = heap[current];
                    heap[current] = temp;
                    current = left;
                }else { //if right child is not null
                    int index = 0;
                    if(heap[left] >= heap[right]) //if left child > right child, swap parent and left
                        index = left;
                    else //if right child > left child, swap parent and right
                        index = right;
                    int temp = heap[index];
                    heap[index] = heap[current];
                    heap[current] = temp;
                    current = index;
                }
            }else //if left child is null, end while loop, nothing can be done anymore
                break;
        }
        currSize--;
        return max;
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

    public static void heapsort(Integer[] arrayToSort){
        MaxHeap sorted = new MaxHeap(arrayToSort); //create heap of array
        int maxLoop = sorted.currSize; //number of times loop should run
        for (int i = 0; i < maxLoop; i++) {
            arrayToSort[i] = sorted.deleteMax(); //add value from delete max to array until heap is completed
        }
    }

    public int getSize(){
        return currSize;
    }
    public int getCapacity(){
        return maxSize;
    }

    private void Reheap(){ //resize heap
        Integer[] newHeap = new Integer[2*maxSize];
        maxSize *= 2;
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
    private boolean isHeap(int index){ //checks if value at index follows max heap conditions
        int left = index*2+1;
        int right = index*2+2;
        if(left>=heap.length){ return true; }
        if(right>=heap.length){ return true; }
        return((heap[left] == null || heap[index] >= heap[left]) && (heap[right] == null || heap[index] >= heap[right]));
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(5);
        System.out.println(heap.toString());
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        System.out.println(heap.toString());
        System.out.println(heap.deleteMax());
        System.out.println(heap.deleteMax());
        System.out.println(heap.toString());
        Integer[] test = new Integer[] {-5,345,5,-543,-634,5,334,2,34,1234,74};
        System.out.println(Arrays.toString(test));
        System.out.println("Heap: \t" + new MaxHeap(test).toString());
        heapsort(test);
        System.out.println(Arrays.toString(test));


    }
}
