package com.example.jokeslist;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewJoke extends Activity {

	RelativeLayout layout;
	SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_joke);
		EditText editText = (EditText) findViewById(R.id.joke);
		editText.requestFocus();
		layout = (RelativeLayout) findViewById(R.id.new_activity_layout);

		if(pref == null)
			pref = getSharedPreferences("com.example.jokeslist", 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_joke, menu);
		return true;
	}


	public void addJokeClick(View view){

//		HashMap<String,String> hashmap = new HashMap<String,String>();
		TextView editText = (TextView) findViewById(R.id.joke);

		if (editText.getText().toString().length() < 10){
			AlertDialog.Builder builder = new AlertDialog.Builder(NewJoke.this);
			builder.setTitle("Warning!")
			.setMessage("You wrote a joke shorter that is shorter then 10 letters.\r\nWrite a new one.")
			.setCancelable(false)
			.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		}
		else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Confirm");
			builder.setMessage("Are you sure you want to add this joke?");

			builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// Do nothing but close the dialog
					HashMap<String,String> hashmap = new HashMap<String,String>();
					TextView editText = (TextView) findViewById(R.id.joke);

					hashmap.put("Joke", editText.getText().toString());
					editText = (TextView) findViewById(R.id.author_input);
					hashmap.put("Author", editText.getText().toString());
					hashmap.put("Mood", Integer.toString(R.drawable.blank));
					MainActivity.jokes.add(hashmap);
					dialog.dismiss();
					finish();
				}

			});

			builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Do nothing
					dialog.dismiss();
				}
			});

			AlertDialog alert = builder.create();
			alert.show();
			
			
			
			
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.background_change_activity2:
			//			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

			return true;
		case R.id.exit_activity2:
			//			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Confirm");
			builder.setMessage("Are you sure?");

			builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// Do nothing but close the dialog
					MainActivity.editor.putBoolean("toQuit", true);
					MainActivity.editor.commit();
					dialog.dismiss();
					finish();
				}

			});

			builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Do nothing
					dialog.dismiss();
				}
			});

			AlertDialog alert = builder.create();
			alert.show();

			return true;
		case R.id.bg_blue_a2:
			Toast.makeText(NewJoke.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.AliceBlue));
			MainActivity.editor.putBoolean("isColorSet_a2", true);
			MainActivity.editor.putInt("color", getResources().getColor(R.color.AliceBlue));
			MainActivity.editor.commit();
			return true;
		case R.id.bg_red_a2:
			Toast.makeText(NewJoke.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.MistyRose));
			MainActivity.editor.putBoolean("isColorSet_a2", true);
			MainActivity.editor.putInt("color", getResources().getColor(R.color.MistyRose));
			MainActivity.editor.commit();
			return true;
		case R.id.bg_green_a2:
			Toast.makeText(NewJoke.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.LightSeaGreen));
			MainActivity.editor.putBoolean("isColorSet_a2", true);
			MainActivity.editor.putInt("color", getResources().getColor(R.color.LightSeaGreen));
			MainActivity.editor.commit();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkPreviousColor();
	}

	private void checkPreviousColor()
	{
		int oldColor;
		if (pref.getBoolean("isColorSet_a2", false)) //check if a color was already set
		{
			oldColor = pref.getInt("color", 0);
			layout.setBackgroundColor(oldColor);
		}

	}
}
