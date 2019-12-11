package com.example.sunny.drawyer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

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
}
