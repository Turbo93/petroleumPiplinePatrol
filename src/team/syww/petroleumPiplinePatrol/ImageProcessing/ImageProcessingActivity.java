package team.syww.petroleumPiplinePatrol.ImageProcessing;

import com.etc.syww.petroleumPiplinePatrol.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ImageProcessingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_processing);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_processing, menu);
		return true;
	}

}
