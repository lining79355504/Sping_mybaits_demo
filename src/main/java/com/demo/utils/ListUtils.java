package com.demo.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author mort
 * @Description
 * @date 2021/4/16
 **/
public class ListUtils {


    //list分批次执行 还有异步分批次支持合并
    public static void batchSplit(String[] args) {
        List<Integer> tmp = genTestList();
        int i = 0;
        int endIndex;
        while (i < tmp.size()) {
            if (i + 2 > tmp.size()) {
                endIndex = tmp.size();
            } else {
                endIndex = i + 2;
            }
            List<Integer> subAccountIds = tmp.subList(i, endIndex);
            System.out.println("args = " + JSON.toJSON(subAccountIds));
            // do business logic
            i = endIndex;
        }
    }

    private static List<Integer> genTestList() {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        tmp.add(1);
        tmp.add(2);
        tmp.add(3);
        tmp.add(4);
        tmp.add(5);
        tmp.add(null);
        tmp.add(6);
        tmp.add(null);
        tmp.add(null);
        return tmp;
    }

    public static void main(String[] args) {
        List<Integer> testList = genTestList();
        testList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                if (null == o1) {
                    return 1;
                }
                if (null == o2) {
                    return -1;
                }

                return o1.compareTo(o2);
            }
        });

        sortNullToEnd(testList, "DESC", true, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(JSON.toJSON(testList));


    }


    // sort 排序 null位置设置
    public static <T> void sortNullToEnd(List<T> list, String sortType, boolean nullEnd, Comparator<T> comparator) {

        if ("ASC".equals(sortType)) {
            list.sort(new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    int o1NullReturn = 1;
                    int o2NullReturn = -1;
                    if (!nullEnd) {
                        o1NullReturn = -1;
                        o2NullReturn = 1;
                    }

                    if (null == o1) {
                        return o1NullReturn;
                    }
                    if (null == o2) {
                        return o2NullReturn;
                    }

                    return comparator.compare(o1, o2);
                }
            });
            return;
        }

        if ("DESC".equals(sortType)) {

            list.sort(new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {

                    int o1NullReturn = 1;
                    int o2NullReturn = -1;
                    if (!nullEnd) {
                        o1NullReturn = -1;
                        o2NullReturn = 1;
                    }

                    if (null == o1) {
                        return o1NullReturn;
                    }
                    if (null == o2) {
                        return o2NullReturn;
                    }

                    return comparator.compare(o2, o1);
                }
            });
            return;
        }
    }

}
