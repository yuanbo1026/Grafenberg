package de.grafenberg.Triping;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

/**
 * Created by b.yuan on 05.08.2015.
 */
public class MineActivity extends Activity {
	GridView mGridView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_trip);

		getWindow().setBackgroundDrawable(null);
		GridView view = (GridView) findViewById(R.id.grid);
		view.setAdapter(new GridAdapter(getBaseContext()));
		mGridView = view;
	}
}