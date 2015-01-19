package com.sifiso.codetribe.yazisa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sifiso.ylibrary.YLibrary.dto.RequestDTO;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;
import com.sifiso.ylibrary.YLibrary.dto.adapter.PagerAdapter;
import com.sifiso.ylibrary.YLibrary.dto.fragment.AttendanceFragment;
import com.sifiso.ylibrary.YLibrary.dto.fragment.BehaviourFragment;
import com.sifiso.ylibrary.YLibrary.dto.fragment.MarkFragment;
import com.sifiso.ylibrary.YLibrary.dto.fragment.PerformFragment;
import com.sifiso.ylibrary.YLibrary.dto.toolbox.BaseVolley;
import com.sifiso.ylibrary.YLibrary.dto.util.SharedUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.Statics;
import com.sifiso.ylibrary.YLibrary.dto.util.ToastUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.WebSocketUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.ZoomOutPageTransformerImpl;

import java.util.ArrayList;
import java.util.List;


public class MainPagerActivity extends FragmentActivity implements AttendanceFragment.OnFragmentAttendanceListener, BehaviourFragment.OnFragmentBehaviourListener {

    boolean refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pager);
        ctx = getApplicationContext();
        mPager = (ViewPager) findViewById(R.id.pager);
        refresh = getIntent().getBooleanExtra("refresh", false);
        student =  SharedUtil.getStudent(ctx);
        if (savedInstanceState != null) {
            response = (ResponseDTO) savedInstanceState
                    .getSerializable("response");
            if (response != null) {
                Log.i("MainPagerActivity", "restored instance state, fragments to be set");
                buildPages();
            }
        }


    }

    private void buildPages() {

        fragmentList = new ArrayList<Fragment>();


        Bundle data1 = new Bundle();
        Log.i("Main", response.getStudent().getEmail());

        data1.putSerializable("response", response);


        markFragment = new MarkFragment();
        markFragment.setArguments(data1);

        attendanceFragment = new AttendanceFragment();
        attendanceFragment.setArguments(data1);

        behaviourFragment = new BehaviourFragment();
        behaviourFragment.setArguments(data1);

        performFragment = new PerformFragment();
        performFragment.setArguments(data1);
        fragmentList.add(attendanceFragment);
        fragmentList.add(behaviourFragment);

        initializeAdapter();

    }

    private void initializeAdapter() {
        adapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        mPager.setAdapter(adapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                currentPageIndex = arg0;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        mPager.setPageTransformer(true, new ZoomOutPageTransformerImpl());
    }

    @Override
    public void onSaveInstanceState(Bundle b) {
        Log.i("MainPagerActivity", "onSaveInstanceState");
        b.putSerializable("response", response);
        super.onSaveInstanceState(b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_pager, menu);
        if (refresh) {
            getData(student.getStudentID());
            return true;
        }
        getData(student.getStudentID());

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        attendanceFragment.onButtonPressed((response==null)?SharedUtil.getResponse(ctx):response);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_refresh) {
            getData(student.getStudentID());
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData(int studentID) {
        RequestDTO w = new RequestDTO();
        w.setRequestType(RequestDTO.GET_STUDENT_BY_ID);
        w.setStudentID(studentID);


        if (!BaseVolley.checkNetworkOnDevice(ctx)) {
            return;
        }
        setRefreshActionButtonState(true);
        WebSocketUtil.sendRequest(ctx, Statics.YAZI_ENDPOINT, w, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(final ResponseDTO r) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        response = r;
                        buildPages();
                        Log.i("MAin", response.getStudent().getAttendenceList().size() + "");
                    }
                });

            }

            @Override
            public void onClose() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setRefreshActionButtonState(false);
                        ToastUtil.errorToast(ctx, "Please Check Your Network Connectivity");
                    }
                });
            }

            @Override
            public void onError(final String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setRefreshActionButtonState(false);
                        ToastUtil.errorToast(ctx, message);
                    }
                });
            }
        });
    }

    public void setRefreshActionButtonState(final boolean refreshing) {
        if (mMenu != null) {
            final MenuItem refreshItem = mMenu.findItem(R.id.action_refresh);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.action_bar_progess);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
    }

    ViewPager mPager;
    Menu mMenu;
    ResponseDTO response;
    int currentPageIndex;
    PagerAdapter adapter;
    private List<Fragment> fragmentList;
    Context ctx;
    boolean isRefresh;
    StudentDTO student;
    List<StudentDTO> studentList;
    private AttendanceFragment attendanceFragment;
    private BehaviourFragment behaviourFragment;
    private MarkFragment markFragment;
    private PerformFragment performFragment;
    SharedPreferences sp;

    @Override
    public void onFragmentAttendance(ResponseDTO resp) {
        student = resp.getStudent();
    }

    @Override
    public void onBehaviourInteraction(ResponseDTO resp) {

    }

}

