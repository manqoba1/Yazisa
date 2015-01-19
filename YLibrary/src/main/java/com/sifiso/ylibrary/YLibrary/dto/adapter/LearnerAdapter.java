package com.sifiso.ylibrary.YLibrary.dto.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sifiso.ylibrary.YLibrary.R;
import com.sifiso.ylibrary.YLibrary.dto.AttendenceDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;
import com.sifiso.ylibrary.YLibrary.dto.util.ToastUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.Util;

import java.util.Date;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class LearnerAdapter extends ArrayAdapter<StudentDTO> {
    public interface LearnerAdapterListener {
        public void onLearnerEditAbsent(StudentDTO learners, int position);

        public void onLearnerEditPresent(StudentDTO learners);

        public void onLearnerEditLateCome(StudentDTO learners);
    }

    private final LayoutInflater mInflater;
    private final int mLayoutRes;
    private List<StudentDTO> mList;
    private Context ctx;
    private LearnerAdapterListener listener;
    private String clazz;

    public LearnerAdapter(Context context, int resource, List<StudentDTO> list, LearnerAdapterListener listener, String clazz) {
        super(context, resource, list);
        this.mLayoutRes = resource;
        mList = list;
        this.clazz = clazz;
        this.listener = listener;
        ctx = context;
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    View view;


    static class ViewHolderItem {
        TextView learnerP, txtabsent, txtpresent, editText2, txtFlagPresent, txtFlagLate, txtFlagAbsent, txtLateCome;

    }

    ViewHolderItem item;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        view = convertView;
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutRes, null);
            item = new ViewHolderItem();
            item.learnerP = (TextView) convertView.findViewById(R.id.learnerP);
            item.txtabsent = (TextView) convertView.findViewById(R.id.txtabsent);
            item.txtpresent = (TextView) convertView.findViewById(R.id.txtpresent);
            item.editText2 = (TextView) convertView.findViewById(R.id.editText2);
            item.txtFlagLate = (TextView) convertView.findViewById(R.id.txtFlagLate);
            item.txtFlagAbsent = (TextView) convertView.findViewById(R.id.txtFlagAbsent);
            item.txtFlagPresent = (TextView) convertView.findViewById(R.id.txtFlagPresent);
            item.txtLateCome = (TextView) convertView.findViewById(R.id.txtLateCome);
            convertView.setTag(item);
        } else {
            item = (ViewHolderItem) convertView.getTag();
        }


        final StudentDTO dto = mList.get(position);

        item.learnerP.setText(dto.getName() + " " + dto.getSurname());
        item.editText2.setText(clazz);
        item.txtFlagAbsent.setText(dto.getCountAbsent() + " Absentees This Year");
        item.txtFlagPresent.setText(dto.getCountAttendant() + " Presents This Year");
        item.txtFlagLate.setText(dto.getCountLate() + " Late comings This Year");
       /* for (int i = 0; i < dto.getAttendenceList().size(); i++) {
            AttendenceDTO attendence = dto.getAttendenceList().get(i);
            if (new Date(attendence.getDateAttended()).getYear() == new Date().getYear() && attendence.getAttendenceFlag() == 1) {

            }
            if (new Date(attendence.getDateAttended()).getYear() == new Date().getYear() && attendence.getAttendenceFlag() == 2) {

            }
            if (new Date(attendence.getDateAttended()).getYear() == new Date().getYear() && attendence.getAttendenceFlag() == 3) {
                item.txtFlagLate.setText(dto.getCountLate() + " Late comings This Year");
            }
        }*/

        item.txtabsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   ToastUtil.toast(ctx, dto.getName() + " " + dto.getSurname());
                listener.onLearnerEditAbsent(dto, position);


            }
        });
        item.txtpresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ToastUtil.toast(ctx, dto.getName() + " " + dto.getSurname());
                listener.onLearnerEditPresent(dto);


            }
        });
        item.txtLateCome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ToastUtil.toast(ctx, dto.getName() + " " + dto.getSurname());
                listener.onLearnerEditLateCome(dto);

            }
        });
        animateView(convertView);
        return convertView;
    }

    public void animateView(final View view) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.grow_fade_in_center);
        a.setDuration(2000);
        if (view == null)
            return;
        view.startAnimation(a);
    }

}
