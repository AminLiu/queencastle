package com.queencastle.dao.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 中文字符串转为对应的拼音简拼和全拼工具类
 * 
 * @author YuDongwei
 *
 */
public class PinYinUtils {
    private static Logger logger = LoggerFactory.getLogger(PinYinUtils.class);

    public static void main(String[] args) {
        System.out.println(simplePinyin("系统管理员"));
        System.out.println(fullPinyin("系统管理员"));
        System.out.println(simplePinyin("于东伟"));
        System.out.println(fullPinyin("于东伟"));

    }

    /**
     * 根据输入的中文字符，输出汉语拼音的首字母
     * 
     * @param chinese
     * @return
     */
    public static String simplePinyin(String chinese) {
        StringBuffer buffer = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (_t != null) {
                        buffer.append(_t[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    logger.error(e.getMessage());
                }
            } else {
                buffer.append(arr[i]);
            }
        }
        return buffer.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 
     * @param chinese
     * @return
     */
    public static String fullPinyin(String chinese) {
        StringBuffer buffer = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    buffer.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    logger.error(e.getMessage());
                }
            } else {
                buffer.append(arr[i]);
            }
        }
        return buffer.toString();
    }
}
