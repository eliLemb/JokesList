package com.example.jokeslist;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditJoke extends Activity {

	long id;
	HashMap<String,String> hashmap;
	
	public SharedPreferences.Editor editor;
	
	RelativeLayout layout;
	SharedPreferences pref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_joke);
		Intent intent = getIntent();
		id = intent.getLongExtra(MainActivity.EXTRA_MESSAGE,-1);
		hashmap = MainActivity.jokes.get((int)id);
		EditText editText = (EditText) findViewById(R.id.joke_edit);
		editText.setText(hashmap.get("Joke")); 
		
		layout = (RelativeLayout) findViewById(R.id.edit_activity_layout);
		
		if(editor == null)
			editor = getSharedPreferences("com.example.jokeslist", 0).edit();
		if(pref == null)
			pref = getSharedPreferences("com.example.jokeslist", 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_joke, menu);
		return true;
	}

	public void likeClick(View view){
		
		hashmap.put("Mood", Integer.toString(R.drawable.like_icon)); //set the icon string
		doneClick(view);

	}
	public void dislikeClick(View view){
		
		hashmap.put("Mood", Integer.toString(R.drawable.dislike)); //set the icon string
		doneClick(view);

	}	
	public void delClick(View view){
		
		MainActivity.jokes.remove((int)id); //delete the selected entry
		doneClick(view);
	}	
	
	public void doneClick(View view){
		TextView editText = (TextView) findViewById(R.id.joke_edit);
		hashmap.put("Joke", editText.getText().toString()); //Edit the existing  entry and update it with the new joke
		finish();
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.edit_activity_layout);;
		switch (item.getItemId()) {
		case R.id.background_change_activity3:
			//			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();

			return true;
		case R.id.exit_activity3:
			//			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

		    builder.setTitle("Confirm");
		    builder.setMessage("Are you sure?");

		    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

		        public void onClick(DialogInterface dialog, int which) {
		            // Do nothing but close the dialog
		        	editor.putBoolean("toQuit", true);
					editor.commit();
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
		case R.id.bg_blue_a3:
			Toast.makeText(EditJoke.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.AliceBlue));
			editor.putBoolean("isColorSet_a3", true);
			editor.putInt("color", getResources().getColor(R.color.AliceBlue));
			editor.commit();
			return true;
		case R.id.bg_red_a3:
			Toast.makeText(EditJoke.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.MistyRose));
			editor.putBoolean("isColorSet_a3", true);
			editor.putInt("color", getResources().getColor(R.color.MistyRose));
			editor.commit();
			return true;
		case R.id.bg_green_a3:
			Toast.makeText(EditJoke.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.LightSeaGreen));
			editor.putBoolean("isColorSet_a3", true);
			editor.putInt("color", getResources().getColor(R.color.LightSeaGreen));
			editor.commit();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/*Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra(MainActivity.EXTRA_MESSAGE,"quit");
		startActivity(intent);*/
	}
		
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		checkPreviousColor();
		super.onResume();
	}
	
	private void checkPreviousColor()
	{
		int oldColor;
		if (pref.getBoolean("isColorSet_a3", false)) //check if a color was already set
		{
			oldColor = pref.getInt("color", 0);
			layout.setBackgroundColor(oldColor);
		}
		
	}
}
