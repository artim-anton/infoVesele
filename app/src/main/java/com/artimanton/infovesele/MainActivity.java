package com.artimanton.infovesele;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.artimanton.infovesele.activity.HomeActivity;
import com.artimanton.infovesele.activity.HomeActivityWeb;
import com.artimanton.infovesele.db.SugarORM;
import com.artimanton.infovesele.model.NewsModel;
import com.artimanton.infovesele.notification.Notification_reciever;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MY_SETTINGS = "notification_watercanal";
   // private String MY_LOG = "myLog";
    //private List<NewsModel> listNews = new ArrayList<>();
    //private NewsModel newsModel;

    //private ProgressBar progressBar;
    //Integer count = 1;

    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //progressBar = (ProgressBar) findViewById(R.id.progressbar);


        //SugarORM.deleteTables();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, HomeActivityWeb.class);
                startActivity(i);
                finish();
            }
        }, 1*1000);



        SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа
        boolean saveAlarm = sp.getBoolean("saveAlarm", false);

        if (!saveAlarm) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 30);

        Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
        intent.setAction("MY_NOTIFICATION_MESSAGE");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY*31, pendingIntent);

        SharedPreferences.Editor e = sp.edit();
        e.putBoolean("saveAlarm", true);
        e.commit();  }


        //new ParseAllNews().execute();
    }

   /* class ParseAllNews extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(3);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            String urlHome = "https://veselivska-gromada.gov.ua/news/";
            try {

                Document document = Jsoup.connect(urlHome).get();
                Elements element = document.select("div[class=pageLister]");
                String lastPage = element.select("li>a[class=last]").attr("href");
                int count = Integer.parseInt(lastPage.replace("?p=",""));

                for (int i = 1; i <= 3; i++) {
                    String url = "https://veselivska-gromada.gov.ua/news/?p=" + i;
                    itemNews(url);
                    publishProgress(i);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Intent intent = new Intent(MainActivity.this, HomeActivityWeb.class);
            //intent.putParcelableArrayListExtra("news", (ArrayList<? extends Parcelable>) listNews);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }


        private void itemNews(String url){
            try {
                String linkPageNews, linkImageNews, nameNews;
                Document document = Jsoup.connect(url).get();
                Elements els =  document.select("div[class=col-xs-12]>div[id=content_block]>div[class=one_news]");
                Elements elsGrayBlock =  document.select("div[class=col-xs-12]>div[id=content_block]>div[class=one_object_news grey_block]");

                 for (Element elj : elsGrayBlock){
                    linkPageNews = elj.select("div[class=row]>div[class=col-sm-4]>a").attr("href");
                    linkImageNews = elj.select("div[class=row]>div[class=col-sm-4]>a>img").attr("src");
                    nameNews = elj.select("div[class=row]>div[class=col-sm-8]>p>a").text();

                    //listNews.add(new NewsModel(linkImageNews, nameNews, linkPageNews));
                    newsModel = new NewsModel(linkImageNews, nameNews, linkPageNews);
                    newsModel.save();

                }

                for (Element el : els) {
                    linkPageNews = el.select("div[class=row]>div[class=grid-30]>a").attr("href");
                    linkImageNews = el.select("div[class=row]>div[class=col-sm-4]>a>img").attr("src");
                    nameNews = el.select("div[class=row]>div[class=col-sm-8]>p>a").text();

                    //listNews.add(new NewsModel(linkImageNews, nameNews, linkPageNews));
                    newsModel = new NewsModel(linkImageNews, nameNews, linkPageNews);
                    newsModel.save();
                }





            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
