package com.baluche.util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public final class SortUtils {

    private SortUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 将需要传送的数据进行排序
     *
     * @param strs
     * @return
     */
    public static ArrayList<String> sortASCII(String[] strs) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < strs.length; i++) {
            //第一个字符的ascii码大，放前面
            for (int j = i + 1; j < strs.length; j++) {
                int num = 0;
                compare(strs, i, j, num);
            }

        }
        for (int i = 0; i < strs.length; i++) {
            list.add(strs[i]);

        }
        Collections.reverse(list);

        for (int i = 0; i < list.size(); i++) {
            System.err.println(list.get(i));
        }
        return list;
    }

    /**
     * @param strs
     * @param i
     * @param num
     */
    private static void compare(String[] strs, int i, int j, int num) {
        //判断2个字符串谁的长度最小，则以当前长度作为num+1的最大标准
        if (strs[i].length() >= strs[j].length()) {
            if (num + 1 <= strs[j].length()) {
                if (strs[j].charAt(num) > strs[i].charAt(num)) {
                    String temp = strs[i];
                    strs[i] = strs[j];
                    strs[j] = temp;
                    //若相等，则判断第二个
                } else if (strs[j].charAt(num) == strs[i].charAt(num)) {
                    num++;
                    compare(strs, i, j, num);
                }
            }
        } else {
            if (num + 1 <= strs[i].length()) {
                if (strs[j].charAt(num) > strs[i].charAt(num)) {
                    String temp = strs[i];
                    strs[i] = strs[j];
                    strs[j] = temp;
                    //若相等，则判断第二个
                } else if (strs[j].charAt(num) == strs[i].charAt(num)) {
                    num++;
                    compare(strs, i, j, num);
                }
            } else {
                //表示当前字符串内容都一致，strs[j]的长度大。 则放前面。
                String temp = strs[i];
                strs[i] = strs[j];
                strs[j] = temp;
            }
        }
    }
}
