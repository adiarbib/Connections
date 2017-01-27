package com.example.user.connections;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.FileNotFoundException;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button doneButt;
    ImageButton profilePic;
    EditText mailEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    Button birthdateButt;

    Calendar cal;
    String formattedDate;
    SimpleDateFormat df;
    Date pickedDate;
    String country;
    String usernametxt;
    String passwordtxt;
    String mailtxt;

    public static final String DATE_KEY = "date";
    public static final String COUNTRY_KEY = "country";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initLayoutAndOtherStuff();
        birthdayButtonClick();
        profilePictureClick();
        initSpinner();

        doneButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Retrieve the text entered from the EditText
                usernametxt = usernameEditText.getText().toString();
                passwordtxt = passwordEditText.getText().toString();
                mailtxt = mailEditText.getText().toString();

                // Force user to fill up the form
                if (usernametxt.equals("") || passwordtxt.equals("") || mailtxt.equals("") ) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();

                } else {
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.setEmail(mailtxt);
                    user.put(DATE_KEY,pickedDate);
                    user.put(COUNTRY_KEY,country);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                }

            }
        });

    }

    private void initSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.countries_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void profilePictureClick() {
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void birthdayButtonClick() {
        birthdateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth += 1;
                        birthdateButt.setText(selectedday + " / " + selectedmonth + " / " + selectedyear);
                        pickedDate = new Date(selectedyear, selectedmonth, selectedday);
                    }
                }, cal.get(java.util.Calendar.YEAR), cal.get(java.util.Calendar.MONTH), cal.get(java.util.Calendar.DAY_OF_MONTH));

                mDatePicker.setTitle("Select your birthday");
                mDatePicker.show();

            }
        });
    }

    private void initLayoutAndOtherStuff() {
        profilePic = (ImageButton) findViewById(R.id.img);
        mailEditText = (EditText) findViewById(R.id.mail);
        usernameEditText = (EditText) findViewById(R.id.signup_username_input);
        passwordEditText = (EditText) findViewById(R.id.signup_password_input);
        birthdateButt = (Button) findViewById(R.id.birthdate);
        doneButt = (Button) findViewById(R.id.done_butt);

        cal = Calendar.getInstance();
        df = new SimpleDateFormat("dd - MMM - yyyy");
        formattedDate = df.format(cal.getTime());
        pickedDate = cal.getTime();
        birthdateButt.setText(formattedDate);
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
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        country = (String) parent.getItemAtPosition(pos);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
