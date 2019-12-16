package com.example.sunny.drawyer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    RecyclerView recyclerView;
    profile_data_adapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        recyclerView = findViewById(R.id.taadminrecyleview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<profile_data_model>itemList = new ArrayList<>();
        for(int i=0; i<20; i++){
            itemList.add(new profile_data_model("Sunny", "scsahasunny@gmail.com"));
        }
        adapter = new profile_data_adapter(this, itemList);
        recyclerView.setAdapter(adapter);

        profile_helper profile_helper = new profile_helper(this, recyclerView, 100) {
            @Override
            public void intantiatemybutton(RecyclerView.ViewHolder viewHolder, List<com.example.sunny.drawyer.profile_helper.MyButton> buffer) {

                buffer.add(new MyButton(DashBoard.this, "Delete", 20, R.color.gray, R.drawable.ic_delete_black_24dp, new MyButtonClickListener() {
                    @Override
                    public void onClick(int pos) {
                        Toast.makeText(DashBoard.this, "Item of "+pos+" is delete", Toast.LENGTH_LONG).show();
                    }
                }));

                buffer.add(new MyButton(DashBoard.this, "Update", 20, R.color.gray, R.drawable.ic_update_black_24dp, new MyButtonClickListener() {
                    @Override
                    public void onClick(int pos) {
                        Toast.makeText(DashBoard.this, "Item of "+pos+" is Update", Toast.LENGTH_LONG).show();
                    }
                }));
            }
        };

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home",R.color.colorPrimary));
        menuItems.add(new MenuItem("Profile", R.color.colorPrimary));
        menuItems.add(new MenuItem("Exit", R.color.colorPrimary));
        menuItems.add(new MenuItem("Active CR", R.color.colorPrimary));

        sNavigationDrawer.setMenuItemList(menuItems);

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position){
                    case 0:
                        Intent i = new Intent(DashBoard.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    case 1:
                        Toast.makeText(DashBoard.this, "Logout Succussfull", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(DashBoard.this, "Exit work here", Toast.LENGTH_LONG).show();
                        break;
                    case 3:

                        break;

                }
            }
        });
    }
}
