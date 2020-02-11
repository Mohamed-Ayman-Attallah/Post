package com.example.post;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.post.UserAdapter.encodeImage;

public class Sign extends AppCompatActivity {

TextView textView;
    private CircleImageView profileImage;
    private static final int PICK_IMAGE = 1;
    private TextInputLayout textInputEmail;
    Uri imageUri;
    String s="";
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mDisplayDate = (TextView) findViewById(R.id.age);
        profileImage = findViewById(R.id.pic);
        textInputEmail = findViewById(R.id.input_email);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gall = new Intent();
                gall.setType("image/*");
                gall.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gall, "Select Picture"), PICK_IMAGE);
            }
        });



        textView = findViewById(R.id.back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign.this,Page2.class);
                startActivity(intent);
            }
        });



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Sign.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet:  mm/dd/yyy:  " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE  && resultCode == RESULT_OK){
           imageUri = data.getData();
           try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
               profileImage.setImageBitmap(bitmap);
                s = encodeImage(bitmap);
               Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
           }catch (IOException e){
               e.printStackTrace();
           }
        }
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    public void input(View v){
        if (!validateEmail()) {
            return;
        }else if(s.equals(""))
        {
            return;
        }
        String input = "Email: " + textInputEmail.getEditText().getText().toString();
        Intent intent = new Intent(Sign.this,Home.class);
        //intent.putExtra("photo",s);
        startActivity(intent);
    }
}
