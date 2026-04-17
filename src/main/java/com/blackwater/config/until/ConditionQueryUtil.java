package com.blackwater.config.until;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConditionQueryUtil {


    public static List<String> permute(String str) {
        List<String> result = new ArrayList<>();
        permuteHelper(str.toCharArray(), 0, result);
        return result;
    }

    private static void permuteHelper(char[] arr, int index, List<String> result) {
        if (index == arr.length - 1) { // 当前索引达到最后一个字符
            result.add(new String(arr));
        } else {
            for (int i = index; i < arr.length; i++) {
                swap(arr, index, i);  // 交换字符
                permuteHelper(arr, index + 1, result); // 递归处理下一个位置的字符
                swap(arr, index, i); // 恢复字符交换前的顺序
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public List<String> getAllString(String s) {
        return permute(s);
    }

}
