package gogoal.com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Go Goal on 5/24/2017.
 */

public class Downloadlist {

    public static ArrayList<String> list=new ArrayList<>();
    private static AppCompatActivity context;
    static SharedPreferences prefs;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void addlist(String dnfilename) {


        list.add(dnfilename);
        Set<String> set = new HashSet<String>();
        set.addAll(list);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("dlist", set);
        editor.commit();



    }

    public static ArrayList<String> getlist() {

        list=new ArrayList<>();
        try{
            Set<String> set = prefs.getStringSet("dlist", null);
            list.addAll(set);
        }catch (Exception e){

        }

        return list;
    }

    public static void removelist(ArrayList<String> mylist) {
        //list.remove(position);
        list=mylist;
        Set<String> set = new HashSet<String>();
        set.addAll(list);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("dlist", set);
        editor.commit();

    }

    public static void setcontext(AppCompatActivity context) {
        Downloadlist.context = context;
        prefs = context.getSharedPreferences("dlist",
                Context.MODE_PRIVATE);
    }
}
