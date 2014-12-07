/**
 * Author : Vijay Kumar 
 */

package com.example.listviewp1;

import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.database.MyDatabase;

public class MainActivity extends Activity {

	EditText ed_one;
	Button btn_add, btn_delete_selected;
	ListView lv_main;
	ArrayList<Model> arraylist;
	MyAdapter adapter;

	// database related
	MyDatabase myDatabase_ob;
	SQLiteDatabase sqLiteDatabase_ob;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		myDatabase_ob = new MyDatabase(getApplicationContext());
		sqLiteDatabase_ob = this.openOrCreateDatabase(MyDatabase.databaseName,
				0, null);
		myDatabase_ob.onCreate(sqLiteDatabase_ob);

		arraylist.addAll(myDatabase_ob.fetch());

		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				myDatabase_ob.save(ed_one.getText().toString());

				//i clear the arraylist and add all new updated elements. 
				arraylist.clear();
				arraylist.addAll(myDatabase_ob.fetch());

				//now i update the listview
				adapter.notifyDataSetChanged();

				// this code reload or refresh the activity (below code is accurate)
				/*
				 * Intent intent = getIntent(); overridePendingTransition(0, 0);
				 * intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); finish();
				 * overridePendingTransition(0, 0); startActivity(intent);
				 */
				// -------------------------------

			}
		});

		adapter = new MyAdapter(getApplicationContext(), arraylist);

		lv_main.setAdapter(adapter);

	}

	// my methods
	public void init() {
		ed_one = (EditText) findViewById(R.id.ed_one);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_delete_selected = (Button) findViewById(R.id.btn_delete_selected);
		lv_main = (ListView) findViewById(R.id.lv_main);
		arraylist = new ArrayList<Model>();
	}
}
