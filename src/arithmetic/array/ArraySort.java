package arithmetic.array;

//简单排序
public class ArraySort {
	private long[] a;
	private int nElems;
	
	public ArraySort(int max){
		a = new long[max];
		nElems = 0;
	}
	public void insert (long value){
		a[nElems] = value;
		nElems ++;
	}
	public void display(){
		for(int j=0;j<nElems;j++){
			System.out.print(a[j]+" ");
		}
		System.out.println("");
	}
	//冒泡排序(基本不用，数据量很小的时候会有些应用价值)
	//每轮比较都是把最大的数放到后面,而且永远两两相邻元素比较
	public void bubbleSort(){
		int out,in;
		for(out=nElems-1;out>1;out--){
			for(in=0;in<out;in++){
				if(a[in]>a[in+1]){
					swap(in,in+1);
				}
			}
		}
	}
	
	//选择排序（虽然把交换次数降到了最低，但是比较次数还是很多；当数据量小，并且交换数据相比比较数据更耗时的时候，可以使用）
	//选择排序法是将数组的第一个数据作为最大或者最小的值，然后通过比较循环，输出有序的数组。
	public void selectionSort(){
		int out,in,min,max;
		for(out=0;out<nElems-1;out++){
			min = out;
			for(in=out+1;in<nElems;in++){
				if(a[in]<a[min])
					min = in;
			}
			swap3(out,min);
		}
	}
	//双重选择，比单向的稍微快一点
	public void selectionSort1(){
		int out,in,min,max;
		for(out=0;out<nElems/2+1;out++){
			min = out;
			max = out;
			for(in=out+1;in<nElems-out;in++){
				if(a[in]<a[min])
					min = in;
				if(a[in]>a[max])
					max = in;
			}
			swap3(out,min);
			swap3(nElems-out-1,max);
		}
	}
	//插入排序（当数据量比较小，且数据大致有序的时候，是比较好的选择）
	//插入排序是选择一个数组中的数据，通过不断的插入比较最后进行排序
	//插入算法把要排序的数组分成两部分：第一部分包含了这个数组的所有元素，
	//但将最后一个元素除外，而第二部分就只包含这一个元素。
	//在第一部分排序后，再把这个最后元素插入到此刻已是有序的第一部分里的位置
	public void insertionSort(){
		int in,out;
		for(out=1;out<nElems;out++){
			long temp = a[out];
			in = out;
			while(in>0&&a[in-1]>=temp){
				a[in] = a[in-1];
				--in;
			}
			a[in] = temp;
		}
	}
	
	//删除重复数据,需要是有序的集合
	public void noDups(){
		long[] b = new long[a.length];
		int inc = 0;
		b[inc] = a[0];
		for(int i=1;i<a.length;i++){
			if(b[inc] == a[i])
				continue;
			b[++inc] = a[i];
		}
		a = b;
	}
	/// <summary>  
    /// <b>奇偶排序</b>的思路是在数组中重复两趟扫描。  
    /// 第一趟扫描选择所有的数据项对，a[j]和a[j+1]，j是奇数(j=1, 3, 5……)。  
    /// 如果它们的关键字的值次序颠倒，就交换它们。  
    /// 第二趟扫描对所有的偶数数据项进行同样的操作(j=2, 4,6……)。  
    /// 重复进行这样两趟的排序直到数组全部有序。  
    ///   
    /// 平均时间复杂度:O(n^2)  
    /// Stability:Yes  
    /// </summary>  
	public void OddEvenSort()
    {  
        boolean sorted = false;  
        while (!sorted)  
        {  
            sorted = true;  
            // odd-even  
            for (int i = 1; i < a.length - 1; i += 2)  
            {  
                if (a[i]>a[i+1])  
                {  
                    this.swap3(i, i+1);
                    sorted = false;  
                }  
            }  
            // even-odd  
            for (int j = 0; j < a.length - 1; j += 2)  
            {  
                if (a[j]>a[j+1])  
                {  
                    this.swap3(j, j+1);
                    sorted = false;  
                }  
            }  
        }  
    }  
	
	public static void main(String[] args) {
		int maxSize = 10;
		ArraySort arr;
		arr = new ArraySort(maxSize);
		
		//随机插入maxSize个数
		for(int i=0;i<maxSize;i++){
			long n = (long)(Math.random()*(maxSize-1));
			arr.insert(n);
		}
		//插入逆序的数字
//		for(int i=0;i<maxSize;i++){
//			long n = (99999-i);
//			arr.insert(n);
//		}
		
		arr.display();
		long tick1 = System.currentTimeMillis();
//		arr.bubbleSort();
		arr.selectionSort();
		arr.noDups();
//		arr.selectionSort1();
//		arr.insertionSort();
		System.out.println(System.currentTimeMillis()-tick1);
		arr.display();
		 
	}
	
	private void swap(int one,int two){
		Long temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}
	private void swap2(int one,int two){
		if(one == two)
			return;
		a[one] = a[one]+ a[two];
		a[two] = a[one]-a[two];
		a[one] = a[one] - a[two];
	}
	//异或的方法交换两个数字
	private void swap3(int one,int two){
		if(one == two)
			return;
		a[one] = a[one]^a[two];
		a[two] = a[one]^a[two];
		a[one] = a[one]^a[two];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
