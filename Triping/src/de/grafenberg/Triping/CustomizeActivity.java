package de.grafenberg.Triping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import java.util.Calendar;

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

	private String countries;
	private boolean isWithOld;
	private boolean isWithChild;
	private boolean isWithFriend;

	private Spinner spinner_old;
	private Spinner spinner_child;
	private Spinner spinner_friend;
	private String[] counts = new String[]{"1", "2", "3", "4", "5"};

	private String old_count;
	private String child_count;
	private String friend_count;


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
		spinner_old = (Spinner) findViewById(R.id.spinner_old);
		spinner_child = (Spinner) findViewById(R.id.spinner_child);
		spinner_friend = (Spinner) findViewById(R.id.spinner_friend);

		country.setOnClickListener(this);
		start.setOnClickListener(this);
		end.setOnClickListener(this);
		confirm.setOnClickListener(this);
		old.setOnClickListener(this);
		child.setOnClickListener(this);
		friend.setOnClickListener(this);
		spinner_old.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String countText = context.getString(R.string.people_count);
				old_count = countText.replace("?", counts[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});
		spinner_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String countText = context.getString(R.string.people_count);
				child_count = countText.replace("?", counts[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		spinner_friend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String countText = context.getString(R.string.people_count);
				friend_count = countText.replace("?", counts[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.button_country:
				showDialog();
				return;
			case R.id.button_start:
				pickStartDate();
				return;
			case R.id.button_end:
				pickEndDate();
				return;
			case R.id.button_confirm:
				confirm();
				return;
			case R.id.companion_old:
				isWithOld = old.isChecked();
				spinner_old.setVisibility(isWithOld ? View.VISIBLE : View.INVISIBLE);
				return;
			case R.id.companion_child:
				isWithChild = child.isChecked();
				spinner_child.setVisibility(isWithChild ? View.VISIBLE : View.INVISIBLE);
				return;
			case R.id.companion_friend:
				isWithFriend = friend.isChecked();
				spinner_friend.setVisibility(isWithFriend ? View.VISIBLE : View.INVISIBLE);
				return;
			default:
				return;
		}
	}

	private void pickStartDate() {
		Calendar c = Calendar.getInstance();
		new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
										  int monthOfYear, int dayOfMonth) {
						start.setText(context.getString(R.string.customize_start)
								+ "\n" + year +
								"-" +
								(monthOfYear + 1)
								+ "-" + dayOfMonth);
					}
				}
				, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH)).show();


	}

	private void confirm() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage
				(context.getString(R.string.confirm_country_pick) + "\n"
						+ country.getText().toString() + " \n\n"
						+ context.getString(R.string.confirm_companion_with)
						+ (isWithChild ? context.getString(R.string
						.customize_companion_child) + child_count : "")
						+ (isWithOld ? context.getString(R.string
						.customize_companion_old)+old_count : "")
						+ (isWithFriend ? context.getString(R.string
						.customize_companion_friend) + friend_count : "")
						+ context.getString(R.string.confirm_companion_who) + "\n\n"
						+ start.getText().toString() + "\n"
						+ end.getText().toString() + "\n\n");

		alertDialogBuilder.setPositiveButton(context.getString(R.string
				.confirm_positive_button_text), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	private void pickEndDate() {
		Calendar c = Calendar.getInstance();
		new DatePickerDialog(context,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
										  int monthOfYear, int dayOfMonth) {
						end.setText(context.getString(R.string.customize_start) + "\n"
								+ year + "-" + (monthOfYear + 1)
								+ "-" + dayOfMonth);
					}
				}
				, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
				.get(Calendar.DAY_OF_MONTH)).show();
	}

	private void showDialog() {

		// custom dialog
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.country_dialog);
		final TextView tv = (TextView) dialog.findViewById(R.id.textview_show_country);

		final String[] names = context.getResources().getStringArray(R.array.country_name);

		GridView gridView = (GridView) dialog.findViewById(R.id.gridview);
		gridView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, names));
		gridView.setNumColumns(4);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				country.setText("");
				tv.setText(tv.getText().toString() + "  " + names[position]);
			}
		});
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView tv = (TextView) dialog.findViewById(R.id.textview_show_country);
				countries = tv.getText().toString();
				country.setText(countries);

				dialog.dismiss();
			}
		});
		Button cancelButton = (Button) dialog.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TextView tv = (TextView) dialog.findViewById(R.id.textview_show_country);
				tv.setText("");
			}
		});

		dialog.show();
	}
}