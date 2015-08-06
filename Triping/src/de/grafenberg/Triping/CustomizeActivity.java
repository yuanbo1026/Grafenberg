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
        start.setOnClickListener(this);
        end.setOnClickListener(this);
        confirm.setOnClickListener(this);
        old.setOnClickListener(this);
        child.setOnClickListener(this);
        friend.setOnClickListener(this);
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
                isWithOld =old.isChecked();
                return;
            case R.id.companion_child:
                isWithChild = child.isChecked();
                return;
            case R.id.companion_friend:
                isWithFriend = friend.isChecked();
                return;
            default:
                return;
        }
    }

    /*private void checkedBox(int id) {
        switch (id) {
            case R.id.companion_old:
                isWithOld =old.isChecked();
                return;
            case R.id.companion_child:
                isWithChild = true;
                return;
            case R.id.companion_friend:
                isWithFriend = true;
                return;
            default:
                return;
        }
    }*/

    private void pickStartDate() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        start.setText("�?�程：\n" + year + "-" + (monthOfYear + 1)
                                + "-" + dayOfMonth);
                    }
                }
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();


    }

    private void confirm() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage
                ("您选择的国家是：\n"
                        + country.getText().toString() + " \n"
                        + "与 "
                        + (isWithChild ? " 孩�? " : "")
                        + (isWithOld ? " �?人 " : "")
                        + (isWithFriend ? " 朋�?� " : "")
                        + " �?�行" + "\n"
                        + start.getText().toString() + "\n"
                        + end.getText().toString() + "\n");

        alertDialogBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
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
                        end.setText("返程：\n" + year + "-" + (monthOfYear + 1)
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

        dialog.show();
    }
}