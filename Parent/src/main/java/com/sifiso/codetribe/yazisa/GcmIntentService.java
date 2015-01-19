package com.sifiso.codetribe.yazisa;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;


import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.sifiso.ylibrary.YLibrary.dto.AttendenceDTO;
import com.sifiso.ylibrary.YLibrary.dto.IssueDTO;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;

public class GcmIntentService extends GCMBaseIntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    private static final String GCM_SENDER_ID = "260429534791";

    public GcmIntentService() {
        super(GCM_SENDER_ID);
    }

    @Override
    protected void onError(Context arg0, String arg1) {
        Log.i(TAG, "onError ... " + arg1);

    }

    @Override
    protected void onMessage(Context arg0, Intent intent) {
        Log.w(TAG, "onMessage ..trainee:..gcm message here... " + intent.getExtras().toString());
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
        Log.d(TAG, "GCM messageType = " + messageType);
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
                    .equals(messageType)) {
                Log.e(TAG, "GoogleCloudMessaging - MESSAGE_TYPE_SEND_ERROR");
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
                    .equals(messageType)) {
                Log.e(TAG, "GoogleCloudMessaging - MESSAGE_TYPE_SEND_ERROR");

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
                    .equals(messageType)) {
                //It's a regular GCM message, do some work.
                sendNotification(intent);
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);

    }

    @Override
    protected void onRegistered(Context arg0, String arg1) {
        Log.i(TAG, "onRegistered ... " + arg1);
        //registerWithAirBop(registrationId);
    }

    @Override
    protected void onUnregistered(Context arg0, String arg1) {
        Log.i(TAG, "onUnRegistered ... " + arg1);
    }

    Gson gson = new Gson();

    private void sendNotification(Intent msgIntent) {
        mNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        String message = msgIntent.getExtras().getString("message");
        ResponseDTO response;
        try {

            response = gson.fromJson(message, ResponseDTO.class);
            if (response.getAttendence() != null) {
                sendAttendanceNotification(response.getAttendence());
            } else if (response.getIssue() != null) {
                sendIssueNotification(response.getIssue());
            }

        } catch (Exception e) {
            sendSimpleNotification(message);
            sendRefreshNotification(msgIntent);
        }

    }

    private void sendRefreshNotification(Intent msgIntent) {
        Intent wpaIntent = new Intent(this, MainPagerActivity.class);
        wpaIntent.putExtra("refresh", true);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                wpaIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        String contentText = "Refresh";

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_action_refresh)
                .setContentTitle("refresh")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Who be me?"))
                .setContentText(contentText);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    private void sendAttendanceNotification(AttendenceDTO dto) {

        Intent wpaIntent = new Intent(this, MainPagerActivity.class);
        wpaIntent.putExtra("refresh", true);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                wpaIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.reply32)
                .setContentTitle("Teacher " + dto.getTeacher().getSurname())
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Attendance Issue"))
                .setContentText(dto.getMessage());

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
    private void sendSimpleNotification(String msg) {

        Intent wpaIntent = new Intent(this, MainPagerActivity.class);
        wpaIntent.putExtra("refresh", true);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                wpaIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.reply32)
                .setContentTitle("Confirmation")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Gcm Confirmation"))
                .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
    private void sendIssueNotification(IssueDTO dto) {

        Intent wpaIntent = new Intent(this, MainPagerActivity.class);
        wpaIntent.putExtra("refresh", true);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                wpaIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.reply32)
                .setContentTitle("Teacher " + dto.getTeacher().getSurname())
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Behaviour Issue"))
                .setContentText(dto.getMessage());

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }


    static final String TAG = "GcmIntentService-Trainee";

}