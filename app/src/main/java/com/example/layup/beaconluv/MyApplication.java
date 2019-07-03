package com.example.layup.beaconluv;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

/**
 * Created by layup on 2017-11-18.
 */

public class MyApplication extends Application {

    private BeaconManager beaconManager;
    private BeaconManager beaconManager2;


    @Override
    public void onCreate() {

        /** 어플리케이션을 설치할 때 최초 실행된다. */
        super.onCreate();
        //EstimoteSDK.initialize(getApplicationContext(), "<Here goes your application ID>", "<>Here goes your application token");

        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager2 = new BeaconManager(getApplicationContext());

        //Application 설치가 끝나면 Beacon Monitoring Service를 시작한다.
        //Application 을 종료해도 Service 가 계속 실행된다.







        // Android 디바이스가 Beacon의 송신 범위에 들어가거나 나왔을 때 체크한다.
        //OnEnterRegion : 등록 된 지역에서 비콘에 처음 도달 가능하게 될 때 발생합니다.
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {  //지역(모니터링 된 지역) , List <Beacon> 비콘 (이벤트를 트리거 한 비콘 목록)
                showNotification("Cheerluv Team 첫 번째 비콘", "쿠폰 비콘 연결되었습니다." + "연결됬어! " + list.get(0).getRssi());
                Intent intent  = new Intent(getApplicationContext(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("excuteType", "beacon1");
                getApplicationContext().startActivity(intent);
            }

         //OnExitRegion : 지역에 등록 된 모든 신호가 도달 불능이되었을 때 발생합니다.
            @Override
            public void onExitedRegion(Region region) {
               cancelNotification(); //해제할때 필요
               /*howNotification("Cheerluv Team", "비콘 해제되었습니다." );*/
            }
        });








        beaconManager2.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region2, List<Beacon> list) {  //지역(모니터링 된 지역) , List <Beacon> 비콘 (이벤트를 트리거 한 비콘 목록)
                showNotification("Cheerluv Team 두 번째 비콘", "게시판 비콘 연결되었습니다." + "연결됬어! " + list.get(0).getRssi());
                Intent intent  = new Intent(getApplicationContext(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("excuteType", "beacon2");
                getApplicationContext().startActivity(intent);
            }

            //OnExitRegion : 지역에 등록 된 모든 신호가 도달 불능이되었을 때 발생합니다.
            @Override
            public void onExitedRegion(Region region) {
                cancelNotification(); //해제할때 필요
               /*howNotification("Cheerluv Team", "비콘 해제되었습니다." );*/
            }
        });











        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region("monitored region",
                        UUID.fromString("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"),30001,14086)); //연결할 Beacon의 ID, Major, Minor Code를 알아야 한다.
            }
        });




        beaconManager2.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager2.startMonitoring(new Region("monitored region2",
                        UUID.fromString("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"), 30001, 14085)); //연결할 Beacon의 ID, Major, Minor Code를 알아야 한다.
            }
        });




    } //절대영역


/** ====================================================시스템바 알림==================================================== */
    public void cancelNotification() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }

    //Notification은 통보하다 뜻으로 Beacon의 신호가 연결되거나 끊키면 알림.
    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, ChatActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this,
                0, //request code 자리.
                new Intent[]{notifyIntent},   //인텐트자리.
                PendingIntent.FLAG_UPDATE_CURRENT); //플래그자리. 노티피케이션이 이미 한번 실행되어있으면 코드실행시 내용을 업데이트

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext());
        notification.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title) //알람제목
                .setContentText(message) //알람세부텍스트
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
/** ========================================================================================================================= */



       /* Notification notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(android.R.drawable.ic_dialog_info) //시스템바 작은아이콘
                .setContentTitle(title)
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)  //유저가 터치시 자동으로 사라지도록 함.
                .setContentIntent(pendingIntent)
                .build();*/


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification.build());
    }

}
