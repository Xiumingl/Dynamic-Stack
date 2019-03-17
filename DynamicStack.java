
public class DynamicStack {
	// Invariant of the ArrayStack class:
	// 1. The top index of the stack is in the instance variable top, so the number
	// of items in the stack is (top+1);
	// 2. For a non-empty stack, the items in the stack are stored in a partially
	// filled array called data, with the bottom of the stack at data[0], the next
	// item at data[1], and so on,to the top of the stack at data[top].
	// 3. The maximum number of items in the stack is in the instance variable
	// capacity;
	private Object[] data;
	private int capacity;
	private int top;

	public DynamicStack(int capacity) {
		this.capacity = capacity;
		data = new Object[capacity];
		top = -1;
	}

	public DynamicStack() {
		this(6);
	}

	public boolean isEmpty() {
		if (this.top == -1) {
			return true;
		} else {
			return false;
		}
	}

	// best case:жи(1) worst case:жи(n)
	public void push(Object n) {
		if (capacity == top + 1) {
			Object[] copy = this.data;
			this.capacity = 2 * this.capacity;
			this.data = new Object[capacity];
			this.top = -1;
			for (Object i : copy) {
				this.push(i);
			}
			this.push(n);
		} else {
			this.data[++top] = n;
		}
	}

	public Object pop() {
		if (isEmpty()) {
			return null;
		} else {
			Object topItem = this.data[top];
			this.data[top--] = null;
			return topItem;
		}

	}

	public Object peek() {
		if (isEmpty()) {
			return null;
		} else {
			return this.data[top];
		}

	}

	public static void main(String[] args) {

		DynamicStack testStack = new DynamicStack();
		for (int i = 0; i < 1000; i++) {
			testStack.push(i);
		}
		for (int i = 0; i < 1000; i++) {
			String item = String.valueOf(testStack.pop());
			System.out.print(item + '\n');
		}

	}
}
