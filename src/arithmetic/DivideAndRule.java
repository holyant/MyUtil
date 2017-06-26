package arithmetic;

import java.util.Random;
//分治法
public class DivideAndRule {
	public static void PartionGet(int s,int e,int[] meter,MaxMinValue mmv){
		/* 参数:
		* s 当前分治段的开始下标
		* e 当前分治段的结束下标
		* meter 表的地址
		* max 存储当前搜索到的最大值
		* min 存储当前搜索到的最小值
		*/
		int i;
		if(e-s <= 1){ /* 获取局部解，并更新全局解 */
			if(meter[s] > meter[e]){
				if(meter[s] > mmv.getMax())
					mmv.setMax(meter[s]);
				if(meter[e] < mmv.getMin())
					mmv.setMin(meter[e]);
			}else{
				if(meter[e] > mmv.getMax())
					mmv.setMax(meter[e]);
				if(meter[s] < mmv.getMin())
					mmv.setMin(meter[s]);
			}
			return ;
		}
		i = s + (e-s)/2; /* 不是子问题继续分治,这里使用了二分，也可以是其它 */
		PartionGet(s,i,meter,mmv);
		PartionGet(i+1,e,meter,mmv);
	}
		
		
	public static void main(String[] args) {
		int i;
		int meter[] = new int[10];
		MaxMinValue mmv = new MaxMinValue(Integer.MIN_VALUE,Integer.MAX_VALUE);
		System.out.println("The array's element as followed:");
		Random r = new Random(Integer.MAX_VALUE);
		for(i = 0; i < meter.length; i ++){ /* 随机数据填充数组 */
			meter[i] = r.nextInt();
			if(((i+1)%10==0)){/* 输出表的随机数据 */
				System.out.println(meter[i]);
			}else{
				System.out.print(meter[i]+",");
			}
		}
		DivideAndRule.PartionGet(0,meter.length - 1,meter,mmv); /* 分治法获取最值 */
		System.out.println("max:"+mmv.getMax()+",min:"+mmv.getMin());
	}
	
}

//内部类
 class MaxMinValue{
	private int max;
	private int min;
	
	public MaxMinValue(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	
}