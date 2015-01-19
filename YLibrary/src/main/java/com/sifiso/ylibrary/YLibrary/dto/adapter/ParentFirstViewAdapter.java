package com.sifiso.ylibrary.YLibrary.dto.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.sifiso.ylibrary.YLibrary.R;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;
import com.sifiso.ylibrary.YLibrary.dto.util.Statics;

import java.util.List;

/**
 * Created by CodeTribe1 on 2015-01-06.
 */
public class ParentFirstViewAdapter extends ArrayAdapter<StudentDTO> {
    public interface ParentFirstViewListener {
        public void onImageListener(StudentDTO student);

        public void onSmallButtonListener(StudentDTO student);
    }

    private List<StudentDTO> mList;
    private Context ctx;
    private Bundle b;
    LayoutInflater inflater;
    ParentFirstViewListener listener;
    private final int mLayoutRes;

    public ParentFirstViewAdapter(Context context, int resource, List<StudentDTO> list, ParentFirstViewListener listener) {
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
            h.MV_count = (TextView) convertView.findViewById(R.id.MV_count);
            h.MV_logo = (ImageView) convertView.findViewById(R.id.MV_logo);
            h.MV_name = (TextView) convertView.findViewById(R.id.MV_name);
            convertView.setTag(h);
        } else {
            h = (Holder) convertView.getTag();
        }
        final StudentDTO dto = mList.get(position);
        h.MV_name.setText(dto.getSurname()+", " + dto.getName());
        h.MV_count.setText("" + (position + 1));
        h.MV_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSmallButtonListener(dto);
                System.out.println(dto.getEmail());
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(Statics.IMAGE_URL);
        sb.append("student/t").append(dto.getStudentID()).append(".jpg");


        System.out.println(sb.toString());
        /*ImageLoader.getInstance().displayImage(sb.toString(),h.MV_logo,new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                h.MV_logo.setImageDrawable(ctx.getResources().getDrawable(R.drawable.users32));
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });*/
        animateView(convertView);
        return convertView;
    }

    class Holder {
        TextView MV_name, MV_count;
        ImageView MV_logo;
    }

    public void animateView(final View view) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.grow_fade_in_center);
        a.setDuration(2000);
        if (view == null)
            return;
        view.startAnimation(a);
    }
}
