package com.hdev.gcolorpicker.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hdev.colorpicker.GColorPickerDialog;
import com.hdev.colorpicker.callback.ColorPickerCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ColorPickerCallback {
    @BindView(R.id.text_view_hello_world)
    TextView textViewHelloWorld;
    private int TAG_DIALOG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        int colorSaved = GColorPickerDialog.getColor(this, TAG_DIALOG);
        textViewHelloWorld.setTextColor(colorSaved);
    }

    @OnClick(R.id.button_show_dialog)
    public void showDialog() {
        GColorPickerDialog.show(this, TAG_DIALOG);
    }

    @Override
    public void onColorSelected(int colorValue) {
        textViewHelloWorld.setTextColor(colorValue);
    }
}
