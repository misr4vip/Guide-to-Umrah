package com.example.guidetoumrah;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import java.util.concurrent.atomic.AtomicBoolean;

public class Utility {

    public static boolean showMessage(Context context , String title , String message)
    {
        AtomicBoolean answer = new AtomicBoolean(false);
        AlertDialog.Builder alert =  new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Yes", (dialog, id) -> {
            answer.set(true);
            dialog.cancel();
        });
        alert.setNegativeButton("No", (dialog, id) -> {
            //  Action for 'NO' Button
            answer.set(false);
            dialog.cancel();
        });
        alert.create().show();
        return answer.get();
    }

}
