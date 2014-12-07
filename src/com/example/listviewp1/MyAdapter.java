package com.example.listviewp1;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.database.MyDatabase;

public class MyAdapter extends ArrayAdapter<Model> implements Interface4List {

	List<Model> list;
	private LayoutInflater inflater;

	MyDatabase myDatabase_ob;
	SQLiteDatabase sqLiteDatabase_ob;

	public MyAdapter(Context context, List<Model> objects_list) {
		super(context, R.layout.my_cutomview, objects_list);

		list = objects_list;

		// init
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		myDatabase_ob = new MyDatabase(getContext());
		sqLiteDatabase_ob = getContext().openOrCreateDatabase(
				MyDatabase.databaseName, 0, null);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		MyCustomView myCustomView_ob = null;

		if (convertView == null) {

			myCustomView_ob = (MyCustomView) inflater.inflate(
					R.layout.my_cutomview, null);
		}

		else {
			myCustomView_ob = (MyCustomView) convertView;
		}

		myCustomView_ob.rowContent(list.get(position), position);

		myCustomView_ob.setOnClickListoner_vc(this);

		return myCustomView_ob;
	}

	// this mehtods is define in interface
	@Override
	public void myClick(int positon, int globel_id) {

		// i deleting one row from database table
		myDatabase_ob.delete(globel_id + "");

		// i deleting row from list
		list.remove(positon);

		notifyDataSetChanged();

	}

}
