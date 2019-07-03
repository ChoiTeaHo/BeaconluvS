package com.example.layup.beaconluv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final String executeType = getIntent().getStringExtra("excuteType");

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = null;

                //비콘에 의해 실행 또는 일반 실행일때 ACTIVITY 다르게.
                if(executeType != null && executeType.equals("beacon1")) {
                    intent = new Intent(SplashActivity.this, CouponActivity.class); //비콘1에의해
                    Log.d("TAG", "if실행(beacon1)");

                }else if(executeType != null && executeType.equals("beacon2")){
                    intent = new Intent(SplashActivity.this, ChatActivity.class); //비콘2에의해 실행
                    Log.d("TAG", "if실행(beacon1)");
                }

                else{
                    intent = new Intent(SplashActivity.this, MainActivity.class); //일반실행
                    Log.d("TAG","else실행");
                }


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
