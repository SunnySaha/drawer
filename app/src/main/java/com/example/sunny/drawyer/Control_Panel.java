package com.example.sunny.drawyer;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Control_Panel extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control__panel);

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home",R.color.colorPrimary));
        menuItems.add(new MenuItem("Logout", R.color.colorPrimary));
        menuItems.add(new MenuItem("Exit", R.color.colorPrimary));

        sNavigationDrawer.setMenuItemList(menuItems);

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position){
                    case 0:
                        Intent i = new Intent(Control_Panel.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    case 1:
                        Toast.makeText(Control_Panel.this, "Logout Succussfull", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(Control_Panel.this, "Exit work here", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
    }


    public void createteacherbtn(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Control_Panel.this);
        View dialogview = getLayoutInflater().inflate(R.layout.add_teacher_admin_dialog, null);

        final EditText adminemail = dialogview.findViewById(R.id.taadminemail);
        final EditText adminname = dialogview.findViewById(R.id.taadminname);
        final TextView note = dialogview.findViewById(R.id.taadminnote);

        Button createAdmin = dialogview.findViewById(R.id.createTaAdmin);

        createAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getadminemail = adminemail.getText().toString();
                String getadminname = adminname.getText().toString();
                if(!getadminemail.isEmpty() && !getadminname.isEmpty()){
                    Toast.makeText(Control_Panel.this,"Admin Email "+getadminemail
                            +" Admin Name "+getadminname, Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Control_Panel.this,"Fillup Filled", Toast.LENGTH_LONG).show();

                }
            }
        });

        alertDialog.setCancelable(true);
        alertDialog.setView(dialogview);
        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

    public void addcoursesbtn(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Control_Panel.this);
        View dialogview = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText course = dialogview.findViewById(R.id.addCourseEditText);
        Button addbtn = dialogview.findViewById(R.id.createbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getCourseName = course.getText().toString();
                if(!getCourseName.isEmpty()){
                    Toast.makeText(Control_Panel.this,"Course Name "+getCourseName, Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Control_Panel.this,"Fillup Filled", Toast.LENGTH_LONG).show();

                }
            }
        });

        alertDialog.setCancelable(true);
        alertDialog.setView(dialogview);
        AlertDialog dialog = alertDialog.create();
        dialog.show();


    }

    public void createcrbtn(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Control_Panel.this);
        View dialogview = getLayoutInflater().inflate(R.layout.add_cr_admin_dialog, null);

        final EditText adminemail = dialogview.findViewById(R.id.cradminemail);
        final EditText adminname = dialogview.findViewById(R.id.cradminname);
        final TextView note = dialogview.findViewById(R.id.cradminnote);

        Button createAdmin = dialogview.findViewById(R.id.createCRAdmin);

        createAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getadminemail = adminemail.getText().toString();
                String getadminname = adminname.getText().toString();
                if(!getadminemail.isEmpty() && !getadminname.isEmpty()){
                    Toast.makeText(Control_Panel.this,"Admin Email "+getadminemail
                            +" Admin Name "+getadminname, Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Control_Panel.this,"Fillup Filled", Toast.LENGTH_LONG).show();

                }
            }
        });

        alertDialog.setCancelable(true);
        alertDialog.setView(dialogview);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public void post_noticebtn(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Control_Panel.this);
        View dialogview = getLayoutInflater().inflate(R.layout.post_notice, null);

        final TextView note = dialogview.findViewById(R.id.addCourseEditText);
        final TextView filename = dialogview.findViewById(R.id.filename);
        Button choosebtn = dialogview.findViewById(R.id.loadfilebtn);
        Button uploadbtn = dialogview.findViewById(R.id.uploadfilebtn);

        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Control_Panel.this, "File Loaded", Toast.LENGTH_LONG).show();
                filename.setText("File Name Will show here");
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!filename.getText().toString().isEmpty()){
                    Toast.makeText(Control_Panel.this, "File Uploaded to server", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(Control_Panel.this, "Please Choose a file", Toast.LENGTH_LONG).show();
                }
            }
        });


        alertDialog.setCancelable(true);
        alertDialog.setView(dialogview);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public void dashboardbtn(View view) {

        Intent i = new Intent(Control_Panel.this, DashBoard.class);
        startActivity(i);
    }
}
