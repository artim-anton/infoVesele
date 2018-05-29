package com.artimanton.infovesele.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.BaseActivity;
import com.artimanton.infovesele.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListNewsActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private String MY_LOG = "myLog";
    private String TAG = "Log";
    TextView tvLog;
    ArrayList<NewsModel> mListNews;


    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelableArrayList("array",mListNews);
        super.onSaveInstanceState(outState, outPersistentState);
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        setupBottomNavigation(0);


        mRecyclerView = findViewById(R.id.news_list);
        tvLog = findViewById(R.id.tvLog);

        if(savedInstanceState == null) {
        mListNews = getIntent().getParcelableArrayListExtra("news");}
        else {mListNews = savedInstanceState.getParcelableArrayList("array");}

        //tvLog.setText(String.valueOf(mListNews.get(1).getNameNews()));

        GridLayoutManager manager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            manager = new GridLayoutManager(this, 2);
        }
        else{
            manager = new GridLayoutManager(this, 1);
        }

        mRecyclerView.setLayoutManager(manager);

        RVAdapter adapter = new RVAdapter(mListNews);

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
