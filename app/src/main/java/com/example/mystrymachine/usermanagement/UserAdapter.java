package com.example.mystrymachine.usermanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MYSTRY MACHINE on 4/9/2015.
 */
public class UserAdapter extends BaseAdapter{
    Context context;
    ArrayList<User> usersList;

    public UserAdapter(Context context, ArrayList<User> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @Override

    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user=usersList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_list_row, null);

        }
        TextView tvID= (TextView) convertView.findViewById(R.id.tv_user_id);
        tvID.setText((CharSequence) user.getUser_id());
        TextView tvNAME= (TextView) convertView.findViewById(R.id.tv_user_name);
        tvNAME.setText((CharSequence) user.getUser_name());
        TextView tvGENDER= (TextView) convertView.findViewById(R.id.tv_user_gender);
        tvGENDER.setText((CharSequence) user.getUser_gender());
        TextView tvBIRTHDATE= (TextView) convertView.findViewById(R.id.tv_user_bDate);
        tvBIRTHDATE.setText((CharSequence) user.getUser_bDate());
        TextView tvCONTACT= (TextView) convertView.findViewById(R.id.tv_user_mNumber);
        tvCONTACT.setText((CharSequence) user.getUser_mNumber());
        Button edit_btn= (Button) convertView.findViewById(R.id.edit);
        Button delete_btn= (Button) convertView.findViewById(R.id.delete);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(context);
                dbHandler.delectUser(user);

                Intent intent=new Intent(context,ListUserActivity.class);

                v.getContext().startActivity(intent);
                ////ListUserActivity activity=new ListUserActivity();
                //activity.onCreate(Bundle.EMPTY);
                //Delete1 delete1=new Delete1(context);
                //delete1.del_redirect();
            }
        });
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AddUserActivity.class);
                intent.putExtra("e_id",user.getUser_id());
                intent.putExtra("type","EDIT");
                v.getContext().startActivity(intent);
            }
        });





        return convertView;
    }


}
