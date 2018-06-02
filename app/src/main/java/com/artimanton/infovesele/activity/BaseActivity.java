package com.artimanton.infovesele.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.artimanton.infovesele.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

abstract class BaseActivity extends AppCompatActivity {

    static BottomNavigationViewEx bottomNavigationViewEx;

    public void setupBottomNavigation(int navNumber, final Activity activity) {
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
                switch (item.getItemId()) {
                    case R.id.nav_item_home:
                        ActivityToOpen = HomeActivity.class;
                        break;
                    case R.id.nav_item_transport:
                        ActivityToOpen = TransportActivity.class;
                        break;
                    case R.id.nav_item_voice:
                        ActivityToOpen = AdvertVeseleActivity.class;
                        break;
                    case R.id.nav_item_build:
                        //Toast.makeText(con, "nav_item_build", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_forum:
                        //Toast.makeText(con, "nav_item_forum", Toast.LENGTH_SHORT).show();
                        break;
                }

                if (ActivityToOpen != null) {
                    Intent intent = new Intent(con, ActivityToOpen);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    activity.finish();
                    overridePendingTransition(0,0);
                    return true;
                } else {
                    return false;
                }
            }


        });
        bottomNavigationViewEx.getMenu().getItem(navNumber).setChecked(true);
    }

}

