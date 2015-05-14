package com.example.mystrymachine.usermanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchUserActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    Button searchButton,listButton;
    Spinner spinner;
    EditText totalCount,searchCount,key;
    String[] opt = { "NAME","CONTACT_NUMBER","GENDER","MONTH"};
    UserAdapter userAdapter;
    String temp;
    ListView lvCustomList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        lvCustomList= (ListView) findViewById(R.id.search_lv);
        totalCount = (EditText)findViewById(R.id.totalCount);
        searchCount = (EditText)findViewById(R.id.searchCount);
        spinner = (Spinner)findViewById(R.id.spinner);
        listButton= (Button) findViewById(R.id.listButton);
        key = (EditText)findViewById(R.id.key);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchUserActivity.this,ListUserActivity.class);
                startActivity(intent);
            }
        });

        totalCount= (EditText) findViewById(R.id.totalCount);
//        totalCount.setText(userAdapter.getCount());


        ArrayAdapter<String> adpr=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opt);
        adpr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adpr);

        spinner.setOnItemSelectedListener(this);


        searchButton= (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                DBHandler mm = new DBHandler(getApplicationContext());
                Bundle extra = getIntent().getExtras();

                totalCount= (EditText) findViewById(R.id.totalCount);
                totalCount.setText(extra.getString("total_user_count").toString());

                ArrayList<User> usersList = new ArrayList<User>();
                usersList.clear();
                String query,search_key;


                switch (temp)
                {
                    case  "NAME":

//                        //Toast.makeText(getApplicationContext(),key.getText().toString(),Toast.LENGTH_LONG).show();
//                        String search_key= key.getText().toString();
//                        query = "SELECT * FROM users WHERE u_name LIKE "+search_key;
//
//                        usersList = mm.selectAll(query);
//                        searchCount.setText( Integer.toString(usersList.size()));
//                        UserAdapter userAdapter=new UserAdapter(SearchUserActivity.this,usersList);
//                        lvCustomList.setAdapter(userAdapter);
//
                        search_key= key.getText().toString();
                        query = "SELECT * FROM users WHERE u_name LIKE '"+search_key+"'";
                        showSearchedUserList(query);




                        break;
                        case  "CONTACT_NUMBER":
                        search_key= key.getText().toString();
                        query = "SELECT * FROM users WHERE u_mNumber LIKE "+search_key;
                        showSearchedUserList(query);


                        // usersList = mm.selectAll(query);
//                        searchCount.setText( Integer.toString(usersList.size()));
//
//                        UserAdapter userAdapter1=new UserAdapter(SearchUserActivity.this,usersList);
//                        lvCustomList.setAdapter(userAdapter1);


                        break;

                    case  "GENDER":
//                      //  query = "SELECT * FROM users WHERE (u_gender = "+key.getText().toString()+")";
//                        query = key.getText().toString();
//
//                        usersList = mm.selectAll(query);
//                        searchCount.setText( Integer.toString(usersList.size()));
//
//                        UserAdapter userAdapter2=new UserAdapter(SearchUserActivity.this,usersList);
//                        lvCustomList.setAdapter(userAdapter2);
//

                        search_key= key.getText().toString();
                        query = "SELECT * FROM users WHERE u_gender LIKE '"+search_key+"'";
                        showSearchedUserList(query);


                    break;
                    case "MONTH":

                        search_key= key.getText().toString();
                        query = "SELECT * FROM users WHERE u_bDate LIKE '%/"+search_key+"/%'";
                        showSearchedUserList(query);

                        break;
                    default :
                        break;

                }

            }
        });


    }

    private void showSearchedUserList(String query) {
        ArrayList<User> usersList = new ArrayList<User>();
        usersList.clear();

        DBHandler dbHandler = new DBHandler(getApplicationContext());
               Cursor c1 = dbHandler.selectQuery(query);
        searchCount.setText(Integer.toString(c1.getCount()));

        if (c1 != null ) {
            if (c1.moveToFirst()) {
                do {
                    User user=new User();
                    int idIndex = c1.getColumnIndex(dbHandler.COLUMN_ID);
                    int nameIndex = c1.getColumnIndex(dbHandler.COLUMN_NAME);
                    int genderIndex = c1.getColumnIndex(dbHandler.COLUMN_GENDER);
                    int bDateIndex = c1.getColumnIndex(dbHandler.COLUMN_BDATE);
                    int mNumberIndex = c1.getColumnIndex(dbHandler.COLUMN_MNUMBER);
                    user.setUser_id(c1.getString(idIndex));
                    user.setUser_name(c1.getString(nameIndex));
                    user.setUser_gender(c1.getString(genderIndex));
                    user.setUser_bDate(c1.getString(bDateIndex));
                    user.setUser_mNumber(c1.getString(mNumberIndex));

                    usersList.add(user);

                } while (c1.moveToNext());
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No records found...",Toast.LENGTH_LONG).show();
        }
        c1.close();

        UserAdapter userAdapter=new UserAdapter(SearchUserActivity.this,usersList);
        lvCustomList.setAdapter(userAdapter);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        temp = opt[position];

        switch (temp)
        {
            case  "NAME":

                key.setHint("Enter Name to Search");


                break;
            case  "CONTACT_NUMBER":

                key.setHint("Enter Mobile Number to Search");


                break;

            case  "GENDER":
                key.setHint("Enter Gender to Search");



                break; case  "MONTH":
            key.setHint("Enter Month to Search eg : 02 for FEB");



            break;
            default :
                break;





        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
