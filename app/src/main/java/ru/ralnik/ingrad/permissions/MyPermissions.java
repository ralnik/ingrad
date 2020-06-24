package ru.ralnik.ingrad.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import 	androidx.core.content.ContextCompat;

public class MyPermissions {
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Context context;
    private Activity activity;

    public MyPermissions(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
       /*
        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)    ) {

        } else{
            if (!verifyStoragePermissions(activity)){
                Toast toast3 = Toast.makeText(context, "Ошибка при разрешение прав на запись", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.CENTER, 0, 0);
                toast3.show();
            }
        }
        */
    }



    public boolean verifyStoragePermissions() {
        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)    ) {
            return true;
        }
        // Check if we have write permission
        int permissionREAD = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWRITE = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if ((permissionREAD != PackageManager.PERMISSION_GRANTED) || (permissionWRITE != PackageManager.PERMISSION_GRANTED)){
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return true;
        }
        return false;
    }
}
