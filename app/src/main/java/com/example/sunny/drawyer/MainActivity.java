package com.example.sunny.drawyer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home",R.color.colorPrimary));
        menuItems.add(new MenuItem("Logout", R.color.colorPrimary));
        menuItems.add(new MenuItem("Exit", R.color.colorPrimary));
        menuItems.add(new MenuItem("Control Panel", R.color.colorPrimaryDark));

        sNavigationDrawer.setMenuItemList(menuItems);

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position){
                    case 0:
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Logout Succussfull", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Exit work here", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Intent intent= new Intent(MainActivity.this, Control_Panel.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }
}
