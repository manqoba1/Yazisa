package com.sifiso.ylibrary.YLibrary.dto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.sifiso.ylibrary.YLibrary.R;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;

import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-20.
 */
public class ClassAbsentAdaptor extends ArrayAdapter<StudentDTO> {
    public interface AbsentAdaptorListener {
        public void onLateComing(StudentDTO a);
    }

    private final LayoutInflater mInflater;
    private final int mLayoutRes;
    private List<StudentDTO> mList;
    private Context ctx;
    private AbsentAdaptorListener listener;
    private int chooseClass;

    public ClassAbsentAdaptor(Context context, int resource, List<StudentDTO> list, AbsentAdaptorListener listener, int chooseClass) {
        super(context, resource, list);
        this.mLayoutRes = resource;
        mList = list;
        this.chooseClass = chooseClass;
        this.listener = listener;
        ctx = context;
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder h;
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutRes, null);
            h = new Holder();
            h.ABV_absentDate = (TextView) convertView.findViewById(R.id.ABV_absentDate);
            h.ABV_class_subj = (TextView) convertView.findViewById(R.id.ABV_class_subj);
            h.ABV_late = (TextView) convertView.findViewById(R.id.ABV_late);
            h.ABV_name = (TextView) convertView.findViewById(R.id.ABV_name);
            h.ABV_logo = (ImageView) convertView.findViewById(R.id.ABV_logo);
            convertView.setTag(h);

        } else {
            h = (Holder) convertView.getTag();
        }
        StudentDTO dto = mList.get(position);
        // h.ABV_absentDate.setText(new Date(dto.getAbsentDate()).toString());
        h.ABV_name.setText(dto.getName() + " " + dto.getSurname());
        if(chooseClass==1){
            h.ABV_late.setVisibility(TextView.GONE);
        }else{
            h.ABV_late.setVisibility(TextView.VISIBLE);
        }
        // h.ABV_class_subj.setText(dto.getClazzLearner().getClazz().getClassName());
       /* if (dto.getLateForClass() == 1) {
            h.ABV_late.setText("Late for class");
        } else {
            h.ABV_late.setText("Absent");
        }*/
        animateView(convertView);
        return convertView;
    }

    public void animateView(final View view) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.grow_fade_in_center);
        a.setDuration(500);
        if (view == null)
            return;
        view.startAnimation(a);
    }

    class Holder {
        ImageView ABV_logo;
        TextView ABV_name, ABV_late, ABV_class_subj, ABV_absentDate;
    }
}
