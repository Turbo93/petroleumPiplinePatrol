package team.syww.petroleumPiplinePatrol.Camera;

import team.syww.petroleumPiplinePatrol.MainActivity.MainActivity;

import com.etc.syww.petroleumPiplinePatrol.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CameraScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_screen);
		ibt_return = (ImageButton) findViewById(R.id.ibt_return);
		ibt_return.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(CameraScreenActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera_screen, menu);
		return true;
	}

	private ImageButton ibt_return;
}
