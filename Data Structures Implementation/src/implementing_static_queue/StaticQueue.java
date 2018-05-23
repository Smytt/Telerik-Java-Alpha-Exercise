package implementing_static_queue;

public class StaticQueue {
    public int[] stack;
    public int head;
    public int tail;
    public int capacity;

    public StaticQueue() {
        capacity = 10;
        stack = new int[capacity];
        head = -1;
        tail = -1;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void enqueue(int value) {
        tail++;
        if (tail == capacity && head == -1) {
            doubleCapacity();
        }

        tail %= capacity;

        if (tail == head) {
            doubleCapacity();
        }

        stack[tail] = value;
    }

    public int dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        head++;
        head %= capacity;
        return stack[head];
    }

    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty");
        }
        return stack[(head + 1) % capacity];
    }


    private void doubleCapacity() {
        int[] newStack = new int[capacity * 2];
        if (head == -1) {
            head = 0;
        }
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[(head + i) % capacity];
        }
        head = -1;
        tail = capacity;
        capacity *= 2;
        stack = newStack;
    }
}
