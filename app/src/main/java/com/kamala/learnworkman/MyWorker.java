package com.kamala.learnworkman;



import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class MyWorker  extends Worker {
    public static String TAG="im here";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        int a=22;
        int b =25;
        Log.e(TAG,"at"+ String.valueOf(a+b));


        return Result.success();
    }
}