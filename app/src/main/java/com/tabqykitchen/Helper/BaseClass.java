package com.tabqykitchen.Helper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.widget.Toast;

import com.tabqykitchen.R;

import java.util.regex.Pattern;

public class BaseClass {

    public static Boolean checkemail(final String email) {
        if(email!=null)
        {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            if(pattern.matcher(email).matches())
            {
                return pattern.matcher(email).matches();
            }
        }
        return false;
    }

    private static Toast t;
    public static void showToast(Context context, String msg){
        if(t != null)
            t.cancel();
        t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.show();
    }

    public static void callFragment(Fragment fragment, Bundle bundle, FragmentManager manager  ){
        String tag = fragment.getClass().getName();

        if(manager.getBackStackEntryCount() != 0) {
            String tag1 = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            if (!tag1.equals(tag)) {

                if(bundle != null)
                    fragment.setArguments(bundle);

                if (manager.findFragmentByTag(tag) == null) { //fragment not in back stack, create it.
                    manager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(tag).commit();

                } else {
                    manager.beginTransaction().replace(R.id.frame, fragment).commit();
                }
            } else {
                // Calling the same fragment already visible
            }
        } else{
            manager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(tag).commit();
        }
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    public static int calculateNoOfRows(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        int noOfRows = (int) (dpHeight / 180);
        return noOfRows;
    }
}