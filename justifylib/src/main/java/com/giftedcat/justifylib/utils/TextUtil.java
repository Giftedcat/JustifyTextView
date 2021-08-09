package com.giftedcat.justifylib.utils;

import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.ScaleXSpan;
import android.widget.TextView;

public class TextUtil {

    /**
     * 将给定的字符串给定的长度两端对齐
     *
     * @param textView
     * @param numberWord 汉字个数，eg:size=5，则将str在5个汉字的长度里两端对齐
     * @Return
     */
    public static void justifyString(TextView textView, int numberWord) {

        /** 使用透明的 drawable 来填充空格部分*/
        Drawable drawable = new ColorDrawable(0x00ffffff);
        Drawable drawableCompletion = new ColorDrawable(0x00ffffff);
        SpannableString space = new SpannableString(" ");
        SpannableString spaceCompletion = new SpannableString(" ");

        String str = textView.getText().toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        char[] chars = str.trim().toCharArray();
        /** 需要达到的总长度*/
        float needTextSize = numberWord * textView.getTextSize();
        /** 已有的长度*/
        float realTextSize = 0;
        for (int i = 0; i < chars.length; i++) {
            realTextSize += textView.getPaint().measureText(String.valueOf(chars[i]));
        }
        if (realTextSize >= needTextSize) {
            return;
        }
        int l = chars.length;
        /** 计算字符间隔的长度*/
        float scale = (needTextSize - realTextSize) / (float) (l - 1);
        int completion = (int) needTextSize - ((int) scale * (l - 1) + (int) realTextSize);
        for (int i = 0; i < l; i++) {
            spannableStringBuilder.append(chars[i]);
            if (i != l - 1) {
                if (i == 0) {
                    drawableCompletion.setBounds(0, 0, completion + (int) scale, 0);
                    spaceCompletion.setSpan(new ImageSpan(drawableCompletion), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableStringBuilder.append(spaceCompletion);
                } else {
                    drawable.setBounds(0, 0, (int) scale, 0);
                    space.setSpan(new ImageSpan(drawable), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableStringBuilder.append(space);
                }
            }
        }
        textView.setText(spannableStringBuilder);
    }
} 