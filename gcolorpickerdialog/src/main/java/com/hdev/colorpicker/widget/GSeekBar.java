package com.hdev.colorpicker.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.util.AttributeSet;

import com.hdev.colorpicker.R;

public class GSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    private int progressColor;

    public GSeekBar(Context context) {
        super(context);
    }

    public GSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GSeekBar, 0, 0);
        progressColor = typedArray.getInt(R.styleable.GSeekBar_progressColor, 0);
        if (progressColor != 0) {
            getProgressDrawable().setColorFilter(progressColor, PorterDuff.Mode.SRC_IN);
            getThumb().setColorFilter(progressColor, PorterDuff.Mode.SRC_IN);
        }
    }
}
