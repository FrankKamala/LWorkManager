package com.kamala.learnworkman;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyPeriodic  extends Worker {

    public static String TAG="Periodic";
    public MyPeriodic(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG,"to stay");


        return Result.success();
    }
}
