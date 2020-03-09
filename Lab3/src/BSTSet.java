class TNode{
    int element;
    TNode left;
    TNode right;
    TNode(int i, TNode l, TNode r)
    { element =i; left = l; right = r; }
}
public class BSTSet { //O(n)
    private TNode root;
    public BSTSet(){
        root = null;
    } //O(1)
//    public oldBSTSet(int[] input){
//        if(input.length == 0){
//            root = null;
//        }else {
//            root = new TNode(input[0], null, null);
//            for (int i = 0; i < input.length; i++) {
//                if (!isIn(input[i])) {
//                    add(input[i]);
//                }
//            }
//        }
//    }

    public BSTSet(int[] input){
        if(input.length == 0){
            root = null;
        }else {
            int[] sortedArr = insertionSort(input);
            root = recConstructor(sortedArr, 0, sortedArr.length-1);

        }
    }
    public TNode recConstructor(int[] input, int start, int end){ //O(nlogn)
        if(start>end){
            return null;
        }
        int mid = (start + end)/2;
        TNode root = new TNode(input[mid], null, null);
        root.left = recConstructor(input, start, mid-1);
        root.right = recConstructor(input, mid+1, end);
        return root;
    }

    public boolean isIn(int v){ //O(logn)
        if(root == null)
            return false;
        return(recIsIn(root, v));
    }
    private boolean recIsIn(TNode startNode, int v){ //recursively check if value is in BST
        if(startNode == null){
            return false;
        }if(v<startNode.element){
            return(recIsIn(startNode.left, v));
        }else if(v>startNode.element){
            return(recIsIn(startNode.right, v));
        }else{
            return true;
        }
    }

    public void add(int v){ //O(logn)
        if(root == null){
            root = new TNode(v, null, null);
        }else{
            recAdd(root, v);
        }
    }
    private TNode recAdd(TNode startNode, int v){ //recursively add function
        if(startNode == null){                      //referenced 2SI4 Lecture Slides to understand how to make it work
            startNode = new TNode(v, null, null);
        }else if(v < startNode.element){
            startNode.left = recAdd(startNode.left, v);
        }else if(v > startNode.element){
            startNode.right = recAdd(startNode.right, v);
        }
        return startNode;
    }

    public boolean remove(int v){
        if(!isIn(v))
            return false;
        else{
            recRemove(root, v);
            return true;
        }
    }
    private TNode recRemove(TNode startNode, int v){ //O(logn)
        if(v < startNode.element) //if less, recursively call to the left branch
            startNode.left = recRemove(startNode.left, v);
        else if(v > startNode.element) //if more, recursively call to the right branch
            startNode.right = recRemove(startNode.right, v);
        else if(startNode.left != null && startNode.right != null){ //both branches have elements
            startNode.element = minVal(startNode.right).element; //find the min value on the right branch
            startNode.right = removeMinVal(startNode.right); //remove the old min value on the right branch
        }else{
            if(startNode.left != null) // if only one branch empty, put the other branch in the current spot
                startNode = startNode.left;
            else
                startNode = startNode.right;
        }
        return startNode;
    }
    private TNode minVal(TNode startNode){
        TNode min = startNode;
        while(startNode.left != null){
            min = startNode.left;
            startNode = startNode.left;
        }
        return min;
    }
    private TNode maxVal(TNode startNode){
        TNode max = startNode;
        while(startNode.right != null){
            max = startNode.right;
            startNode = startNode.right;
        }
        return max;
    }
    private TNode removeMinVal(TNode startNode){
        if(startNode.left != null)
            startNode.left = removeMinVal(startNode.left);
        else
            startNode = startNode.right;
        return startNode;
    }


    public BSTSet union(BSTSet s){
        BSTSet unionSet = new BSTSet();
        union(unionSet, root);
        union(unionSet, s.root);
        return unionSet;
    }
    private void union(BSTSet s, TNode startNode){  //O(nlogn+mlogm)
        if(startNode != null){
            s.add(startNode.element);
            union(s, startNode.left);
            union(s, startNode.right);
        }
    }

    public BSTSet intersection(BSTSet s){ //O(nlogn + mlogm)
        BSTSet intersectSet = new BSTSet();
        intersection(intersectSet, s.root);
        return intersectSet;
    }
    private void intersection(BSTSet s, TNode startNode) {
        if (startNode != null) {
            if (isIn(startNode.element))
                s.add(startNode.element);
            intersection(s, startNode.left);
            intersection(s, startNode.right);
        }
    }

    public BSTSet difference(BSTSet s){ //O(nlogn + mlogm)
        BSTSet differenceSet = new BSTSet();
        difference(differenceSet, this.root, s);
        return differenceSet;
    }
    private void difference(BSTSet s, TNode startNode, BSTSet other) {
        if (startNode != null) {
            if (!other.isIn(startNode.element))
                s.add(startNode.element);
            difference(s, startNode.left, other);
            difference(s, startNode.right, other);
        }
    }

    public int size(){
        if(root == null)
            return 0;
        return(size(root, 0));
    } //O(n)
    private int size(TNode startNode, int counter){
        if(startNode!=null) {
            counter++;
            counter = size(startNode.left, counter);
            counter = size(startNode.right, counter);
        }
        return counter;
    }

    public int height () {
        return recHeight(this.root);
    } //O(n)

    private int recHeight(TNode startNode) {
        if (startNode == null) {
            return -1;
        } else {
            return(Math.max(recHeight(startNode.left) + 1,  recHeight(startNode.right) + 1));
        }
    }

    public void printBSTSet() {
        if (root == null) {
            System.out.println("The set is empty");
        } else {
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
    }

    private void printBSTSet(TNode t) {
        if (t != null) {
            printBSTSet(t.left);
            System.out.print(t.element + ", ");
            printBSTSet(t.right);
        }
    }

    public void printNonRec() { //O(n)
        System.out.print("The set elements are: ");
        MyStack stack = new MyStack();
        TNode currNode = root;
        while (currNode != null) {
            stack.push(currNode);
            currNode = currNode.left;
        }
        currNode = stack.peek();
        while (currNode != maxVal(root)) {
            currNode = stack.pop();
            System.out.print((currNode.element) + ", ");
            if (currNode.right != null) {
                currNode = currNode.right;
                while (currNode != null) {
                    stack.push(currNode);
                    currNode = currNode.left;
                }
            }
        }
    }


    private int[] insertionSort(int arr[]){
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        int temp = arr[arr.length-1];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != temp) {
                count++;
            }
            temp = arr[i];
        }
        int[] noRep = new int[count];
        temp = arr[arr.length-1];
        count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != temp) {
                noRep[count] = arr[i];
                count++;
            }
            temp = arr[i];
        }
        return noRep;
    }
}

