# JustifyTextView
左右对齐的TextView，适配各种分辨率，完美实现UI需求

# 一、前言

我们在开发中经常会碰到设计师给出的ui图，上下两个字数不一样的文本框一样长，中间用空格填充了。

设计师是为了整张设计图的美观使用空格填充了文本框使之左右对齐，但是对于开发来说需要考虑到文本框的内容可能会有所变动，字母和汉字占位不一样，还有分辨率不同的情况，所以使用空格填充的方案显然不是非常完美

# 二、效果图

![效果图](https://upload-images.jianshu.io/upload_images/20395467-c14ec085d25da60f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 三、实现原理

如果是纯汉字的话，实现两端对齐确实可以使用空格就可以

但是除了汉字如果有其他字符如英文不同的字母宽度不一样

便会导致使用固定空格填充会出现宽度对不上的情况

![不同字母宽度](https://upload-images.jianshu.io/upload_images/20395467-ed4f1d6daa98fdcf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

于是我们要使用需要字符宽度减去已有所有字符宽度

然后再分配到每两个字符中间

```
        char[] chars = str.trim().toCharArray();
        /** 需要达到的总长度*/
        float needTextSize = numberWord * textView.getTextSize();
        /** 已有的长度*/
        float realTextSize = 0;
        for (int i = 0; i < chars.length; i++) {
            realTextSize += textView.getPaint().measureText(String.valueOf(chars[i]));
        }
        int l = chars.length;
        /** 计算字符间隔的长度*/
        float scale = (needTextSize - realTextSize) / (float) (l - 1);
```

# 四、如何使用

#### （一）添加库

```
maven { url "https://jitpack.io" }

```
```
implementation 'com.github.Giftedcat:JustifyTextView:1.0.0'
```

（二）在xml中使用
**numberWord：需要达到的汉字字数**
**extraWords：额外不需要对齐的字符串**
***注意 如果添加了ExtraWords，请使用getRealText函数来获取文本框的内容**
```
    <com.giftedcat.justifylib.view.JustifyTextView
        android:id="@+id/tv_titl5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello世界"
        android:textSize="20sp"
        app:extraWords=":"
        app:numberWord="11" />

    <com.giftedcat.justifylib.view.JustifyTextView
        android:id="@+id/tv_titl6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="你好World"
        android:textSize="20sp"
        app:extraWords=":"
        app:numberWord="11" />
```

（三）在代码中使用
```
        tvTitle1.setText("你好");
        tvTitle1.setExtraWords(":");
        tvTitle1.setNumberWord(11);
```

