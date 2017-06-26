package 亚信出账准备;
/**
 * 
 * @description 生成驼峰型表头
 * @author fangyh
 * @time Jul 1, 2014 9:17:17 AM
 */
public class 字符串2驼峰结构 {
	public static void main(String[] args) {
		String strs[] = { "IS_OLD","SVC_NUM","PROD_ID","ACCEPT_TIME","CHNL_TYPE","AGENT_1","AGENT_2"};
		 for(String str : strs) {
		 System.out.println(camel(str));
		 }
	}

	public static String camel(String str) {
		String returnStr = "";// 要返回的值
		str = str.toLowerCase();// 先把要转换的名字全转换为小写
		String[] arr = str.split("_");// 根据_来转换为数组
		returnStr += arr[0];// 第一组肯定是小写，先拼接
		for (int i = 1; i < arr.length; i++) {// 从第二级开始
			StringBuilder strbu = new StringBuilder(arr[i]);
			strbu.setCharAt(0, Character.toTitleCase(arr[i].charAt(0)));// 以上两句是把每一个单词的第一个字母大写
			returnStr += strbu.toString();// 拼接
		}
		return returnStr;// 返回
	}
}
