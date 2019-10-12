# GColorPickerDialog [![](https://jitpack.io/v/Hendriyawan/GColorPickerDialog.svg)](https://jitpack.io/#Hendriyawan/GColorPickerDialog)
simple android library for color picker
* update 2.0 mode landscape
<img src="https://raw.githubusercontent.com/Hendriyawan/GColorPickerDialog/master/ss_1.png" width="250"/>
<img src="https://raw.githubusercontent.com/Hendriyawan/GColorPickerDialog/master/ss_2.png" width="250"/>
<img src="https://raw.githubusercontent.com/Hendriyawan/GColorPickerDialog/master/ss_3.png" width="250"/>
<img src="https://raw.githubusercontent.com/Hendriyawan/GColorPickerDialog/master/ss_4.png" width="250"/>

# Usage
to use this library, you must add the jitpack.io repository
* require support-appcompat-v7
```
repository {
	maven { url "https://jitpack.io" }
}
```

and add dependecy
```
dependecies {
	...
	implementation 'com.github.Hendriyawan:GColorPickerDialog:1.0'
}
```

# Example
```
puclic class MainActivity extends AppCompatActivity implements ColorPickerCallbak {
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
		textViewHelloWorld.setTextColor(colorValue);
	}
}
```
