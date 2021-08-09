package com.giftedcat.justifylib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.giftedcat.justifylib.R;
import com.giftedcat.justifylib.utils.TextUtil;

public class JustifyTextView extends AppCompatTextView {

    /**
     * 字数
     */
    private int numberWord;
    /**
     * 额外不需要对齐的字符串
     */
    private String extraWords;

    /**
     * 实际的文本
     */
    private String realText;

    Canvas canvas;

    public JustifyTextView(Context context) {
        super(context);
    }

    public JustifyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustifyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.JustifyTextView, 0, 0);

        canvas = new Canvas();

        numberWord = a.getInteger(R.styleable.JustifyTextView_numberWord, -1);
        extraWords = a.getString(R.styleable.JustifyTextView_extraWords);
        if (extraWords == null)
            extraWords = "";

        if (numberWord != -1 && numberWord != 0) {
            setText(getText().toString());
        }
    }

    public void setText(String text) {
        realText = text;
        super.setText(realText);
        TextUtil.justifyString(this, numberWord);
        append(extraWords);
    }

    public String getRealText() {
        return realText;
    }

    public void setNumberWord(int numberWord) {
        this.numberWord = numberWord;
        setText(realText);
    }

    public int getNumberWord() {
        return numberWord;
    }

    public void setExtraWords(String extraWords) {
        this.extraWords = extraWords;
        setText(realText);
    }

    public String getExtraWords() {
        return extraWords;
    }

}
