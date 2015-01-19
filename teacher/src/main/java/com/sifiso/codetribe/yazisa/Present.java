package com.sifiso.codetribe.yazisa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.sifiso.ylibrary.YLibrary.dto.AttendenceDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;
import com.sifiso.ylibrary.YLibrary.dto.adapter.ClassAbsentAdaptor;

import java.util.ArrayList;


public class Present extends Activity {
    private TextView P_count;
    private ListView P_ls_class_absent;
    private Button P_btn_save;
    private ArrayList<StudentDTO> studentDTOList;
    private ClassAbsentAdaptor adaptor;
    private Context ctx;
    private String name, surname;

    private int teacherID, subjectID, clazzID;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);
        ctx = getApplicationContext();
        build();
    }

    private void build() {
        i = getIntent();
        teacherID = i.getIntExtra("userID", 0);
        subjectID = i.getIntExtra("subjectID", 0);
        clazzID = i.getIntExtra("clazzID", 0);
        P_count = (TextView) findViewById(R.id.P_count);

        P_btn_save = (Button) findViewById(R.id.P_btn_save);
        P_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  savePresentLearners(studentDTOList);
            }
        });
        P_ls_class_absent = (ListView) findViewById(R.id.P_ls_class_absent);
        Log.w("Absent", "Hello " + teacherID + " " + subjectID + " " + clazzID);
        studentDTOList = new ArrayList<StudentDTO>();
        studentDTOList = (ArrayList<StudentDTO>) getIntent().getSerializableExtra("classAbsent");
        P_count.setText(studentDTOList.size() + "");


        adaptor = new ClassAbsentAdaptor(ctx, R.layout.absent_view, studentDTOList, new ClassAbsentAdaptor.AbsentAdaptorListener() {
            @Override
            public void onLateComing(StudentDTO a) {

            }
        }, 1
        );
        P_ls_class_absent.setAdapter(adaptor);
        registerForContextMenu(P_ls_class_absent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.present, menu);
        return true;
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.w(LOG, "onCreateContextMenu ...");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.daily_context_menu, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        studentDTO = studentDTOList.get(info.position);
        // learnersDTO = response.getLearnersList().get(info.position);
        if (studentDTO == null) throw new UnsupportedOperationException("zeInstructor is null");
        menu.setHeaderTitle(studentDTO.getName() + " "
                + studentDTO.getSurname());
        menu.setHeaderIcon(ctx.getResources().getDrawable(R.drawable.ic_launcher));


    }

    StudentDTO studentDTO, studentDTOs;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.w(LOG, "onContextItemSelected");
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();

        switch (item.getItemId()) {

            case R.id.menu_list_report:
                //TODO - make sizes dynamic
                Intent i = new Intent(Present.this,ReportLearner.class);
                i.putExtra("userID",teacherID);
                i.putExtra("subjectID",subjectID);
                i.putExtra("clazzID",clazzID);
               i.putExtra("learner", studentDTO);
                startActivity(i);
                return true;
            case R.id.menu_update_absence:

            default:
                return super.onContextItemSelected(item);
        }
    }

    private ArrayList<AttendenceDTO> attendenceList = new ArrayList<AttendenceDTO>();

   /* private void savePresentLearners(ArrayList<StudentDTO> dtos) {
        for (StudentDTO l : dtos) {
            AttendenceDTO dto = new AttendenceDTO();
            dto.setDateAttended(new Date().getTime());
            dto.setClazzID(clazzID);
            dto.setSubjectID(subjectID);
            dto.setTearcherID(teacherID);
            dto.setLearnersID(l.getLearnersID());
            attendenceList.add(dto);
        }
        RequestDTO w = new RequestDTO();
        w.setRequestType(RequestDTO.REGISTER_PRESENTS);
        w.setAttendenceList(attendenceList);

        if (!BaseVolley.checkNetworkOnDevice(ctx)) {
            return;
        }
        WebSocketUtil.sendRequest(ctx, Statics.YAZI_ENDPOINT, w, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(ResponseDTO r) {
                if (r.getStatusCode() == 0) {
                    ToastUtil.toast(ctx, "Present Register Marked");
                }
            }

            @Override
            public void onClose() {
                ToastUtil.errorToast(ctx, "Please Check Your Network Connectivity");
                Log.w(LOG, "Socket closed");
            }

            @Override
            public void onError(String message) {

                ToastUtil.errorToast(ctx, message);
                Log.w(LOG, message);
            }
        });
    }*/

    private String LOG = Present.class.getSimpleName();
}
