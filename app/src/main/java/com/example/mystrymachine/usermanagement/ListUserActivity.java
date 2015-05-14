package com.example.mystrymachine.usermanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ListUserActivity extends ActionBarActivity {
    Button add,search;
    ListView lvCustomList;
    DBHandler dbHandler;
    Intent searchIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        //getting list of user
        lvCustomList= (ListView) findViewById(R.id.lv_custom_list);
        dbHandler=new DBHandler(getApplicationContext());
        showUserList();

        //add listener to button
        add=(Button)findViewById(R.id.AddUser);
        search=(Button)findViewById(R.id.SearchUser);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(ListUserActivity.this,AddUserActivity.class);
                intent.putExtra("type","ADD");

                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(ListUserActivity.this,SearchUserActivity.class);
                startActivity(searchIntent);
            }
        });
    }

    private void showUserList() {
        ArrayList<User> usersList = new ArrayList<User>();
        usersList.clear();
        String query = "SELECT * FROM users ";
        Cursor c1 = dbHandler.selectQuery(query);

        searchIntent = new Intent(ListUserActivity.this,SearchUserActivity.class);
        searchIntent.putExtra("total_user_count", Integer.toString(c1.getCount()));
        if (c1 != null && c1.getCount() != 0) {
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
        c1.close();

        UserAdapter userAdapter=new UserAdapter(ListUserActivity.this,usersList);
        lvCustomList.setAdapter(userAdapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
