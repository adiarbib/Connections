package com.example.user.connections;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.Date;

public class SignUpActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

    Calendar cal;
    ImageButton profilePic;
    EditText mailEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    Button birthdateButt;
    Button countryButt;
    String formattedDate;
    SimpleDateFormat df;
    Date pickedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        profilePic = (ImageButton) findViewById(R.id.img);
        mailEditText = (EditText) findViewById(R.id.mail);
        usernameEditText = (EditText) findViewById(R.id.signup_username_input);
        passwordEditText = (EditText) findViewById(R.id.signup_password_input);
        birthdateButt = (Button) findViewById(R.id.birthdate);
        countryButt = (Button) findViewById(R.id.country);

        cal = Calendar.getInstance();
        df = new SimpleDateFormat("dd - MMM - yyyy");
        formattedDate = df.format(cal.getTime());
        pickedDate = cal.getTime();
        birthdateButt.setText(formattedDate);

        birthdateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmonth, int selectedday) {
                        birthdateButt.setText(selectedday + " / " + selectedmonth + " / " + selectedyear);
                        selectedmonth += 1;
                        pickedDate = new Date(selectedyear, selectedmonth, selectedday);
                    }
                }, cal.get(java.util.Calendar.YEAR), cal.get(java.util.Calendar.MONTH), cal.get(java.util.Calendar.DAY_OF_MONTH));

                mDatePicker.setTitle("Select Date");
                mDatePicker.show();*/
                DialogFragment newFragment = new DatePickerFragment();
                //newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            //textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                profilePic.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //do some stuff for example write on log and update TextField on activity
        pickedDate=new Date(year,month+1,day);
        Toast.makeText(SignUpActivity.this, "This is my Toast message!",
                Toast.LENGTH_LONG).show();
        birthdateButt.setText(day+" - "+month+1+" - "+year);
    }
}
