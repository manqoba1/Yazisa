package com.sifiso.ylibrary.YLibrary.dto.util;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sifiso.ylibrary.YLibrary.R;

/**
 * Created by CodeTribe1 on 2014-11-22.
 */
public class ToastUtil {
    public static void toast(Context ctx, String message, int durationSeconds,
                             int gravity) {
        Vibrator vb = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(30);
        LayoutInflater inf = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main = inf.inflate(R.layout.toast, null);

        TextView msg = (TextView) main.findViewById(R.id.txtTOASTMessage);
        msg.setText(message);

        CustomToast toast = new CustomToast(ctx, durationSeconds);
        toast.setGravity(gravity, 0, 0);
        toast.setView(main);
        toast.show();
    }

    public static void toast(Context ctx, String message) {
        Vibrator vb = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(30);
        LayoutInflater inf = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main = inf.inflate(R.layout.toast, null);

        TextView msg = (TextView) main.findViewById(R.id.txtTOASTMessage);
        msg.setText(message);

        CustomToast toast = new CustomToast(ctx, 3);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(main);
        toast.show();
    }

    public static void errorToast(Context ctx, String message) {
        if (message == null) {
            Log.e("ToastUtil", "Error message is NULL");
            return;
        }
        Vibrator vb = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(50);
        LayoutInflater inf = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main = inf.inflate(R.layout.toast_error, null);

        TextView msg = (TextView) main.findViewById(R.id.txtTOASTMessage);
        msg.setText(message);

        Toast toast = new Toast(ctx);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(main);
        toast.show();

        Log.e("ToastUtil", message);
    }

    public static void noNetworkToast(Context ctx) {
        Vibrator vb = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(50);
        LayoutInflater inf = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout main = (LinearLayout) inf.inflate(R.layout.toast_error, null);

        TextView msg = (TextView) main.findViewById(R.id.txtTOASTMessage);
        msg.setText("Network not available. Please check your network settings and your reception signal.\n" +
                "Please try again.");

        Toast toast = new Toast(ctx);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(main);
        toast.show();

    }


    public static void serverUnavailable(Context ctx) {

        ToastUtil.errorToast(ctx, "Server is not available or reachable at " +
                "this time. Please try later or contact GhostPractice support.");
    }

    public static void memoryUnavailable(Context ctx) {

        ToastUtil.errorToast(ctx, "Memory required is not available");
    }
}
