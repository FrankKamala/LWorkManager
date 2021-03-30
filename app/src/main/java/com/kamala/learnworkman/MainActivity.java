package com.kamala.learnworkman;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.impl.constraints.controllers.BatteryChargingController;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView mText;
    public static String TAG="Main";
    Dialog myDialog ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.onlyText);
        myDialog = new Dialog(MainActivity.this);
        myDialog.setContentView(R.layout.custom_dialog);
        myDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        myDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        myDialog.setCancelable(false);
        myDialog.getWindow().getAttributes().windowAnimations= R.style.animation;


        Constraints constraints =new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Constraints periodConstraints =new Constraints.Builder().setRequiresBatteryNotLow(true).build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);


        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyPeriodic.class,20, TimeUnit.MINUTES).setConstraints(periodConstraints).build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);
//
        //
        //chained
        Constraints chainedConstraints =new Constraints.Builder().setRequiresCharging(false).build();
        OneTimeWorkRequest oneTimeWorkRequestOne = new OneTimeWorkRequest.Builder(MyChained.class).setConstraints(chainedConstraints).build();
        OneTimeWorkRequest oneTimeWorkRequestTwo = new OneTimeWorkRequest.Builder(MyChainedWork.class).setConstraints(chainedConstraints).build();

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequestOne).then(oneTimeWorkRequestTwo).enqueue();

        Button leave = myDialog.findViewById(R.id.button);
        leave.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this,"Leaving ",Toast.LENGTH_SHORT);
            myDialog.dismiss();
        });

        mText.setOnClickListener(view -> myDialog.show());
    }
}