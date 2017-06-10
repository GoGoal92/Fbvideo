package gogoal.com.Object;

/**
 * Created by Go Goal on 5/16/2017.
 */

public class Pageurl {

    public static String realurl;
    private static boolean bool;

    public static void sentrealurl(String fbpageurl) {

        realurl=fbpageurl;

    }

    public static String geturl() {
        return realurl;
    }

    public static boolean getbool() {


        return !realurl.equals("0");
    }
}
