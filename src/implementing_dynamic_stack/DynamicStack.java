package implementing_dynamic_stack;

public class DynamicStack {
    public Node top;

    public DynamicStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        this.top = newNode;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("DynamicStack is empty");
        }

        int value = top.value;
        top = top.next;

        return value;
    }

    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("DynamicStack is empty");
        }

        return top.value;
    }
}
