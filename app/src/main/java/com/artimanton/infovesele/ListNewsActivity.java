package com.artimanton.infovesele;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artimanton.infovesele.model.NewsModel;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListNewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private String MY_LOG = "myLog";
    TextView tvLog;
    BottomNavigationViewEx bottomNavigationViewEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        bottomNavigationViewEx = findViewById(R.id.bottom_navigation_view);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableAnimation(false);
        for (int i = 0; i < bottomNavigationViewEx.getMenu().size(); i++) {
            bottomNavigationViewEx.setIconTintList(i, null);
        }


        mRecyclerView = findViewById(R.id.news_list);
        tvLog = findViewById(R.id.tvLog);


        ArrayList<NewsModel> mListNews = getIntent().getParcelableArrayListExtra("news");

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

            Picasso.get()
                    .load(mNews.get(position).getLinkImageNews())
                    .resize(800, 800)
                    .centerCrop()
                    .into(holder.imageNews);


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
