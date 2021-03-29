package com.kamala.learnworkman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public static String TAG="Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constraints constraints =new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Constraints periodConstraints =new Constraints.Builder().setRequiresBatteryNotLow(true).build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);


        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyPeriodic.class,20, TimeUnit.MINUTES).setConstraints(periodConstraints).build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);

    }
}