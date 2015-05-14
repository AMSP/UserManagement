package com.example.mystrymachine.usermanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Date;


public class AddUserActivity extends ActionBarActivity {
    Button save;
    User user;
    EditText u_id,fname,lname,bDate,mNumber;
    RadioButton male,female;
    String u1_id,u_name,u_gender,u_bDate,u_mNumber;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        dbHandler=new DBHandler(getApplicationContext());
        save=(Button)findViewById(R.id.save);

        u_id = (EditText) findViewById(R.id.u_id);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        bDate = (EditText) findViewById(R.id.birthDate);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        mNumber = (EditText) findViewById(R.id.mNumber);



        Bundle extra=getIntent().getExtras();
        String type= extra.getString("type");
        if(extra.getString("type").equals("EDIT"))
        {
            String e_id;
            e_id = extra.getString("e_id");
            DBHandler dbHandler1 = new DBHandler(getApplicationContext());

            User user = dbHandler1.getUser(e_id);
            u_id.setText(e_id);
            String f_name = user.getUser_name().toString();

            fname.setText(f_name.substring(0,f_name.indexOf(" ")));
            lname.setText(f_name.substring(f_name.indexOf(" ")+1,f_name.length()));





            bDate.setText(user.getUser_bDate());
            if (user.getUser_gender().equals("male")) {

                male.setChecked(true);

            } else {

                female.setChecked(true);
            }
            mNumber.setText(user.getUser_mNumber());

             save.setText("UPDATE");

        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_id = (EditText) findViewById(R.id.u_id);
                fname = (EditText) findViewById(R.id.fname);
                lname = (EditText) findViewById(R.id.lname);
                bDate = (EditText) findViewById(R.id.birthDate);

                male = (RadioButton) findViewById(R.id.male);
                female = (RadioButton) findViewById(R.id.female);

                mNumber = (EditText) findViewById(R.id.mNumber);
                if (u_id.getText().toString().equals("")) {
                    u_id.setHint("Please enter ID");
                    u_id.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                } else if (fname.getText().toString().equals("")) {
                    fname.setHint("Please enter first name");
                    fname.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    u_id.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    lname.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    bDate.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    mNumber.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                } else if (lname.getText().toString().equals("")) {
                    lname.setHint("Please enter last name");
                    lname.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    u_id.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    fname.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    bDate.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    mNumber.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                } else if (bDate.getText().toString().equals("")) {
                    bDate.setHint("Please enter birthdate");
                    bDate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    u_id.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    lname.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    fname.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    mNumber.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                } else if (mNumber.getText().toString().equals("")) {
                    mNumber.setHint("Please enter number");
                    mNumber.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    u_id.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    lname.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    fname.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    bDate.setBackgroundColor(getResources().getColor(android.R.color.transparent));



                }else {


                if (save.getText().toString().equals("SAVE")) {

                    if (male.isChecked()) {
                        u_gender = male.getText().toString();
                    } else {

                        u_gender = female.getText().toString();


                    }

                    u1_id = u_id.getText().toString();
                    u_name = fname.getText().toString().concat(" ").concat(lname.getText().toString());
                    u_bDate = bDate.getText().toString();
                    u_mNumber = mNumber.getText().toString();


                    user = new User(u1_id, u_name, u_gender, u_bDate, u_mNumber);
                    dbHandler.addUser(user);
                    Toast.makeText(getApplicationContext(), "Records are inserted...", Toast.LENGTH_SHORT).show();
0
                } else {

                    Toast.makeText(getApplicationContext(), "Updating code", Toast.LENGTH_LONG).show();

                    if (male.isChecked()) {
                        u_gender = male.getText().toString();
                    } else {

                        u_gender = female.getText().toString();


                    }

                    u1_id = u_id.getText().toString();
                    u_name = fname.getText().toString().concat(" ").concat(lname.getText().toString());
                    u_bDate = bDate.getText().toString();
                    u_mNumber = mNumber.getText().toString();


                    user = new User(u1_id, u_name, u_gender, u_bDate, u_mNumber);
                    dbHandler.updateUser(user);


                }

                Intent intent = new Intent(AddUserActivity.this, ListUserActivity.class);
                startActivity(intent);
            }
            }

        });

//        Bundle extra=getIntent().getExtras();
//
//
//        String e_id=extra.getString("e_id");
//        if (e_id != null || e_id!="")
//        {
//            DBHandler dbHandler1 = new DBHandler(getApplicationContext());
//            User user = dbHandler1.getUser(e_id);
//            u_id.setText(user.getUser_id());
//            fname.setText(user.getUser_name());
//            bDate.setText(user.getUser_bDate());
//            if (user.getUser_gender() == "male") {
//
//                male.setChecked(true);
//
//            } else {
//
//                female.setChecked(true);
//            }
//            mNumber.setText(user.getUser_mNumber());
//
//            save.setText("update");
//
//        }


    }



}
