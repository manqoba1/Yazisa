package com.sifiso.ylibrary.YLibrary.dto.fragment;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sifiso.ylibrary.YLibrary.R;
import com.sifiso.ylibrary.YLibrary.dto.AttendenceDTO;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;
import com.sifiso.ylibrary.YLibrary.dto.adapter.AttendanceAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AttendanceFragment.OnFragmentAttendanceListener} interface
 * to handle interaction events.
 * Use the {@link AttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendanceFragment extends Fragment {


    private OnFragmentAttendanceListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AttendenceFragment.
     */
    // TODO: Rename and change types and number of parameters
    private ResponseDTO response;
    private ListView LV_list;
    private TextView txtNoOfAtt;

    private Context ctx;
    private Activity activity;
    private AttendanceAdapter attendanceAdapter;

    public static AttendanceFragment newInstance(ResponseDTO resp) {
        AttendanceFragment fragment = new AttendanceFragment();
        Bundle args = new Bundle();
        args.putSerializable("response", resp);

        fragment.setArguments(args);
        return fragment;
    }

    public AttendanceFragment() {
        // Required empty public constructor
    }

    private void build() {
        LV_list = (ListView) view.findViewById(R.id.LV_list);
        txtNoOfAtt = (TextView) view.findViewById(R.id.txtNoOfAtt);
        txtNoOfAtt.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });
        txtNoOfAtt.setText("" + response.getStudent().getAttendenceList().size());
        attendanceAdapter = new AttendanceAdapter(ctx, R.layout.attendance_view, response.getStudent().getAttendenceList(), new AttendanceAdapter.AttendanceAdapterListener() {
            @Override
            public void studentClicked(AttendenceDTO attendence) {

            }
        });
        LV_list.setAdapter(attendanceAdapter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_attendence, container, false);
        ctx = getActivity().getApplicationContext();
        activity = getActivity();
        if (getArguments() != null) {
            Bundle b = getArguments();
            response = (ResponseDTO) getArguments().getSerializable("response");
            Log.i("AttendanceFragment", response.getStudent().getEmail());
            build();
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(ResponseDTO resp) {
        if (mListener != null) {
            mListener.onFragmentAttendance(resp);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentAttendanceListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentAttendanceListener {
        // TODO: Update argument type and name
        public void onFragmentAttendance(ResponseDTO resp);
    }

}
