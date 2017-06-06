package gogoal.myfb.Object;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Go Goal on 4/18/2017.
 */

public class Jsonparser {


    public static String getonestring(String str, String status) {
        JSONObject jo = null;
        String result = "";
        try {
            jo = new JSONObject(str);
            result = jo.getString(status);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }





    public static ArrayList<get> getobjectlist(String s) {
        ArrayList<get> strlist = new ArrayList<>();

        try {

            JSONArray id = new JSONArray(s);
            for (int i = 0; i < id.length(); i++) {
                JSONObject c1 = id.getJSONObject(i);
                get eg=new get();
                eg.setCaname(c1.getString("type"));
                eg.setCaid(c1.getString("id"));


                strlist.add(eg);
            }


        } catch (Exception e) {

        }

        return strlist;
    }


    public static ArrayList<get> getMovielist(String s) {
        ArrayList<get> strlist = new ArrayList<>();

        try {

            JSONArray id = new JSONArray(s);
            for (int i = 0; i < id.length(); i++) {
                JSONObject c1 = id.getJSONObject(i);
                get eg=new get();
                eg.setMoviename(c1.getString("title"));
                eg.setMovieid(c1.getString("id"));
                eg.setMovieimgurl(c1.getString("imgurl"));


                strlist.add(eg);
            }


        } catch (Exception e) {

        }

        return strlist;
    }

    public static ArrayList<get> getMovielist_ca(String s) {
        ArrayList<get> strlist = new ArrayList<>();

        try {

            JSONObject nn=new JSONObject(s);

            JSONArray id = nn.getJSONArray("arr");
            for (int i = 0; i < id.length(); i++) {
                JSONObject c1 = id.getJSONObject(i);
                get eg=new get();
                eg.setMoviename(c1.getString("title"));
                eg.setMovieid(c1.getString("id"));
                eg.setMovieimgurl(c1.getString("imgurl"));
                eg.setMovietype(c1.getString("type"));



                strlist.add(eg);
            }


        } catch (Exception e) {

        }

        return strlist;
    }

    public static  ArrayList<get>  addMovielist(String s, ArrayList<get> list) {

        try {

            JSONObject jojo=new JSONObject(s);
            JSONArray id = jojo.getJSONArray("arr");
            for (int i = 0; i < id.length(); i++) {
                JSONObject c1 = id.getJSONObject(i);
                get eg=new get();
                eg.setMoviename(c1.getString("title"));
                eg.setMovieid(c1.getString("id"));
                eg.setMovieimgurl(c1.getString("imgurl"));


                list.add(eg);
            }


        } catch (Exception e) {

        }
        return  list;

    }

    public static ArrayList<get> addMovielist_ser(String s, ArrayList<get> list) {

        try {

            JSONObject jojo=new JSONObject(s);
            JSONArray id = jojo.getJSONArray("arr");
            for (int i = 0; i < id.length(); i++) {
                JSONObject c1 = id.getJSONObject(i);
                get eg=new get();
                eg.setMoviename(c1.getString("title"));
                eg.setMovieid(c1.getString("id"));
                eg.setMovieimgurl(c1.getString("imgurl"));
                eg.setMoviepisode(c1.getString("episode"));


                list.add(eg);
            }


        } catch (Exception e) {

        }
        return  list;
    }


    public static ArrayList<get> getserieslistrow(String s) {
        ArrayList<get> strlist = new ArrayList<>();

        try {

            JSONObject nn=new JSONObject(s);

            JSONArray id = nn.getJSONArray("arr");
            for (int i = 0; i < id.length(); i++) {
                JSONObject c1 = id.getJSONObject(i);
                get eg=new get();
                eg.setMoviename(c1.getString("title"));
                eg.setMovieid(c1.getString("id"));
                eg.setMovieimgurl(c1.getString("imgurl"));
                eg.setMovietype(c1.getString("type"));
                eg.setMoviepisode(c1.getString("episode"));



                strlist.add(eg);
            }


        } catch (Exception e) {

        }

        return strlist;
    }



}
