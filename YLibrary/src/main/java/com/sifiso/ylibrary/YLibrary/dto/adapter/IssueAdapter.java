package com.sifiso.ylibrary.YLibrary.dto.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sifiso.ylibrary.YLibrary.R;
import com.sifiso.ylibrary.YLibrary.dto.IssueDTO;
import com.sifiso.ylibrary.YLibrary.dto.util.Util;

import java.util.Date;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-12-10.
 */
public class IssueAdapter extends ArrayAdapter<IssueDTO> {
    public interface IssueAdapterListener {
        public void studentClicked(IssueDTO attendence);
    }

    private List<IssueDTO> mList;
    private Context ctx;
    private Bundle b;
    LayoutInflater inflater;
    IssueAdapterListener listener;
    private final int mLayoutRes;

    public IssueAdapter(Context context, int resource, List<IssueDTO> list) {
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
        IssueDTO att = mList.get(position);

        h.txtCount.setText("" + (mList.size() + 1));
        h.txtStdDate.setText(Util.getLongDate(new Date(att.getIssueDate())));
        h.txtStdName.setText(att.getStudent().getName() + " " + att.getStudent().getSurname());
        h.txtStdMessage.setText(att.getMessage());

        h.statusFlag.setTextAppearance(ctx, R.style.textSquareRed);
        h.statusFlag.setText(att.getTeacher().getName() + " " + att.getTeacher().getSurname());
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
