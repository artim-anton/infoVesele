package com.artimanton.infovesele.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_organization.Watercanal;

public class Notification_reciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeting_intent = new Intent(context, Watercanal.class);
        repeting_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeting_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Моё Весёлое")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Напоминаем! Срок подачи показаний водомера до 25 числа, текущего месяца, нажмите на уведомление, чтобы подать сейчас."))
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setAutoCancel(true);

        if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")){notificationManager.notify(100, builder.build());}
    }
}
