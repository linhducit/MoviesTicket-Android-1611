package com.pt.movieticket.widgets.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewBoldItalic extends TextView {

    public TextViewBoldItalic(Context context) {
        super(context);

        Typeface face = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_BOLT_ITALIC);
        this.setTypeface(face);
    }

    public TextViewBoldItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_BOLT_ITALIC);
        this.setTypeface(face);
    }

    public TextViewBoldItalic(Context context, AttributeSet attrs,
                              int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), TextFontConfig.FONT_BOLT_ITALIC);
        this.setTypeface(face);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
