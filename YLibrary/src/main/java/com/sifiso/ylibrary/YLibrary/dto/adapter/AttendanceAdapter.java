package com.sifiso.ylibrary.YLibrary.dto.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sifiso.ylibrary.YLibrary.R;
import com.sifiso.ylibrary.YLibrary.dto.AttendenceDTO;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;
import com.sifiso.ylibrary.YLibrary.dto.util.Util;

import java.util.Date;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-12-10.
 */
public class AttendanceAdapter extends ArrayAdapter<AttendenceDTO> {
    public interface AttendanceAdapterListener {
        public void studentClicked(AttendenceDTO attendence);
    }

    private List<AttendenceDTO> mList;
    private Context ctx;
    private Bundle b;
    LayoutInflater inflater;
    AttendanceAdapterListener listener;
    private final int mLayoutRes;

    public AttendanceAdapter(Context context, int resource, List<AttendenceDTO> list, AttendanceAdapterListener listener) {
        super(context, resource, list);
        this.mLayoutRes = resource;
        mList = list;
        this.listener = listener;
        ctx = context;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    Holder h;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(mLayoutRes, null);
            h = new Holder();
            h.statusFlag = (TextView) convertView.findViewById(R.id.statusFlag);
            h.txtStdName = (TextView) convertView.findViewById(R.id.txtStdName);
            h.txtStdMessage = (TextView) convertView.findViewById(R.id.txtStdMessage);
            h.txtStdDate = (TextView) convertView.findViewById(R.id.txtStdDate);
            h.txtCount = (TextView) convertView.findViewById(R.id.txtCount);
            convertView.setTag(h);
        } else {
            h = (Holder) convertView.getTag();
        }
        AttendenceDTO att = mList.get(position);

        h.txtCount.setText("" + (position + 1));
        h.txtStdDate.setText(Util.getLongDate(new Date(att.getDateAttended())));
        h.txtStdName.setText(att.getTeacher().getName() + " " + att.getTeacher().getSurname());
        h.txtStdMessage.setText(att.getMessage());

        if (att.getAttendenceFlag() == 1) {
            Log.w("AttendanceAdapter", att.getDateAttended() + "");
            h.statusFlag.setBackground(ctx.getResources().getDrawable(R.drawable.xsquare_red));
            h.txtCount.setTextAppearance(ctx, R.style.textRoundRed);
        } else if (att.getAttendenceFlag() == 2) {
            Log.w("AttendanceAdapter2", att.getDateAttended() + "");
            h.statusFlag.setBackground(ctx.getResources().getDrawable(R.drawable.xsquare_green));
            h.txtCount.setTextAppearance(ctx, R.style.textRoundGreen);
        } else {
            h.statusFlag.setBackground(ctx.getResources().getDrawable(R.drawable.xsquare_blue));
            h.txtCount.setTextAppearance(ctx, R.style.textRoundBlue);
        }
        animateView(convertView);
        return convertView;
    }

    class Holder {
        TextView statusFlag, txtStdName, txtStdMessage, txtStdDate, txtCount;
    }

    public void animateView(final View view) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.grow_fade_in_center);
        a.setDuration(2000);
        if (view == null)
            return;
        view.startAnimation(a);
    }
}
