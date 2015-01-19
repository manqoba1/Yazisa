package com.sifiso.codetribe.yazisa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sifiso.ylibrary.YLibrary.dto.IssueDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;

import com.sifiso.ylibrary.YLibrary.dto.RequestDTO;

import java.util.Date;


public class ReportLearner extends Activity {
    private Intent i;
    private Button RL_btn_report;
    private EditText RL_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_learner);
//build();
    }

    private void build() {
        i = getIntent();
        final StudentDTO learner = (StudentDTO) i.getSerializableExtra("learner");
        Log.i("LOG", learner.getName());
        RL_btn_report = (Button) findViewById(R.id.RL_btn_report);
        RL_btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* i.putExtra("userID",teacherID);
                i.putExtra("subjectID",subjectID);
                i.putExtra("clazzID",clazzID);
                i.putExtra("learner",learnersDTO);*/

                IssueDTO dto = new IssueDTO();
                dto.setTeacherID(i.getIntExtra("userID", 0));
                dto.setStudentID(learner.getStudentID());
                dto.setIssueDate(new Date().getTime());
                dto.setMessage(RL_message.getText().toString());
            }
        });
        RL_message = (EditText) findViewById(R.id.RL_message);
    }

    private void sendReport(IssueDTO dto) {
        RequestDTO r = new RequestDTO();
        r.setRequestType(RequestDTO.SEND_PARENT_REPORT);
        r.setIssue(dto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.report_learner, menu);
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
    private void codeToEditAFileRow(String oldPrescription){
       RL_message.setText(oldPrescription + "\n\n " +new Date()+"\n");

    }
}
