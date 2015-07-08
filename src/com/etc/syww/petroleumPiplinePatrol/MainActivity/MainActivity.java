package com.etc.syww.petroleumPiplinePatrol.MainActivity;

import com.etc.syww.petroleumPiplinePatrol.R;
import com.etc.syww.petroleumPiplinePatrol.Camera.CameraScreen;
import com.etc.syww.petroleumPiplinePatrol.Map.MapCloth;
import com.etc.syww.petroleumPiplinePatrol.Settings.Settings;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ibt_camera = (ImageButton) findViewById(R.id.ibt_camera);
		ibt_map = (ImageButton) findViewById(R.id.ibt_map);
		ibt_imageprocessing = (ImageButton) findViewById(R.id.ibt_imageprocessing);
		ibt_settings = (ImageButton) findViewById(R.id.ibt_settings);
		ibt_camera.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CameraScreen.class);
				startActivity(intent);
				finish();
			}});
		ibt_map.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MapCloth.class);
				startActivity(intent);
				finish();
			}});
		ibt_imageprocessing.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}});
		ibt_settings.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Settings.class);
				startActivity(intent);
				finish();
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		System.out.println("aaaaaaaa");
		return true;
	}
	private ImageButton ibt_camera;
	private ImageButton ibt_map;
	private ImageButton ibt_imageprocessing;
	private ImageButton ibt_settings;

}
