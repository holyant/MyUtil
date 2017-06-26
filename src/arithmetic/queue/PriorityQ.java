package arithmetic.queue;
//优先级队列
public class PriorityQ {
	private int maxSize;
	private long[] queArray;
	private int nItems;
	public PriorityQ(int s){
		maxSize = s;
		queArray = new long[maxSize];
		nItems = 0;
	}
	public void insert(long item){
		int j;
		if(nItems==0){
			queArray[nItems++] = item;
		}else{
			for(j=nItems-1;j>=0;j--){
				if(item>queArray[j]){
					queArray[j+1] = queArray[j];
				}else{
					break;
				}
				
			}
			queArray[j+1] = item;
			nItems++;
		}
	}
	public long remove(){
		return queArray[--nItems];
	}
	public long peekMin(){
		return queArray[nItems-1];
	}
	public boolean isEmpty(){
		return(nItems==0);
	}
	public boolean isFull(){
		return (nItems==maxSize);
	}
	public int size(){
		return nItems;
	}
	public static void main(String[] args) {
		PriorityQ thePriorityQ = new PriorityQ(5);
		thePriorityQ.insert(30);
		thePriorityQ.insert(50);
		thePriorityQ.insert(10);
		thePriorityQ.insert(40);
		thePriorityQ.insert(20);
		
		while(!thePriorityQ.isEmpty()){
			long item = thePriorityQ.remove();
			System.out.println(item+" ");
		}
		System.out.println("");
	}
}
