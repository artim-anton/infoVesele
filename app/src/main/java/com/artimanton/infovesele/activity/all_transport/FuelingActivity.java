package com.artimanton.infovesele.activity.all_transport;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.utilities.BackGroundActivity;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FuelingActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    HashMap<Integer, ArrayList<String>> hashMapOne = new HashMap<Integer, ArrayList<String>>();
    HashMap<Integer, ArrayList<String>> hashMapTwo = new HashMap<Integer, ArrayList<String>>();
    String urlAzsOne = "https://www.azski.com.ua/networks/avias/07c52181-7ee6-4a4b-8322-4615f414041d";
    String urlAzsTwo = "https://www.azski.com.ua/networks/zog/32";
    String AzsNameOne, AzsNameTwo;
    String RelevanceOne, RelevanceTwo;
    ImageView imageAvias;
    ImageView imageZog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fueling);

        imageAvias = (ImageView) findViewById(R.id.img_avias);
        imageZog = (ImageView) findViewById(R.id.img_zog);

        Picasso.get()
                .load("https://www.azski.com.ua/images/networks/avias.jpg")
                .into(imageAvias);
        Picasso.get()
                .load("https://www.azski.com.ua/images/networks/zog.jpg")
                .into(imageZog);

        new MyTask().execute();


    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        TableLayout tableRowOne = (TableLayout) findViewById(R.id.tableLayoutOne);
        TableLayout tableRowTwo = (TableLayout) findViewById(R.id.tableLayoutTwo);

        ArrayList<HashMap<String, String>> userTable = new ArrayList<HashMap<String, String>>();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(FuelingActivity.this);
            mProgressDialog.setTitle("Application");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            int countOne = 0;
            int countTwo = 0;
            try {
                Document docOne = Jsoup.connect(urlAzsOne).get();
                Document docTwo = Jsoup.connect(urlAzsTwo).get();
                if (docOne != null || docTwo != null) {
                    Elements tableRowsOne = docOne.getElementsByClass("fuel-gas-station-price-table").select("tr");
                    Elements tableRowsTwo = docTwo.getElementsByClass("fuel-gas-station-price-table").select("tr");

                    AzsNameOne = docOne.getElementsByClass("entry-content clearfix").select("p").first().text();
                    RelevanceOne = docOne.getElementsByClass("entry-content clearfix").select("p").last().text();

                    AzsNameTwo = docTwo.getElementsByClass("entry-content clearfix").select("p").first().text();
                    RelevanceTwo = docTwo.getElementsByClass("entry-content clearfix").select("p").last().text();

                    for (int i = 0; i < tableRowsOne.size(); i++) {
                        Element rowOne = tableRowsOne.get(i);

                        ArrayList<String> arrayListOne = new ArrayList<String>();
                        Elements rowItemsOne = rowOne.select("td");  //select
                        for (int j = 0; j < rowItemsOne.size(); j++) {
                            arrayListOne.add(rowItemsOne.get(j).text());
                        }
                        hashMapOne.put(countOne, arrayListOne);
                        countOne++;
                        Log.d("Output", hashMapOne.toString());
                        for (Element link : rowItemsOne) {
                            //Log.d("Return: ", "" + link.text());
                            //receivedStr = link.text();
                        }



                    }

                    for (int i = 0; i < tableRowsTwo.size(); i++) {
                        Element rowTwo = tableRowsTwo.get(i);

                        ArrayList<String> arrayListTwo = new ArrayList<String>();
                        Elements rowItemsTwo = rowTwo.select("td");  //select
                        for (int j = 0; j < rowItemsTwo.size(); j++) {
                            arrayListTwo.add(rowItemsTwo.get(j).text());
                        }
                        hashMapTwo.put(countTwo, arrayListTwo);
                        countTwo++;
                        Log.d("Output", hashMapTwo.toString());
                        for (Element link : rowItemsTwo) {
                            //Log.d("Return: ", "" + link.text());
                            //receivedStr = link.text();
                        }



                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            TextView tvAzsNameOne = findViewById(R.id.AzsNameOne);
            TextView tvAzsNameTwo = findViewById(R.id.AzsNameTwo);
            TextView tvRelevanceOne = findViewById(R.id.RelevanceOne);
            TextView tvRelevanceTwo = findViewById(R.id.RelevanceTwo);

            tvAzsNameOne.setText(AzsNameOne);
            tvAzsNameTwo.setText(AzsNameTwo);
            tvRelevanceOne.setText(RelevanceOne);
            tvRelevanceTwo.setText(RelevanceTwo);


            for (int i=0; i<hashMapOne.size(); i++) {
                TextView textPrice = new TextView(FuelingActivity.this);
                TextView textName = new TextView(FuelingActivity.this);

                textName.setGravity(Gravity.LEFT);
                textName.setTextColor(0xff000000);

                textPrice.setGravity(Gravity.CENTER);
                textPrice.setTextColor(0xff000000);

                textName.setText(hashMapOne.get(i).get(0).toString());
                textPrice.setText(hashMapOne.get(i).get(1).toString());



                // создаём строку для таблицы
                TableRow rowPrice = new TableRow(FuelingActivity.this);
                rowPrice.addView(textName);// добавляем в строку столбец с именем пользователя
                rowPrice.addView(textPrice);// добавляем в строку столбец с именем пользователя
                tableRowOne.addView(rowPrice); // добавляем в таблицу новую строку


            }


            for (int i=0; i<hashMapTwo.size(); i++) {
                TextView textPrice = new TextView(FuelingActivity.this);
                TextView textName = new TextView(FuelingActivity.this);

                textName.setGravity(Gravity.LEFT);
                textName.setTextColor(0xff000000);

                textPrice.setGravity(Gravity.CENTER);
                textPrice.setTextColor(0xff000000);


                textName.setText(hashMapTwo.get(i).get(0).toString());
                textPrice.setText(hashMapTwo.get(i).get(1).toString());

                // создаём строку для таблицы
                TableRow rowTwo = new TableRow(FuelingActivity.this);
                rowTwo.addView(textName);// добавляем в строку столбец с именем пользователя
                rowTwo.addView(textPrice);// добавляем в строку столбец с именем пользователя
                tableRowTwo.addView(rowTwo); // добавляем в таблицу новую строку
            }

            mProgressDialog.dismiss();
        }
    }
}

