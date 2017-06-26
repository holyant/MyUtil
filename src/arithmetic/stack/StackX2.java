package arithmetic.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//栈，可存储所有类型的对象
public class StackX2 {
	private int maxSize;
	private Object[] stackArray;
	private int top;
	
	public StackX2(int s){
		maxSize = s;
		stackArray = new Object[maxSize];
		top = -1;
	}
	public void push(Object j){
		if(isFull()){
			throw new RuntimeException("不能插入，栈已满!");
		}
		stackArray[++top] = j;
	}
	public Object pop(){
		if(isEmpty()){
			throw new RuntimeException("不能取出，栈已空!");
		}
		return stackArray[top--];
	}
	public Object peek(){
		return stackArray[top];
	}
	public boolean isEmpty(){
		return(top==-1);
	}
	public boolean isFull(){
		return (top == maxSize-1);
	}
	public static void main(String[] args) {
		StackX2 theStack = new StackX2(10);
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		while(!theStack.isEmpty()){
			Object value = theStack.pop();
			System.out.println(value);
			System.out.println(" ");
		}
		System.out.println();
	}
}
//字符串倒序
class Reverser2{
	private String input;
	private String output;
	public Reverser2(String in){
		this.input = in;
	}
	public String doRev(){
		int stackSize = input.length();
		StackX2 theStack = new StackX2(stackSize);
		
		for(int j=0;j<input.length();j++){
			char ch = input.charAt(j);
			theStack.push(ch);
		}
		output = "";
		while(!theStack.isEmpty()){
			char ch = theStack.pop().toString().charAt(0);
			output = output +ch;
		}
		return output;
	}
	public static void main(String[] args) throws IOException{
		String input,output;
		while(true){
			System.out.println("Enter a string:");
			System.out.flush();
			input = getString();
			if(input.equals(""))
				break;
			Reverser2 theReverser2 = new Reverser2(input);
			output = theReverser2.doRev();
			System.out.println("Reversed:"+output);
		}
	}
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
}
//分隔符匹配
class BracketChecker{
	private String input;
	public BracketChecker(String in){
		input = in;
	}
	public void check(){
		int stackSize = input.length();
		StackX2 theStackX2 = new StackX2(stackSize);
		
		for(int j=0;j<stackSize;j++){
			char ch = input.charAt(j);
			switch(ch){
			case '{':
			case '[':
			case '(':
				theStackX2.push(ch);
				break;
			case '}':
			case ']':
			case ')':
				if(!theStackX2.isEmpty()){
					char chx = theStackX2.pop().toString().charAt(0);
					if((ch=='}'&&chx!='{')||
						(ch==']'&&chx!='[')||
						(ch==')'&&chx!='(')){
						System.out.println("Error:"+ch+" at"+j);
					}else {
//						System.out.println("Error:"+ch+" at "+j);
					}
				}
				break;
				default:
					break;
			}
		}
		if(!theStackX2.isEmpty()){
			System.out.println("Error:missing right delimiter");
		}
	}
	public static void main(String[] args) throws IOException{
		String input;
		while(true){
			System.out.println("Enter String containing delimiters:");;
			System.out.flush();
			input = getString();
			if(input.equals(""))
				break;
			BracketChecker theChecker = new BracketChecker(input);
			theChecker.check();
		}
	}
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
	
}
















