package de.grafenberg.Triping;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b.yuan on 05.08.2015.
 */
public class CustomizeActivity extends Activity implements View.OnClickListener {
	private Button country;
	private Button start;
	private Button end;
	private Button confirm;
	private CheckBox old;
	private CheckBox child;
	private CheckBox friend;

	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customize);
		context = this;
		country = (Button) findViewById(R.id.button_country);
		start = (Button) findViewById(R.id.button_start);
		end = (Button) findViewById(R.id.button_end);
		confirm = (Button) findViewById(R.id.button_confirm);
		old = (CheckBox) findViewById(R.id.companion_old);
		child = (CheckBox) findViewById(R.id.companion_child);
		friend = (CheckBox) findViewById(R.id.companion_friend);
		country.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.button_country:
				showDialog();
				return;
			case R.id.button_start:
				return;
			case R.id.button_end:
				return;
			case R.id.button_confirm:
				return;
			case R.id.companion_old:
				return;
			case R.id.companion_child:
				return;
			case R.id.companion_friend:
				return;
			default:
				return;
		}
	}

	private void showDialog() {

		// custom dialog
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.country_dialog);
		dialog.setTitle("Title...");

		List<Integer> mList = new ArrayList<Integer>();
		for (int i = 1; i < 36; i++) {
			mList.add(i);
		}

		GridView gridView = (GridView) dialog.findViewById(R.id.gridview);
		gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mList));
		gridView.setNumColumns(5);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				country.setText("");
			}
		});
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();

	}
}