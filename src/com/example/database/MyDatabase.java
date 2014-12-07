package com.example.database;

import java.util.ArrayList;

import com.example.listviewp1.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

	public static String databaseName = "PowerListDatabase";
	SQLiteDatabase sqLiteDatabase_ob;

	public String tableName = "MyTable";

	// fields
	public String idField = "ID";
	public String field1 = "Name";

	public MyDatabase(Context context) {
		super(context, databaseName, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String type = " TEXT";

		String sql = "create table if not exists " + tableName + "(" + idField
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + field1 + type + ")";

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

	public void save(String val1) {

		sqLiteDatabase_ob = this.getWritableDatabase();

		ContentValues contentValues_ob = new ContentValues();

		contentValues_ob.put(field1, val1);

		sqLiteDatabase_ob.insert(tableName, null, contentValues_ob);

		sqLiteDatabase_ob.close();

	}

	public ArrayList<Model> fetch() {

		ArrayList<Model> arraylist = new ArrayList<Model>();

		String sql = "SELECT * FROM " + tableName;
		sqLiteDatabase_ob = this.getReadableDatabase();

		Cursor resultSet = sqLiteDatabase_ob.rawQuery(sql, null);

		if ((resultSet.moveToFirst())) {

			do {

				/*
				 * Model is a class for storing my data in object. this is call
				 * model, bean or getter setter.
				 */
				arraylist.add(new Model(resultSet.getString(0), resultSet
						.getString(1)));

			} while (resultSet.moveToNext());
		}

		return arraylist;
	}

	public void delete(String row_id) {
		sqLiteDatabase_ob = this.getWritableDatabase();

		sqLiteDatabase_ob.delete(tableName, idField + " = ? ",
				new String[] { row_id });

		sqLiteDatabase_ob.close();
	}
}
