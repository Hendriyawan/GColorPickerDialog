# GColorPickerDialog [![](https://jitpack.io/v/Hendriyawan/GColorPickerDialog.svg)](https://jitpack.io/#Hendriyawan/GColorPickerDialog)
simple android library for color picker

# Usage
to use this library, you must add the jitpack.io repository

``` repository {
	maven { url "https://jitpack.io" }
}
```

and add dependecy
``` dependecies {
	...
	implementation 'com.github.Hendriyawan:GColorPickerDialog:1.0'
}
```

# Example
``` puclic class MainActivity extends AppCompatActivity implements ColorPickerCallbak {
	@BindView(R.id.text_view_hello_world)
	TextView textViewHelloWorld;

	private int TAG_DIALOG = 1;
	
	@Override
	protected void onCreate(Bunde savedInstanceState){
	...
		int colorSaved = GColorPickerDialog.getColor(this, TAG_DIALOG);
		textViewHelloWorld.setTextColor(colorSaved);
	}
	@OnClick(R.id.button_show_dialog)
	public void showDialog(){
		GColorPickerDialog.show(this, TAG_DIALOG);
	}

	@Override
	public void onColorSelected(int colorValue){
		textViewHelloWorl.setTextColor(colorValue);
	}
}
```
