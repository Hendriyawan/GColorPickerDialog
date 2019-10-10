package com.hdev.colorpicker.util;

import android.widget.SeekBar;

public class Utils {

    public static String getHexString(SeekBar seekbar) {
        return String.valueOf(Integer.toHexString(seekbar.getProgress()));
    }
}
