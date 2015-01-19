package com.sifiso.codetribe.yazisa;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.sifiso.ylibrary.YLibrary.dto.GcmdeviceDTO;
import com.sifiso.ylibrary.YLibrary.dto.RequestDTO;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;
import com.sifiso.ylibrary.YLibrary.dto.toolbox.BaseVolley;
import com.sifiso.ylibrary.YLibrary.dto.util.GCMUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.SharedUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.Statics;
import com.sifiso.ylibrary.YLibrary.dto.util.ToastUtil;
import com.sifiso.ylibrary.YLibrary.dto.util.WebSocketUtil;

import java.util.ArrayList;


public class Login extends Activity {
    Spinner spinnerEmail;
    EditText LGI_edtUsername, LGI_edtPassword;
    ImageView LGI_btnSign_in;
    Context ctx;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String email;
    GcmdeviceDTO gcmDevice;
    String gcmRegistrationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ctx = getApplicationContext();
        setField();
        getEmail();
    }

    private void setField() {
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        //LGI_edtUsername = (EditText) findViewById(R.id.LGI_edtUsername);
        LGI_edtPassword = (EditText) findViewById(R.id.LGI_edtPassword);
        spinnerEmail = (Spinner) findViewById(R.id.LGI_spinnerEmail);
        LGI_btnSign_in = (ImageView) findViewById(R.id.LGI_btnSign_in);
        LGI_btnSign_in.setOnClickListener(login);
        checkVirginity();
    }

    private void registerDevice() {
        GCMUtil.startGCMRegistration(ctx, new GCMUtil.GCMUtilListener() {
            @Override
            public void onGCMError() {
                Log.e(LOG, "Error registering device for gcm");
            }

            @Override
            public void onDeviceRegistered(String regID) {
                gcmRegistrationID = regID;
                Log.w(LOG, "onDeviceRegistered - stored in variable: " + regID);
                gcmDevice = new GcmdeviceDTO();
                gcmDevice.setRegistrationID(gcmRegistrationID);
                gcmDevice.setAndroidVersion(Build.VERSION.CODENAME);
                gcmDevice.setModel(Build.MODEL);
                gcmDevice.setManufacturer(Build.MANUFACTURER);
                gcmDevice.setSerialNumber(Build.SERIAL);
                gcmDevice.setProduct(Build.DISPLAY);
            }
        });
    }

    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(LOG, email + "  " + LGI_edtPassword.getText().toString());

            if(!email.equals("") && !LGI_edtPassword.getText().toString().equals("")) {
                userLogIn(email, LGI_edtPassword.getText().toString());
            }
        }
    };

    static String LOG = Login.class.getSimpleName();

    private void checkVirginity() {
        //ed.clear().commit();
        int teacherID = SharedUtil.getResponse(ctx).getTeacher().getTeacherID();
        if (teacherID > 0) {
            startActivity(new Intent(Login.this, DailyRegister.class));
            finish();
        }
        GCMUtil.checkPlayServices(ctx, this);
        registerDevice();
    }

    Gson gson = new Gson();

    private void userLogIn(String email, String password) {
        if (!BaseVolley.checkNetworkOnDevice(ctx)) {
            return;
        }

        RequestDTO r = new RequestDTO();
        r.setRequestType(RequestDTO.LOGIN_TEACHER);
        r.setPassword(password);
        r.setEmail(email);
        Log.d(LOG, "**** Stupid ****" + password + " " + email);
        try {
            WebSocketUtil.sendRequest(ctx, Statics.YAZI_ENDPOINT, r, new WebSocketUtil.WebSocketListener() {
                @Override
                public void onMessage(ResponseDTO r) {
                    if (r.getStatusCode() == 0) {
                        SharedUtil.setEmail(ctx,r.getTeacher().getEmail());
                        SharedUtil.setPassword(ctx,r.getTeacher().getPassword());
                        SharedUtil.setResponse(ctx,r);

                        startActivity(new Intent(Login.this, DailyRegister.class));
                        finish();
                    }
                }

                @Override
                public void onClose() {

                }

                @Override
                public void onError(String message) {

                }
            });
        } catch (Exception e) {
            Log.d(LOG, "**** Stupid ****", e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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

    public void getEmail() {
        AccountManager am = AccountManager.get(getApplicationContext());
        Account[] accts = am.getAccounts();
        if (accts.length == 0) {
            //TODO - send user to create acct
            ToastUtil.errorToast(ctx, "No Accounts found. Please create one and try again");
            finish();
            return;
        }
        if (accts.length == 1) {
            email = accts[0].name;
            spinnerEmail.setVisibility(View.GONE);
            return;
        }
        final ArrayList<String> tarList = new ArrayList<String>();
        if (accts != null) {

            for (int i = 0; i < accts.length; i++) {
                tarList.add(accts[i].name);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    R.layout.xsimple_spinner_item, tarList);
            dataAdapter
                    .setDropDownViewResource(R.layout.xsimple_spinner_dropdown_item);
            spinnerEmail.setAdapter(dataAdapter);
            spinnerEmail
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0,
                                                   View arg1, int index, long arg3) {
                            Log.w("RegistrationActivity", "###### Email account index is " + index);
                            /*if (index == 0) {
                                email = null;
                                return;
                            }*/
                            email = tarList.get(index);
                            Log.e("RegistrationActivity", "###### Email account selected is "
                                    + email);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {

                        }
                    });
        }
    }
}
