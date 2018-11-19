package com.artimanton.infovesele.activity.all_organization;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.notification.Notification_reciever;
import com.artimanton.infovesele.utilities.BackGroundActivity;

import java.util.Calendar;

public class Watercanal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watercanal);
        BackGroundActivity.setBackground(this, this);
    }

    public void btnTestify(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfK3M4llCkwh5ild7vZqSSvW4lMiGkbv14LUCOAxQkxeMYWow/viewform"));
        startActivity(intent);
    }

    public void btnSite(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://vodokanalvesele.at.ua"));
        startActivity(intent);
    }

    public void btnCreateNotification(View view) {
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
    }
}
