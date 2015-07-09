package team.syww.petroleumPiplinePatrol.MainActivity;

import team.syww.petroleumPiplinePatrol.Camera.CameraScreenActivity;
import team.syww.petroleumPiplinePatrol.ImageProcessing.ImageProcessingActivity;
import team.syww.petroleumPiplinePatrol.Map.MapClothActivity;
import team.syww.petroleumPiplinePatrol.Settings.SettingsActivity;

import com.etc.syww.petroleumPiplinePatrol.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_main);
		ibt_camera = (ImageButton) findViewById(R.id.ibt_camera);
		ibt_map = (ImageButton) findViewById(R.id.ibt_map);
		ibt_imageprocessing = (ImageButton) findViewById(R.id.ibt_imageprocessing);
		ibt_settings = (ImageButton) findViewById(R.id.ibt_settings);
		ibt_camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CameraScreenActivity.class);
				startActivity(intent);
				finish();
			}
		});
		ibt_map.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MapClothActivity.class);
				startActivity(intent);
				finish();
			}
		});
		ibt_imageprocessing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,
						ImageProcessingActivity.class);
				startActivity(intent);
				finish();
			}
		});
		ibt_settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SettingsActivity.class);
				startActivity(intent);
				finish();
			}
		});
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
