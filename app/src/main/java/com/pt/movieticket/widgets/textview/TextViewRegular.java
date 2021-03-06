package com.pt.movieticket.widgets.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewRegular extends TextView {

    public TextViewRegular(Context context) {
        super(context);

        Typeface face = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_REGULAR);
        this.setTypeface(face);
    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_REGULAR);
        this.setTypeface(face);
    }

    public TextViewRegular(Context context, AttributeSet attrs,
                           int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_REGULAR);
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
