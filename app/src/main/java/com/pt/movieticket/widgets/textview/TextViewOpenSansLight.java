package com.pt.movieticket.widgets.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by TitTit on 11/01/2017.
 */

public class TextViewOpenSansLight extends TextView {
    public TextViewOpenSansLight(Context context) {
        super(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_OPEN_SANS_LIGHT);
        this.setTypeface(typeface);
    }

    public TextViewOpenSansLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_OPEN_SANS_LIGHT);
        this.setTypeface(typeface);
    }

    public TextViewOpenSansLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_OPEN_SANS_LIGHT);
        this.setTypeface(typeface);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
