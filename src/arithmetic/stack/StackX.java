package arithmetic.stack;
//栈，只存储long类型数据
public class StackX {
	private int maxSize;
	private long[] stackArray;
	private int top;
	
	public StackX(int s){
		maxSize = s;
		stackArray = new long[maxSize];
		top = -1;
	}
	public void push(long j){
		if(isFull()){
			throw new RuntimeException("不能插入，栈已满!");
		}
		stackArray[++top] = j;
	}
	public long pop(){
		if(isEmpty()){
			throw new RuntimeException("不能取出，栈已空!");
		}
		return stackArray[top--];
	}
	public long peek(){
		return stackArray[top];
	}
	public boolean isEmpty(){
		return(top==-1);
	}
	public boolean isFull(){
		return (top == maxSize-1);
	}
	public static void main(String[] args) {
		StackX theStack = new StackX(10);
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		while(!theStack.isEmpty()){
			long value = theStack.pop();
			System.out.println(value);
			System.out.println(" ");
		}
		System.out.println();
	}
}















