package com.sifiso.codetribe.yazisa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;
import com.sifiso.ylibrary.YLibrary.dto.adapter.ParentFirstViewAdapter;
import com.sifiso.ylibrary.YLibrary.dto.util.SharedUtil;


public class ParentFirstView extends Activity {
    private ListView PFV_list;
    private ParentFirstViewAdapter parentFirstViewAdapter;
    private Context ctx;
    ResponseDTO response;
    SharedPreferences sp;
    Gson gson = new Gson();
    private StudentDTO studentDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getApplicationContext();
        setContentView(R.layout.activity_parent_first_view);
        response = SharedUtil.getResponse(ctx);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setTitle("My Kids");
        //ed = sp.edit();
    }

    private void build() {
        PFV_list = (ListView) findViewById(R.id.PFV_list);

        getActionBar().setSubtitle(response.getParent().getParentSurname() + " " + response.getParent().getParentName());
        if (response != null) {
            parentFirstViewAdapter = new ParentFirstViewAdapter(ctx, R.layout.marked_view, response.getParent().getStudentList(), new ParentFirstViewAdapter.ParentFirstViewListener() {
                @Override
                public void onImageListener(StudentDTO student) {

                }

                @Override
                public void onSmallButtonListener(StudentDTO student) {
                    studentDTO = student;
                    SharedUtil.setStudent(ctx, student);
                    Intent intent = new Intent(ParentFirstView.this, MainPagerActivity.class);
                    startActivity(intent);
                }
            });
            PFV_list.setAdapter(parentFirstViewAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parent_first_view, menu);
        build();
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
}
