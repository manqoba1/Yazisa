package com.sifiso.codetribe.yazisa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;


import com.sifiso.ylibrary.YLibrary.dto.AttendenceDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;
import com.sifiso.ylibrary.YLibrary.dto.adapter.ClassAbsentAdaptor;

import java.util.ArrayList;


public class Absent extends Activity {
    private TextView AB_count;
    private ListView AB_ls_class_absent;
    private Button Ab_save;
    private ArrayList<StudentDTO> studentDTOList;
    private ClassAbsentAdaptor adaptor;
    private Context ctx;
    private String name, surname;
    private int teacherID, subjectID, clazzID;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent);
        ctx = getApplicationContext();
        build();
    }

    private void build() {
        i = getIntent();
        teacherID = i.getIntExtra("userID", 0);
        subjectID = i.getIntExtra("subjectID", 0);
        clazzID = i.getIntExtra("clazzID", 0);
        AB_count = (TextView) findViewById(R.id.AB_count);
        Ab_save = (Button) findViewById(R.id.Ab_save);
        Ab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  saveAbsentLearners(studentDTOList);
            }
        });
        AB_ls_class_absent = (ListView) findViewById(R.id.AB_ls_class_absent);
        Log.w("Absent", "Hello " + teacherID + " " + subjectID + " " + clazzID);
        studentDTOList = new ArrayList<StudentDTO>();
        studentDTOList = (ArrayList<StudentDTO>) getIntent().getSerializableExtra("classAbsent");
        AB_count.setText(studentDTOList.size() + "");


        adaptor = new ClassAbsentAdaptor(getApplicationContext(), R.layout.absent_view, studentDTOList, new ClassAbsentAdaptor.AbsentAdaptorListener() {
            @Override
            public void onLateComing(StudentDTO a) {

            }
        }, 0);
        AB_ls_class_absent.setAdapter(adaptor);
        registerForContextMenu(AB_ls_class_absent);
    }

    private void setFields() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.absent, menu);
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

    StudentDTO studentDTO;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.w(LOG, "onContextItemSelected");
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {

            case R.id.menu_list_report:
                //TODO - make sizes dynamic
                Toast.makeText(ctx, "Wait Patience Rewards", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_update_absence:

            default:
                return super.onContextItemSelected(item);
        }
    }

    private ArrayList<AttendenceDTO> absenteeList = new ArrayList<AttendenceDTO>();

    /*private void saveAbsentLearners(ArrayList<StudentDTO> dtos) {
        for (StudentDTO l : dtos) {
            AbsenteeDTO dto = new AbsenteeDTO();
            dto.setAbsentDate(new Date().getTime());
            dto.setClazzID(clazzID);
            dto.setLateForClass(0);
            dto.setSubjectID(subjectID);
            dto.setTearcherID(teacherID);
            dto.setLearnersID(l.getLearnersID());
            absenteeList.add(dto);
        }
        RequestDTO w = new RequestDTO();
        w.setRequestType(RequestDTO.REGISTER_ABSENSEE);
        w.setAbsenteeList(absenteeList);

        if (!BaseVolley.checkNetworkOnDevice(ctx)) {
            return;
        }
        WebSocketUtil.sendRequest(ctx, Statics.YAZI_ENDPOINT, w, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(ResponseDTO r) {
                if (r.getStatusCode() == 0) {
                    ToastUtil.toast(ctx, "Absent Register Marked");
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
