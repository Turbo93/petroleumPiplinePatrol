package team.syww.petroleumPiplinePatrol.Map;

import com.etc.syww.petroleumPiplinePatrol.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapClothActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_cloth);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_cloth, menu);
		return true;
	}

}
