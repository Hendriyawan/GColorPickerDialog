package com.hdev.colorpicker.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorPreferences {
    private static SharedPreferences sharedPreferences;
    private static ColorPreferences instance;
    private static SharedPreferences.Editor editor;

    public static void saveValue(Context context, int TAG, int color, int a, int r, int g, int b) {
        sharedPreferences = context.getSharedPreferences(String.valueOf(TAG), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("color", color);
        editor.putInt("progress_a", a);
        editor.putInt("progress_r", r);
        editor.putInt("progress_g", g);
        editor.putInt("progress_b", b);
        editor.putString("hex_a", String.valueOf(Integer.toHexString(a)));
        editor.putString("hex_r", String.valueOf(Integer.toHexString(r)));
        editor.putString("hex_g", String.valueOf(Integer.toHexString(g)));
        editor.putString("hex_b", String.valueOf(Integer.toHexString(b)));
        editor.apply();
    }

    public static void toValue(Context context, int TAG, SeekBar seekBarA, SeekBar seekBarR, SeekBar seekBarG, SeekBar seekBarB, TextView textViewHexA, TextView textViewHexR, TextView textViewHexG, TextView textViewHexB, View colorView) {
        sharedPreferences = context.getSharedPreferences(String.valueOf(TAG), Context.MODE_PRIVATE);
        int color = sharedPreferences.getInt("color", -1);
        int progress_a = sharedPreferences.getInt("progress_a", 0);
        int progress_r = sharedPreferences.getInt("progress_r", 0);
        int progress_g = sharedPreferences.getInt("progress_g", 0);
        int progress_b = sharedPreferences.getInt("progress_b", 0);

        String hex_a = sharedPreferences.getString("hex_a", "ff");
        String hex_r = sharedPreferences.getString("hex_r", "ff");
        String hex_g = sharedPreferences.getString("hex_g", "ff");
        String hex_b = sharedPreferences.getString("hex_b", "ff");

        colorView.setBackgroundColor(color);

        seekBarA.setProgress(progress_a);
        seekBarR.setProgress(progress_r);
        seekBarG.setProgress(progress_g);
        seekBarB.setProgress(progress_b);

        textViewHexA.setText(hex_a);
        textViewHexR.setText(hex_r);
        textViewHexG.setText(hex_g);
        textViewHexB.setText(hex_b);
    }

    public static int getColor(Context context, int TAG) {
        sharedPreferences = context.getSharedPreferences(String.valueOf(TAG), Context.MODE_PRIVATE);
        return sharedPreferences.getInt("color", -1);
    }
}
