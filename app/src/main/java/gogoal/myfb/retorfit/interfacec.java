package gogoal.myfb.retorfit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Go Goal on 3/4/2017.
 */
public interface interfacec {


    @FormUrlEncoded
    @POST("my/category")
    Call<String> category(@Field("api") String name);




    @FormUrlEncoded
    @POST("my/latestmovie")
    Call<String> latestmovie(@Field("api") String name,@Field("count") String cc);



    @FormUrlEncoded
    @POST("my/latestseries")
    Call<String> latestseries(@Field("api") String name,@Field("count") String cc);


    @FormUrlEncoded
    @POST("my/getmoviedetail")
    Call<String> getmoviedetail(@Field("api") String name,@Field("id") String cc);


    @FormUrlEncoded
    @POST("my/getserisemaininfo")
    Call<String> getserisemaininfo(@Field("api") String name,@Field("id") String cc);

    @FormUrlEncoded
    @POST("my/getcategory_choice")
    Call<String> getcategory_choice(@Field("api") String name,@Field("id") String cc,@Field("count") String acc);


    @FormUrlEncoded
    @POST("my/getcategory_series")
    Call<String> getcategory_series(@Field("api") String name,@Field("id") String cc,@Field("count") String acc);


    @FormUrlEncoded
    @POST("my/getreadmore")
    Call<String> getreadmore(@Field("api") String name,@Field("id") String cc);



    @FormUrlEncoded
    @POST("my/Registercheckvc2")
    Call<String> Registercheck(@Field("api") String name,@Field("id") String cc);


    @GET("/{id}")
    Call<String> requestfacebookJson(@Path("id") String fbid);
}
