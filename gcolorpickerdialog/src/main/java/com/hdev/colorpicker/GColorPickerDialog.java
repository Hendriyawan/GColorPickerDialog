package com.hdev.colorpicker;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hdev.colorpicker.callback.ColorPickerCallback;
import com.hdev.colorpicker.util.ColorPreferences;
import com.hdev.colorpicker.util.Utils;
import com.hdev.colorpicker.widget.GSeekBar;


public class GColorPickerDialog extends DialogFragment implements View.OnLongClickListener {
    private GSeekBar seekBarAlpha; //A
    private GSeekBar seekBarRed; //R
    private GSeekBar seekBarGreen; //G
    private GSeekBar seekBarBlue; //B
    private TextView textViewHexAlpha; //HEX  A
    private TextView textViewHexRed; //HEX R
    private TextView textViewHexGreen; //HEX G
    private TextView textViewHexBlue; //HEX B
    private View colorView;
    private int TAG;
    private ColorPickerCallback callback;

    public GColorPickerDialog() {
    }

    public static void show(AppCompatActivity activity, int TAG) {
        GColorPickerDialog dialog = new GColorPickerDialog();
        dialog.show(activity.getSupportFragmentManager(), String.valueOf(TAG));
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        callback = (ColorPickerCallback) getActivity();

        if (getTag() == null) {
            TAG = 1;
        }
        TAG = Integer.valueOf(getTag());

        seekBarRed = view.findViewById(R.id.seekbar_r);
        seekBarGreen = view.findViewById(R.id.seekbar_g);
        seekBarBlue = view.findViewById(R.id.seekbar_b);
        seekBarAlpha = view.findViewById(R.id.seekbar_a);
        textViewHexRed = view.findViewById(R.id.text_view_r);
        textViewHexGreen = view.findViewById(R.id.text_view_g);
        textViewHexBlue = view.findViewById(R.id.text_view_b);
        textViewHexAlpha = view.findViewById(R.id.text_view_a);
        //ColorView
        colorView = view.findViewById(R.id.color_view);
        colorView.setOnLongClickListener(this);

        ColorPreferences.toValue(getActivity(), TAG,
                seekBarAlpha,
                seekBarRed,
                seekBarGreen,
                seekBarBlue,
                textViewHexAlpha,
                textViewHexRed,
                textViewHexGreen,
                textViewHexBlue,
                colorView
        );
        //SeekBar Listener
        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int A = seekBarAlpha.getProgress();
                int R = seekBarRed.getProgress();
                int G = seekBarGreen.getProgress();
                int B = seekBarBlue.getProgress();
                int color = Color.argb(A, R, G, B);
                String HEX_A = Utils.getHexString(seekBarAlpha);
                String HEX_R = Utils.getHexString(seekBarRed);
                String HEX_G = Utils.getHexString(seekBarGreen);
                String HEX_B = Utils.getHexString(seekBarBlue);

                colorView.setBackgroundColor(color);
                textViewHexAlpha.setText(HEX_A);
                textViewHexRed.setText(HEX_R);
                textViewHexGreen.setText(HEX_G);
                textViewHexBlue.setText(HEX_B);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        seekBarRed.setOnSeekBarChangeListener(listener);
        seekBarGreen.setOnSeekBarChangeListener(listener);
        seekBarBlue.setOnSeekBarChangeListener(listener);
        seekBarAlpha.setOnSeekBarChangeListener(listener);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {

            String HEX_A = Utils.getHexString(seekBarAlpha);
            String HEX_R = Utils.getHexString(seekBarRed);
            String HEX_G = Utils.getHexString(seekBarGreen);
            String HEX_B = Utils.getHexString(seekBarBlue);
            int A = seekBarAlpha.getProgress();
            int R = seekBarRed.getProgress();
            int G = seekBarGreen.getProgress();
            int B = seekBarBlue.getProgress();
            int color = Color.argb(A, R, G, B);

            textViewHexAlpha.setText(HEX_A);
            textViewHexRed.setText(HEX_R);
            textViewHexGreen.setText(HEX_G);
            textViewHexBlue.setText(HEX_B);

            colorView.setBackgroundColor(color);
            ColorPreferences.saveValue(getActivity(), TAG, color, A, R, G, B);
            callback.onColorSelected(color);
            dismiss();

        });

        builder.setNegativeButton(android.R.string.no, ((dialog, which) -> {
            dismiss();
        }));
        return builder.create();
    }

    public static int getColor(Context context, int TAG) {
        return ColorPreferences.getColor(context, TAG);
    }

    @Override
    public boolean onLongClick(View v) {
        int x = colorView.getWidth() / 2;
        int y = colorView.getHeight() / 2;
        int endRadius = Math.max(colorView.getWidth(), colorView.getHeight());
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(colorView, x, y, 0, endRadius);
        circularReveal.start();
        int color = Color.argb(seekBarAlpha.getProgress(), seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
        setClipboard(getActivity(), Integer.toHexString(color));
        return true;
    }

    //setClipboard
    public void setClipboard(Context context, String value) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied ! ", " #" + value);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity(), "copied to clipboard ! " + "#" + value, Toast.LENGTH_SHORT).show();

    }
}
