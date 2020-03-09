public class MyStack {
    myStackNode root;
        static class myStackNode {
        TNode data;
        myStackNode next;

        myStackNode(TNode data) {
            this.data = data;
        }
    }
    public boolean isEmpty() {
        if(root == null)
            return true;
        else
            return false;
    }

    public void push(TNode data) {
        myStackNode newNode = new myStackNode(data);

        if (root == null) {
            root = newNode;
        } else {
            myStackNode temp = root;
            root = newNode;
            newNode.next = temp;
        }
//        System.out.println(data + " pushed to stack");
    }
    public TNode pop() {
        TNode popped = new TNode(0, null, null);
        if (root == null) {
            System.out.println("Stack is Empty");
        } else {
            popped = root.data;
            root = root.next;
        }
        return popped;
    }
    public TNode peek() {
        if (root == null) {
            System.out.println("Stack is empty");
            return new TNode(0, null, null);
        } else {
            return root.data;
        }
    }
}
