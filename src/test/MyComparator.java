package test;

import java.util.Comparator;

public class MyComparator implements Comparator<String>{
	/**
     * 如果o1小于o2,返回一个负数;如果o1大于o2，返回一个正数;如果他们相等，则返回0;
     */
    public int compare(String o1, String o2) {
    	
        return o1.compareTo(o2);
    }
}
