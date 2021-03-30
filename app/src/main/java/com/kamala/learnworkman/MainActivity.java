package com.kamala.learnworkman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView mText;
    public static String TAG="Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.onlyText);

        Constraints constraints =new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Constraints periodConstraints =new Constraints.Builder().setRequiresBatteryNotLow(true).build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);


        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyPeriodic.class,20, TimeUnit.MINUTES).setConstraints(periodConstraints).build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);
//
        //
        //chained
        OneTimeWorkRequest oneTimeWorkRequestOne = new OneTimeWorkRequest.Builder(MyChained.class).setConstraints(constraints).build();
        OneTimeWorkRequest oneTimeWorkRequestTwo = new OneTimeWorkRequest.Builder(MyChainedWork.class).setConstraints(constraints).build();

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequestOne).then(oneTimeWorkRequestTwo).enqueue();
    }
}