package com.sifiso.ylibrary.YLibrary.dto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.sifiso.ylibrary.YLibrary.dto.SubjectDTO;

import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-23.
 */
public class SubjectAdapter extends ArrayAdapter<SubjectDTO>{
    private final LayoutInflater mInflater;
    private final int mLayoutRes;
    private List<SubjectDTO> mList;
    private Context ctx;
    private SubjectAdapterListener listener;

    public SubjectAdapter(Context context, int resource, List<SubjectDTO> list,SubjectAdapterListener listener) {
        super(context, resource, list);
        this.mLayoutRes = resource;
        mList = list;
        this.listener = listener;
        ctx = context;
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public interface SubjectAdapterListener{

    }


}
