package com.artimanton.infovesele.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.permission.Internet;

import java.util.Locale;

public class HomeActivityWeb extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int REQUEST_READ_PHONE_STATE = 10001;
    private static final String READ_PHONE_STATE_PERMISSION = Manifest.permission.READ_PHONE_STATE;
    private WebView mWebView;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocate();
        setContentView(R.layout.activity_home_web);
        setupBottomNavigation(0, this);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        isOnline();
        getPermission();
        LoadWeb();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadWeb();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.nav_setting, R.string.nav_logout);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void LoadWeb(){
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
                mWebView.loadUrl("file:///android_asset/error.html");
            }

            public void onPageFinished(WebView view,String url){
                ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress_bar);
                progressbar.setVisibility(View.GONE);
                swipe.setRefreshing(false);
            }
        });

        mWebView.loadUrl("https://veselivska-gromada.gov.ua/news/?p=1");

    }

    public void isOnline(){
        if ( !Internet.isOnline(this) ){
            Toast.makeText(this, "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
        }
    }

    public void getPermission(){
        // проверяем разрешения: если они уже есть,
        // то приложение продолжает работу в нормальном режиме
        if (isPermissionGranted(READ_PHONE_STATE_PERMISSION)) {
            //Toast.makeText(this, "Разрешения есть, можно работать", Toast.LENGTH_SHORT).show();
        } else {
            // иначе запрашиваем разрешение у пользователя
            requestPermission(READ_PHONE_STATE_PERMISSION, REQUEST_READ_PHONE_STATE);
        }
    }

    public boolean isPermissionGranted(String permission) {
        // проверяем разрешение - есть ли оно у нашего приложения
        int permissionCheck = ActivityCompat.checkSelfPermission(this, permission);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(HomeActivity.this, "Разрешения получены", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(HomeActivity.this, "Разрешения не получены", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void requestPermission(String permission, int requestCode) {
        // запрашиваем разрешение
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }


    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }


    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack() & Internet.isOnline(this)) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            showChangeLanguageDialog();

        } else if (id == R.id.nav_logout){

            this.finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showChangeLanguageDialog() {
        final String[] listItem = {"Русский", "Українська", "Polski", "English"};
        AlertDialog.Builder mBilder = new AlertDialog.Builder(HomeActivityWeb.this);
        mBilder.setTitle(getString(R.string.choose_language));
        mBilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0){
                    setLocate("ru");
                    recreate();
                }
                else if (i == 1){
                    setLocate("uk");
                    recreate();
                }
                else if (i == 2){
                    setLocate("pl");
                    recreate();
                }
                else if (i == 3){
                    setLocate("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBilder.create();
        mDialog.show();
    }

    private void setLocate(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("infoVeseleSetting", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
    }
    public void loadLocate(){
        SharedPreferences prefs = getSharedPreferences("infoVeseleSetting",Activity.MODE_PRIVATE);
        String language = prefs.getString("My_lang", "");
        setLocate(language);
    }
}
