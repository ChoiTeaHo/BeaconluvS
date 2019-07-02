package com.example.layup.beaconluv;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private BeaconManager beaconManager2;

    private Region region;
    private Region region2;

    private TextView tvId;

    private boolean isConnected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvId = (TextView) findViewById(R.id.tvId); //텍스트뷰 ID 찾기

        beaconManager = new BeaconManager(this); //비콘매니저 객체 생성
        beaconManager2 = new BeaconManager(this);


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this , CouponActivity.class);
                startActivity(intent1);

            }
        });




        // add this below:
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {  /**비콘을 받아오는 소스, 수신 범위를 갱신받는다. */
                if (!list.isEmpty()) {
                    Beacon nearestBeacon = list.get(0);
                    Log.d("Airport", "Nearest places: " + nearestBeacon.getRssi());

                    tvId.setText(nearestBeacon.getRssi() + "");// nearestBeacon.getRssi() : 비콘의 수신 강도
                    tvId.setTextSize(40);



                    // 수신강도가 -70 이상일때 알림창을 띄운다.
                    if( !isConnected && nearestBeacon.getRssi() > -70) {
                        isConnected = true;

                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("알림")
                                .setMessage("비콘연결")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create().show();

                        Intent intent2 = new Intent(MainActivity.this , SplashActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); /*이전 실행액티비티를 다시 띄울때 액티비트를 다시시작하는것x
                                                                                   히스토리 스택에서 해당액티비티를 다시 제일상단으로 재정렬*/
                        intent2.putExtra("excuteType", "beacon1");
                        startActivity(intent2);
                    }

                    // 수신강도가 -70 이하일때 알림창을 띄운다.
                    else if( isConnected && nearestBeacon.getRssi() < -70 ) {
                        Toast.makeText(MainActivity.this, "연결해제됨.", Toast.LENGTH_SHORT).show();
                        isConnected = false;
                    }
                }
            }
        });
//1


            beaconManager2.setRangingListener(new BeaconManager.RangingListener() {
                @Override
                public void onBeaconsDiscovered(Region region2, List<Beacon> list) {
                    if (!list.isEmpty()) {
                        Beacon nearestBeacon2 = list.get(0);
                        Log.d("Airport2", "Nearest places: 2" + nearestBeacon2.getRssi());

                        tvId.setText(nearestBeacon2.getRssi() + "");// nearestBeacon.getRssi() : 비콘의 수신 강도
                        tvId.setTextSize(10);

                        // 수신강도가 -70 이상일때 알림창을 띄운다.
                        if (!isConnected && nearestBeacon2.getRssi() > -70) {
                            isConnected = true;
                            Toast.makeText(MainActivity.this, "두번째비콘 연결완료.", Toast.LENGTH_SHORT).show();
                        }
                        // 수신강도가 -70 이하일때 알림창을 띄운다.
                        else if (isConnected && nearestBeacon2.getRssi() < -70) {
                            Toast.makeText(MainActivity.this, "두번째비콘 연결해제.", Toast.LENGTH_SHORT).show();
                            isConnected = false;
                        }
                    }
                }
            });




        region = new Region("ranged region",
                UUID.fromString("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"),30001,14086); // 연결할 Beacon의 ID와 Major / Minor Code를 알아야 한다.

        region2 = new Region("ranged region2",
                UUID.fromString("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"),30001,14085);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // 블루투스 권한 및 활성화 코드드
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });

        beaconManager2.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager2.startRanging(region2);
            }
        });
    }


    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);
        beaconManager2.stopRanging(region2);
        super.onPause();
    }

}
