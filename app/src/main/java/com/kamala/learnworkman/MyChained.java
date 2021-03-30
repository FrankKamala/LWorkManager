package com.kamala.learnworkman;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyChained extends Worker {

    public static String TAG="Main Chained";
    public MyChained(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Thread.sleep(5000);
            Log.e(TAG,"PrintMe Out");
            return Result.success();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.failure();
        }



    }
}
