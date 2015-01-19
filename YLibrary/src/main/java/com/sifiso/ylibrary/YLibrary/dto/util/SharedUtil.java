package com.sifiso.ylibrary.YLibrary.dto.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;


import com.google.gson.Gson;
import com.sifiso.ylibrary.YLibrary.dto.ParentDTO;
import com.sifiso.ylibrary.YLibrary.dto.ResponseDTO;
import com.sifiso.ylibrary.YLibrary.dto.StudentDTO;

import java.util.Date;

public class SharedUtil {
    //storeRegistrationId
    public static final String
            COMPANY_STAFF_JSON = "companyStaff",
            COMPANY_JSON = "company",
            PROJECT_SITE_ID = "siteID",
            GCM_REGISTRATION_ID = "gcm",
            SESSION_ID = "sessionID",
            SITE_LOCATION = "siteLocation",

    LOG = "SharedUtil",
            REMINDER_TIME = "reminderTime",
            APP_VERSION = "appVersion";

    public static StudentDTO getStudent(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String j = sp.getString(Constants.STUDENT_ID, null);
        Gson g = new Gson();
        if (j != null) {
            return g.fromJson(j, StudentDTO.class);
        }

        return null;
    }

    public static void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(LOG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(GCM_REGISTRATION_ID, regId);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
        Log.e(LOG, "GCM registrationId saved in prefs! Yebo!!!");
    }
    public static String getRegistrationId(Context context) {
        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String registrationId = prefs.getString(GCM_REGISTRATION_ID, null);
        if (registrationId == null) {
            Log.i(LOG, "GCM Registration ID not found on device.");
            return null;
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = SharedUtil.getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(LOG, "App version changed.");
            return null;
        }
        return registrationId;
    }
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static ParentDTO getParent(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String j = sp.getString(Constants.PARENT_NAME, null);
        Gson g = new Gson();
        if (j != null) {
            return g.fromJson(j, ParentDTO.class);
        }

        return null;
    }

    public static ResponseDTO getResponse(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String j = sp.getString(Constants.RESPONSE, null);
        Gson g = new Gson();
        if (j != null) {
            return g.fromJson(j, ResponseDTO.class);
        }

        return null;
    }

    public static void setResponse(Context ctx, ResponseDTO student) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Gson g = new Gson();
        String json = g.toJson(student);
        Editor ed = sp.edit();
        ed.putString(Constants.RESPONSE, json);
        ed.commit();

    }

    public static void setPassword(Context ctx, String password) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);

        Editor ed = sp.edit();
        ed.putString(Constants.PASSWORD, password);
        ed.commit();

        Log.w("SharedUtil", "#### web socket session id saved: " + password);

    }

    public static String getPassword(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String j = sp.getString(Constants.PASSWORD, null);

        return j;
    }

    public static void setEmail(Context ctx, String email) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);

        Editor ed = sp.edit();
        ed.putString(Constants.EMAIL, email);
        ed.commit();

        Log.w("SharedUtil", "#### web socket session id saved: " + email);

    }

    public static String getEmail(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String j = sp.getString(Constants.EMAIL, null);

        return j;
    }

    public static void setParentID(Context ctx, int parentID) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);

        Editor ed = sp.edit();
        ed.putInt(Constants.PARENT_ID, parentID);
        ed.commit();

        Log.w("SharedUtil", "#### web socket session id saved: " + parentID);

    }

    public static int getParentID(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        int j = sp.getInt(Constants.PARENT_ID, 0);

        return j;
    }

    public static void setSessionID(Context ctx, String sessionID) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);

        Editor ed = sp.edit();
        ed.putString(Statics.SESSION_ID, sessionID);
        ed.commit();

        Log.w("SharedUtil", "#### web socket session id saved: " + sessionID);

    }

    public static String getSessionID(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String j = sp.getString(Statics.SESSION_ID, null);

        return j;
    }

    public static void setStudent(Context ctx, StudentDTO student) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Gson g = new Gson();
        String json = g.toJson(student);
        Editor ed = sp.edit();
        ed.putString(Constants.STUDENT_ID, json);
        ed.commit();

    }

    public static void setParent(Context ctx, ParentDTO student) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Gson g = new Gson();
        String json = g.toJson(student);
        Editor ed = sp.edit();
        ed.putString(Constants.PARENT_NAME, json);
        ed.commit();

    }

    public static Date getLastCompletionDate(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        long cnt = sp.getLong(Constants.LAST_COMPLETION_DATE, 0);
        if (cnt == 0) return null;
        Date date = new Date(cnt);
        return date;
    }

    public static void setLastCompletionDate(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Editor ed = sp.edit();
        ed.putLong(Constants.LAST_COMPLETION_DATE, new Date().getTime());
        ed.commit();

    }

    public static long getPhotoUploadDate(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        long cnt = sp.getInt(Constants.PHOTO_UPLOAD_DATE, 0);

        return cnt;
    }

    public static void setPhotoUploadDate(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Editor ed = sp.edit();
        ed.putLong(Constants.PHOTO_UPLOAD_DATE, new Date().getTime());
        ed.commit();

    }

    public static int getCoursePressHoldCount(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        int cnt = sp.getInt(Constants.PRESS_HOLD_COURSE_COUNT, 0);

        return cnt;
    }

    public static void incrementCoursePressHoldCount(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        int cnt = sp.getInt(Constants.PRESS_HOLD_COURSE_COUNT, 0);
        cnt++;
        Editor ed = sp.edit();
        ed.putInt(Constants.PRESS_HOLD_COURSE_COUNT, cnt);
        ed.commit();

    }

    public static int getCategoryPressHoldCount(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        int cnt = sp.getInt(Constants.PRESS_HOLD_CATEGORY_COUNT, 0);

        return cnt;
    }

    public static void incrementCategoryPressHoldCount(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        int cnt = sp.getInt(Constants.PRESS_HOLD_CATEGORY_COUNT, 0);
        cnt++;
        Editor ed = sp.edit();
        ed.putInt(Constants.PRESS_HOLD_CATEGORY_COUNT, cnt);
        ed.commit();

    }


    public static String getImageUri(Context ctx) {

        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String s = sp.getString(Constants.IMAGE_URI, null);

        return s;
    }

    public static String getThumbUri(Context ctx) {

        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        String s = sp.getString(Constants.THUMB_URI, null);

        return s;
    }

    public static void saveImageUri(Context ctx, Uri uri) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Editor ed = sp.edit();
        ed.putString(Constants.IMAGE_URI, uri.toString());
        ed.commit();
    }

    public static void saveThumbUri(Context ctx, Uri uri) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Editor ed = sp.edit();
        ed.putString(Constants.THUMB_URI, uri.toString());
        ed.commit();
    }

    public static void saveCalendarID(Context ctx, long id) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        Editor ed = sp.edit();
        ed.putLong(Constants.CALENDAR_ID, id);
        ed.commit();
    }

    public static long getCalendarID(Context ctx) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        long s = sp.getLong(Constants.CALENDAR_ID, -1);
        return s;
    }


    private static final Gson gson = new Gson();
}
