package de.grafenberg.Triping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity implements View.OnClickListener {

	private Button custom;
	private Button highlight;
	private Button mine;
	private Button offer;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		custom = (Button) findViewById(R.id.button_customize);
		highlight = (Button) findViewById(R.id.button_highlight);
		mine = (Button) findViewById(R.id.button_mine);
		offer = (Button) findViewById(R.id.button_offer);
		custom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent custom = new Intent(HomeActivity.this, CustomizeActivity.class);
				startActivity(custom);
			}
		});
		highlight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent highlight = new Intent(HomeActivity.this, HighlightActivity
						.class);
				startActivity(highlight);
			}
		});
		mine.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mine = new Intent(HomeActivity.this, MineActivity.class);
				startActivity(mine);
			}
		});
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.button_customize:
				Intent custom = new Intent(this, CustomizeActivity.class);
				startActivity(custom);
				return;
			case R.id.button_highlight:
				Intent highlight = new Intent(this, HighlightActivity.class);
				startActivity(highlight);
				return;
			case R.id.button_mine:
				Intent mine = new Intent(this, MineActivity.class);

				return;
			case R.id.button_offer:
				return;
			default:
				return;

		}

	}
}
