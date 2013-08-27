package com.example.jokeslist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	public final static String EXTRA_MESSAGE = "com.example.jokeslist.MESSAGE";

	public static ArrayList <HashMap<String,String>> jokes;

	public static SharedPreferences.Editor editor;

	SharedPreferences pref;
	SimpleAdapter adapter;
	TextView pick;
	LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layout = (LinearLayout) findViewById(R.id.main_activity_layout);
		//		Intent intent = getIntent();
		//		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		//		if(msg == "quit") finish();

		if (jokes == null)
			jokes = new ArrayList <HashMap<String,String>>();
		if(editor == null)
			editor = getSharedPreferences("com.example.jokeslist", 0).edit();
		if(pref == null)
			pref = getSharedPreferences("com.example.jokeslist", 0);



		adapter = new SimpleAdapter(this,
				jokes,
				R.layout.joke_item,
				new String[]{"Joke","Author","Mood"},
				new int[]{R.id.ListTextView01,R.id.ListTextView02,R.id.icon});  
		setListAdapter(adapter);
		registerForContextMenu(getListView());


	}



	private void checkPreviousColor()
	{
		int oldColor;
		if (pref.getBoolean("isColorSet_a1", false)) //check if a color was already set
		{
			oldColor = pref.getInt("color", 0);
			layout.setBackgroundColor(oldColor);
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.context_menu, menu);
	}
	
	
	public void addNew(View view) {

		Intent intent = new Intent(this, NewJoke.class);
		//String message = editText.getText().toString();
		startActivity(intent);
	}



	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, EditJoke.class);
		intent.putExtra(EXTRA_MESSAGE,id);
		startActivity(intent);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		checkPreviousColor();
		adapter.notifyDataSetChanged();
		if(pref.getBoolean("toQuit", false)){
			editor.putBoolean("toQuit", false);
			editor.commit();
			finish();
		}
	}



	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		AdapterContextMenuInfo selectedRow = (AdapterContextMenuInfo) item.getMenuInfo(); // To get the current selected item
		switch (item.getItemId()) {
        case R.id.context_del:
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            jokes.remove((int)selectedRow.id); //delete the selected entry
    		adapter.notifyDataSetChanged();
            return true;
        case R.id.context_edit:
        	Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        	Intent intent = new Intent(this, EditJoke.class);
    		intent.putExtra(EXTRA_MESSAGE,selectedRow.id);
    		startActivity(intent);
        	return true;
        default:
        	return super.onContextItemSelected(item);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.new_joke:
			//			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			addNew(null);
			return true;
		case R.id.background_change_activity1:
			//			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			return true;
		case R.id.exit_activity1:
			//			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Confirm");
			builder.setMessage("Are you sure?");

			builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// Do nothing but close the dialog
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
		case R.id.bg_blue_a1:
			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.AliceBlue));
			editor.putBoolean("isColorSet_a1", true);
			editor.putInt("color", getResources().getColor(R.color.AliceBlue));
			editor.commit();
			return true;
		case R.id.bg_red_a1:
			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.MistyRose));
			editor.putBoolean("isColorSet_a1", true);
			editor.putInt("color", getResources().getColor(R.color.MistyRose));
			editor.commit();
			return true;
		case R.id.bg_green_a1:
			Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			layout.setBackgroundColor(getResources().getColor(R.color.LightSeaGreen));
			editor.putBoolean("isColorSet_a1", true);
			editor.putInt("color", getResources().getColor(R.color.LightSeaGreen));
			editor.commit();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}


}
