package com.artimanton.infovesele.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.artimanton.infovesele.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

abstract class BaseActivity extends AppCompatActivity{
    static BottomNavigationViewEx bottomNavigationViewEx;
    public void setupBottomNavigation(){
        bottomNavigationViewEx = findViewById(R.id.bottom_navigation_view);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableAnimation(false);
        for (int i = 0; i < bottomNavigationViewEx.getMenu().size(); i++) {
            bottomNavigationViewEx.setIconTintList(i, null);
        }

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            Context con = bottomNavigationViewEx.getContext();
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Class ActivityToOpen = null;
                switch (item.getItemId()){
                    case R.id.nav_item_home: ActivityToOpen = ListNewsActivity.class;
                        break;
                    case R.id.nav_item_transport: ActivityToOpen = Transport.class;
                        break;
                    case R.id.nav_item_build:
                        //Toast.makeText(con, "nav_item_build", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_forum:
                       //Toast.makeText(con, "nav_item_forum", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_voice:
                        //Toast.makeText(con, "nav_item_voice", Toast.LENGTH_SHORT).show();
                }

                if (ActivityToOpen != null){
                    Intent intent = new Intent(con,ActivityToOpen);
                    startActivity(intent);
                }
                return true;
            }


        });

    }
}
