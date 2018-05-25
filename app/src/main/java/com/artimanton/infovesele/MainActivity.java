package com.artimanton.infovesele;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String MY_LOG = "myLog";
    private List<News> listNews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new ParseAllNews().execute();
    }

    class ParseAllNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String urlHome = "https://veselivska-gromada.gov.ua/news/";
            try {
                Document document = Jsoup.connect(urlHome).get();
                Elements element = document.select("div[class=pageLister]");
                String lastPage = element.select("li>a[class=last]").attr("href");
                int count = Integer.parseInt(lastPage.replace("?p=",""));

                for (int i = 0; i <= 1; i++) {
                    String url = "https://veselivska-gromada.gov.ua/news/?p=" + i;
                    itemNews(url);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent intent = new Intent(MainActivity.this, ListNewsActivity.class);
            intent.putParcelableArrayListExtra("news", (ArrayList<? extends Parcelable>) listNews);
            startActivity(intent);
            finish();
        }

        private void itemNews(String url){
            try {
                String linkPageNews, linkImageNews, nameNews;
                Document document = Jsoup.connect(url).get();
                Elements els =  document.select("div[class=col-xs-12]>div[id=content_block]>div*[class=one_object_news]");
                for (Element el : els) {
                    linkPageNews = el.select("div[class=row]>div[class=col-sm-4]>a").attr("href");
                    linkImageNews = el.select("div[class=row]>div[class=col-sm-4]>a>img").attr("src");
                    nameNews = el.select("div[class=row]>div[class=col-sm-8]>p>a").text();

                    //Log.d(MY_LOG, linkPageNews + " " + linkImageNews + " " + nameNews);
                    listNews.add(new News(linkImageNews, nameNews, linkPageNews));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
