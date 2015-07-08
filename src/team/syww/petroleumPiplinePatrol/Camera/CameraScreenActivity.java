package team.syww.petroleumPiplinePatrol.Camera;

import com.etc.syww.petroleumPiplinePatrol.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CameraScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera_screen, menu);
		return true;
	}

}
