package com.sifiso.codetribe.yazisa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.sifiso.ylibrary.YLibrary.dto.AttendenceDTO;
import com.sifiso.ylibrary.YLibrary.dto.ClazzDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;
import com.sifiso.ylibrary.YLibrary.dto.RequestDTO;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;
import com.sifiso.ylibrary.YLibrary.dto.SubjectDTO;
import com.sifiso.ylibrary.YLibrary.dto.adapter.LearnerAdapter;
import com.sifiso.ylibrary.YLibrary.dto.toolbox.BaseVolley;
import com.sifiso.ylibrary.YLibrary.dto.util.GCMUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.SharedUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.Statics;
import com.sifiso.ylibrary.YLibrary.dto.util.TimerUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.ToastUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.Util;
import com.sifiso.ylibrary.YLibrary.dto.util.WebSocketUtil;

import java.util.ArrayList;
import java.util.List;


public class DailyRegister extends Activity {
    private TextView txtCount, txtCountAbsent, txtcount_present, txtCountLate;
    private ListView LV_learner;
    private Spinner spClass, spSubject;
    private LearnerAdapter learnerAdapter;
    private RelativeLayout l1;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    private String name, surname;
    private int teacherID;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_register);
        ctx = getApplicationContext();
        PackageInfo packageInfo;
        try {
            packageInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            Log.i("PackageName", ctx.getPackageName());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.daily_register, menu);
        mMenu = menu;
        build();
        getActionBar().setSubtitle(name + " " + surname);
        return true;
    }

    int subjectID, classID;
    private String LOG = DailyRegister.class.getSimpleName();
    Gson gson = new Gson();


    private void build() {

        response = SharedUtil.getResponse(ctx);
        name = response.getTeacher().getName();
        surname = response.getTeacher().getSurname();
        teacherID = response.getTeacher().getTeacherID();
        l1 = (RelativeLayout) findViewById(R.id.l1);
        txtCount = (TextView) findViewById(R.id.txtCount);
        txtCountAbsent = (TextView) findViewById(R.id.txtCountAbsent);
        txtCountAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (absenteeDTOs != null) {
                    Intent i = new Intent(DailyRegister.this, Absent.class);
                    i.putExtra("classAbsent", absenteeDTOs);
                    i.putExtra("chooseClass", 0);
                    i.putExtra("userID", teacherID);
                    i.putExtra("subjectID", subjectID);
                    i.putExtra("clazzID", classID);
                    startActivity(i);
                } else {
                    ToastUtil.toast(ctx, "No Absent Learners");
                    //  Toast.makeText(ctx, "No present learners", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtcount_present = (TextView) findViewById(R.id.txtcount_present);
        txtcount_present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presentList != null) {
                    Intent i = new Intent(DailyRegister.this, Present.class);
                    i.putExtra("classAbsent", presentList);
                    i.putExtra("chooseClass", 1);
                    i.putExtra("userID", teacherID);
                    i.putExtra("subjectID", subjectID);
                    i.putExtra("clazzID", classID);
                    startActivity(i);
                } else {
                    ToastUtil.toast(ctx, "No Present Learners");
                    // Toast.makeText(ctx, "No absent learners", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtCountLate = (TextView) findViewById(R.id.txtCountLate);
        LV_learner = (ListView) findViewById(R.id.LV_learner);
        spClass = (Spinner) findViewById(R.id.spClass);
        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    classID = clazzDTOs.get(position - 1).getClazzID();

                    dialogClass(classList.get(position));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSubject = (Spinner) findViewById(R.id.spSubject);
        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
                //   Log.i(LOG, subjectID + " Subject Selected "+subjectDTOs.get(position).getName());


                if (position > 0) {
                    subjectID = subjectDTOs.get(position - 1).getSubjectID();

                    dialogSubject(subjectList.get(position));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setSpinner(response);
    }


    Context ctx;


    private List<ClazzDTO> clazzDTOs;
    private List<SubjectDTO> subjectDTOs;
    private List<String> classList, subjectList;
    private String className;

    private void setSpinner(ResponseDTO r) {
        clazzDTOs = r.getClazzList();
        subjectDTOs = r.getSubjectList();
        classList = new ArrayList<String>();
        subjectList = new ArrayList<String>();
        classList.add("Select Class");
        subjectList.add("Select Subject");

        for (int i = 0; i < clazzDTOs.size(); ++i) {
            classList.add(clazzDTOs.get(i).getClassName());
        }
        for (int i = 0; i < subjectDTOs.size(); ++i) {
            subjectList.add(subjectDTOs.get(i).getName() + " " + subjectDTOs.get(i).getGrade().substring(0, 1) + subjectDTOs.get(i).getGrade().substring(5));
        }
        Log.i("DailyRegister", "Class size" + classList.size());
        Log.i("DailyRegister", "subject size" + subjectList.size());
        arrayAdapter = new ArrayAdapter(ctx, R.layout.spinner_text, classList);
        arrayAdapter1 = new ArrayAdapter(ctx, R.layout.spinner_text, subjectList);
        spSubject.setAdapter(arrayAdapter1);
        spClass.setAdapter(arrayAdapter);
    }

    ArrayAdapter arrayAdapter, arrayAdapter1;


    private void addAbsence(StudentDTO dto) {

        if (absenteeDTOs == null) {
            absenteeDTOs = new ArrayList<StudentDTO>();
        }

        for (int i = 0; i < absenteeDTOs.size(); i++) {
            if (absenteeDTOs.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Absent", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        for (int i = 0; i < presentList.size(); i++) {
            if (presentList.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Present", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        for (int i = 0; i < lateList.size(); i++) {
            if (lateList.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Late", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        // Log.i(LOG,dto.getStudentID() + " "+subjectID+" "+teacherID+" "+flag+" "+message);
        // getAttendanceData(dto.getStudentID(), subjectID, teacherID, 2, "");
        markAttendance(getAttendanceData(dto.getStudentID(), subjectID, teacherID, 1, message("Absent", dto.getCountAttendant(), dto.getCountAbsent(), dto.getCountLate())));
        absenteeDTOs.add(dto);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtCountAbsent.setText("" + absenteeDTOs.size());

            }
        });

    }

    private AttendenceDTO getAttendanceData(int studentID, int subjectID, int teacherID, int flag, String message) {
        Log.i(LOG, studentID + " " + subjectID + " " + teacherID + " " + flag + " " + message);
        AttendenceDTO a = new AttendenceDTO();
        a.setTeacherID(teacherID);
        a.setSubjectID(subjectID);
        a.setAttendenceFlag(flag);
        a.setMessage(message);
        a.setStudentID(studentID);
        return a;
    }

    ArrayList<StudentDTO> presentList;
    ArrayList<StudentDTO> lateList;

    private void addLateCome(StudentDTO dto) {

        if (lateList == null) {
            lateList = new ArrayList<StudentDTO>();
        }
        for (int i = 0; i < lateList.size(); i++) {
            if (lateList.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Late", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        for (int i = 0; i < absenteeDTOs.size(); i++) {
            if (absenteeDTOs.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Absent", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        for (int i = 0; i < presentList.size(); i++) {
            if (presentList.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Present", Toast.LENGTH_SHORT, 25);
                return;
            }
        }

        markAttendance(getAttendanceData(dto.getStudentID(), subjectID, teacherID, 3, message("Late", dto.getCountAttendant(), dto.getCountAbsent(), dto.getCountLate())));
        lateList.add(dto);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtCountLate.setText("" + lateList.size());

            }
        });
    }

    private String message(String status, int pre, int absent, int late) {
        String message = status + " today and Has the attendance Record As follows:\n" + pre + " Presents \n" +
                absent + " Absentees\n " + late + " Lates";
        return message;
    }

    private void addPresent(StudentDTO dto) {

        if (presentList == null) {
            presentList = new ArrayList<StudentDTO>();
        }
        for (int i = 0; i < lateList.size(); i++) {
            if (lateList.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Late", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        for (int i = 0; i < absenteeDTOs.size(); i++) {
            if (absenteeDTOs.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Absent", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        for (int i = 0; i < presentList.size(); i++) {
            if (presentList.get(i).getStudentID() == dto.getStudentID()) {
                ToastUtil.toast(ctx, dto.getName() + " Already Marked Present", Toast.LENGTH_SHORT, 25);
                return;
            }
        }
        markAttendance(getAttendanceData(dto.getStudentID(), subjectID, teacherID, 2,
                message("Present", dto.getCountAttendant(), dto.getCountAbsent(), dto.getCountLate())));
        presentList.add(dto);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtcount_present.setText("" + presentList.size());

            }
        });

    }

    ArrayList<StudentDTO> absenteeDTOs;
    ResponseDTO response;

    private void getData(int classID) {
        RequestDTO w = new RequestDTO();
        w.setRequestType(RequestDTO.GET_LEARNERS);
        w.setClazzID(classID);


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
                        setRefreshActionButtonState(false);
                        txtCount.setText("" + r.getStudentList().size());
                        //dtos = r.getStudentList();

                        if (absenteeDTOs == null) {
                            absenteeDTOs = new ArrayList<StudentDTO>();
                        }
                        if (presentList == null) {
                            presentList = new ArrayList<StudentDTO>();
                        }
                        if (lateList == null) {
                            lateList = new ArrayList<StudentDTO>();
                        }

                        txtCountAbsent.setText("" + absenteeDTOs.size());
                        txtcount_present.setText("" + presentList.size());
                        txtCountLate.setText("" + lateList.size());
                        learnerAdapter = new LearnerAdapter(ctx, R.layout.learner_view, r.getStudentList(), new LearnerAdapter.LearnerAdapterListener() {
                            @Override
                            public void onLearnerEditAbsent(StudentDTO learners, int position) {
                                // dtos.remove(learners);
                                if (subjectID <= 0) {
                                    ToastUtil.errorToast(ctx, "Please select subject to mark register");
                                    return;
                                }
                                addAbsence(learners);

                                // learnerAdapter.notifyDataSetChanged();
                                animate();
                            }

                            @Override
                            public void onLearnerEditPresent(StudentDTO learners) {
                                // dtos.remove(learners);
                                if (subjectID <= 0) {
                                    ToastUtil.errorToast(ctx, "Please select subject to mark register");
                                    return;
                                }
                                addPresent(learners);
                                // learnerAdapter.notifyDataSetChanged();
                                animateP();
                            }

                            @Override
                            public void onLearnerEditLateCome(StudentDTO learners) {
                                if (subjectID <= 0) {
                                    ToastUtil.errorToast(ctx, "Please select subject to mark register");
                                    return;
                                }
                                addLateCome(learners);
                                animateL();
                                // learnerAdapter.notifyDataSetChanged();
                            }
                        }, className);


                        LV_learner.setAdapter(learnerAdapter);
                        LV_learner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                studentDTO = response.getStudentList().get(position);
                                Toast.makeText(ctx, studentDTO.getName(), Toast.LENGTH_LONG).show();
                            }
                        });
                        //TODO - use for refresh
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

    private void getTeacherData(int teacherID) {
        RequestDTO w = new RequestDTO();
        w.setRequestType(RequestDTO.GET_SUB_CLASS_BY_TEACHER);
        w.setTeacherID(teacherID);


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
                        setRefreshActionButtonState(false);

                        setSpinner(response);

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

    private void animate() {
        //  Util.animateSlideRight(LV_learner, 500);
        // Util.animateRotationY(txtcount_present, 500);
        Util.animateRotationY(txtCountAbsent, 500);
    }

    private void animateP() {
        //Util.animateSlideRight(LV_learner, 500);
        Util.animateRotationY(txtcount_present, 500);
        // Util.animateRotationY(txtCountAbsent, 500);
    }

    private void animateL() {
        //Util.animateSlideRight(LV_learner, 500);
        Util.animateRotationY(txtCountLate, 500);
        // Util.animateRotationY(txtCountAbsent, 500);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_refresh) {

            if (classID > 0) {
                getData(classID);


            } else {
                ToastUtil.errorToast(ctx, "Please select class to refresh");
            }

            if (teacherID > 0) {
                getTeacherData(teacherID);

            } else {
                ToastUtil.errorToast(ctx, "Please select subject to refresh");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    StudentDTO studentDTO;

    // this are the local methods to make the app work locally


    boolean first = false;
    int min;

    private void dialogSubject(final String subject) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLeft(16);
        layout.setTop(16);
        layout.setBottom(16);
        final EditText name = new EditText(this);
        name.setInputType(InputType.TYPE_CLASS_NUMBER);
        name.setHint("Class duration(in minutes)");
        layout.addView(name);

        new AlertDialog.Builder(DailyRegister.this)
                .setTitle("Ready for " + subject)
                        // entry field for user
                .setView(layout)
                .setPositiveButton("Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                spSubject.setEnabled(false);
                                spSubject.setVisibility(Spinner.GONE);

                                if (subjectID > 0 && classID > 0) {
                                    spClass.setVisibility(Spinner.GONE);
                                    l1.setVisibility(RelativeLayout.GONE);
                                }
                                if (name.getText() != null) {
                                    min = Integer.parseInt(name.getText().toString());
                                    absenteeDTOs = new ArrayList<StudentDTO>();
                                    presentList = new ArrayList<StudentDTO>();
                                    lateList = new ArrayList<StudentDTO>();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Please Enter Class Duration", Toast.LENGTH_SHORT).show();
                                }
                                first = false;

                                TimerUtil.startClass(new TimerUtil.TimerListener() {
                                    @Override
                                    public void onSessionDisconnected() {

                                    }

                                    @Override
                                    public void classOut() {
                                        if (first) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    spSubject.setEnabled(true);
                                                    spClass.setEnabled(true);
                                                    spSubject.setSelection(0);

                                                    subjectID = 0;


                                                    l1.setVisibility(RelativeLayout.VISIBLE);
                                                    spSubject.setVisibility(Spinner.VISIBLE);
                                                    spClass.setVisibility(Spinner.VISIBLE);
                                                    TimerUtil.killTimer();
                                                    ToastUtil.toast(ctx, "Class Ended");
                                                    Log.d(LOG, "Class Ended");
                                                    first = false;
                                                }
                                            });

                                        }
                                        first = true;
                                    }
                                }, (min * 6000));
                            }
                        }).show();
    }

    private void dialogClass(final String clazz) {


        new AlertDialog.Builder(DailyRegister.this)
                .setTitle("Ready for " + clazz)
                        // entry field for user

                .setPositiveButton("Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                spClass.setEnabled(false);
                                spClass.setVisibility(Spinner.GONE);
                                if (subjectID > 0 && classID > 0) {
                                    l1.setVisibility(RelativeLayout.GONE);
                                }

                                absenteeDTOs = new ArrayList<StudentDTO>();
                                presentList = new ArrayList<StudentDTO>();
                                lateList = new ArrayList<StudentDTO>();
                                getData(classID);


                            }
                        }).show();
    }

    @Override
    public void onBackPressed() {
        Log.i(LOG, first + "");
        if (first == false
                ) {
            super.onBackPressed();
        }
    }

    private void markAttendance(AttendenceDTO a) {
        if (subjectID <= 0) {
            ToastUtil.toast(ctx, "Please select the subject to mark register");
            spSubject.setFocusable(true);

            return;
        }
        RequestDTO w = new RequestDTO();
        w.setRequestType(RequestDTO.REGISTER_ATTENDENCE);
        w.setAttendence(a);

        if (!BaseVolley.checkNetworkOnDevice(ctx)) {
            return;
        }

        WebSocketUtil.sendRequest(ctx, Statics.YAZI_ENDPOINT, w, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(final ResponseDTO response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ToastUtil.toast(ctx, response.getMessage());
                    }
                });

            }

            @Override
            public void onClose() {

            }

            @Override
            public void onError(String message) {
                // ToastUtil.errorToast(ctx, message);
                Log.e(LOG, message);
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
}
