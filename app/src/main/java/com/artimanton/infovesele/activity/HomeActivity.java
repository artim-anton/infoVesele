package com.artimanton.infovesele.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.model.NewsModel;
import com.artimanton.infovesele.permission.Internet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private static final int REQUEST_READ_PHONE_STATE = 10001;
    private static final String READ_PHONE_STATE_PERMISSION = Manifest.permission.READ_PHONE_STATE;

    private RecyclerView mRecyclerView;
    private String MY_LOG = "myLog";
    private String TAG = "Log";
    private TextView tvLog;
    private ArrayList<NewsModel> mListNews;
    private Bundle outState;
    private NewsModel newsModel;
    private List<NewsModel> allContacts;


  /*  @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("array",mListNews);
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupBottomNavigation(0, this);

        if ( !Internet.isOnline(this) ){
            Toast.makeText(this, "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
        }

        // проверяем разрешения: если они уже есть,
        // то приложение продолжает работу в нормальном режиме
        if (isPermissionGranted(READ_PHONE_STATE_PERMISSION)) {
            //Toast.makeText(this, "Разрешения есть, можно работать", Toast.LENGTH_SHORT).show();
        } else {
            // иначе запрашиваем разрешение у пользователя
            requestPermission(READ_PHONE_STATE_PERMISSION, REQUEST_READ_PHONE_STATE);
        }


        mRecyclerView = findViewById(R.id.news_list);
        tvLog = findViewById(R.id.tvLog);

        allContacts = NewsModel.listAll(NewsModel.class);

        /*if(savedInstanceState == null) {
        mListNews = getIntent().getParcelableArrayListExtra("news");}
        else {
            mListNews = savedInstanceState.getParcelableArrayList("array");}*/
       // mListNews = getIntent().getParcelableArrayListExtra("news");
        //tvLog.setText(String.valueOf(mListNews.get(1).getNameNews()));

        GridLayoutManager manager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            manager = new GridLayoutManager(this, 2);
        }
        else{
            manager = new GridLayoutManager(this, 1);
        }

        mRecyclerView.setLayoutManager(manager);

        RVAdapter adapter = new RVAdapter(allContacts);

        mRecyclerView.setAdapter(adapter);
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.NewsViewHolder>{

        List<NewsModel> mNews;

        public RVAdapter(List<NewsModel> news){
            mNews = news;
        }


        @Override
        public RVAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_view, parent, false));
        }

        @Override
        public void onBindViewHolder(RVAdapter.NewsViewHolder holder, final int position) {


            holder.nameNews.setText(mNews.get(position).getNameNews().toString());
            holder.linkPageNews.setText(mNews.get(position).getLinkPageNews().toString());

            if (!mNews.get(position).getLinkImageNews().isEmpty()){
            Picasso.with(getApplicationContext())
                    .load(mNews.get(position).getLinkImageNews())
                    .resize(800,0)
                    //.centerInside()
                    .into(holder.imageNews);
            holder.imageNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(mNews.get(position).getLinkPageNews().toString()));
                    startActivity(intent);
                }
            });
            }
        }

        @Override
        public int getItemCount() {
            return mNews.size();
        }

        public class NewsViewHolder extends RecyclerView.ViewHolder {
            ImageView imageNews;
            TextView nameNews;
            TextView linkPageNews;
            public NewsViewHolder(View itemView) {
                super(itemView);
                imageNews = itemView.findViewById(R.id.img_news);
                nameNews = itemView.findViewById(R.id.et_name_news);
                linkPageNews = itemView.findViewById(R.id.et_text_news);
            }
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


}
