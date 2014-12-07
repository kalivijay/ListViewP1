package com.example.listviewp1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyCustomView extends LinearLayout {

	TextView tv_one;
	ImageView iv_delete;
	Interface4List interface4List_ob;
	int globel_position, globel_id;

	public MyCustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyCustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		tv_one = (TextView) findViewById(R.id.tv_one);
		iv_delete = (ImageView) findViewById(R.id.iv_delete);
	}

	// myMehtod
	public void rowContent(Model model_ob, int position) {

		tv_one.setText(model_ob.data);

		globel_position = position;

		globel_id = Integer.parseInt(model_ob.id);

		iv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				interface4List_ob.myClick(globel_position, globel_id);
			}
		});

	}

	// this method called in adapter
	public void setOnClickListoner_vc(Interface4List interface4List_ob) {

		this.interface4List_ob = interface4List_ob;

	}

}
