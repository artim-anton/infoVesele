package com.artimanton.infovesele.activity;

import android.content.res.Configuration;
import android.os.Bundle;
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
        public void onBindViewHolder(RVAdapter.NewsViewHolder holder, int position) {


            holder.nameNews.setText(mNews.get(position).getNameNews().toString());
            holder.linkPageNews.setText(mNews.get(position).getLinkPageNews().toString());

            if (!mNews.get(position).getLinkImageNews().isEmpty()){
            Picasso.get()
                    .load(mNews.get(position).getLinkImageNews())
                    .resize(800,0)
                    .centerInside()
                    .into(holder.imageNews);}
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


}
