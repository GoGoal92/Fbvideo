package gogoal.com.retorfit;

import gogoal.com.Categorychoice;
import gogoal.com.Fragment.Fragment_Movies;
import gogoal.com.Fragment.Fragment_Series;
import gogoal.com.MainActivity;
import gogoal.com.Moviedetail;
import gogoal.com.Object.Constant;
import gogoal.com.Object.Stringconverter;
import gogoal.com.Readmore;
import gogoal.com.Series_ca_detail;
import gogoal.com.Seriesdetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Go Goal on 5/6/2017.
 */
public class Myclient {

    public static void getcategory(String a) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.category(a);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    MainActivity.Feedback(response.body().toString());
                } catch (Exception e) {
                    MainActivity.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MainActivity.Feedback_Error();
                t.printStackTrace();
            }
        });

    }


    public static void getmovielist(String api,String count) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.latestmovie(api,count);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Fragment_Movies.Feedback(response.body().toString());
                } catch (Exception e) {
                    Fragment_Movies.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Fragment_Movies.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getserieslist(String api, String count) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.latestseries(api,count);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Fragment_Series.Feedback(response.body().toString());
                } catch (Exception e) {
                    Fragment_Series.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Fragment_Series.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getmoviedetail(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.getmoviedetail(Constant.apikey,id);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Moviedetail.Feedback(response.body().toString());
                } catch (Exception e) {
                    Moviedetail.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Moviedetail.Feedback_Error();
                t.printStackTrace();
            }
        });
    }


    public static void getserisemaininfo(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.getserisemaininfo(Constant.apikey,id);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Seriesdetail.Feedback(response.body().toString());
                } catch (Exception e) {
                    Seriesdetail.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Seriesdetail.Feedback_Error();
                t.printStackTrace();
            }
        });

    }

    public static void getcategory_choice(String id,String count) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.getcategory_choice(Constant.apikey,id,count);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Categorychoice.Feedback(response.body().toString());
                } catch (Exception e) {
                    Categorychoice.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Categorychoice.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getmovielist_refresh(String apikey, String s) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.latestmovie(apikey,s);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Fragment_Movies.Feedback_refresh(response.body().toString());
                } catch (Exception e) {
                    Fragment_Movies.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Fragment_Movies.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getserieslist_refresh(String apikey, String s) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.latestseries(apikey,s);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Fragment_Series.Feedback_refresh(response.body().toString());
                } catch (Exception e) {
                    Fragment_Series.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Fragment_Series.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getcategory_series(String id, String count) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.getcategory_series(Constant.apikey,id,count);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Series_ca_detail.Feedback(response.body().toString());
                } catch (Exception e) {
                    Series_ca_detail.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Series_ca_detail.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getreadmore(String a) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.getreadmore(Constant.apikey,a);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Readmore.Feedback(response.body().toString());
                } catch (Exception e) {
                    Readmore.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Readmore.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void Registercheck(String userid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.Registercheck(Constant.apikey,userid);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    MainActivity.Feedback_Reg(response.body().toString());
                } catch (Exception e) {
                    MainActivity.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MainActivity.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getmovielist_main(String api, String count) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.latestmovie(api,count);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    MainActivity.Feedback_moveieslist(response.body().toString());
                } catch (Exception e) {
                    MainActivity.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MainActivity.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void getserieslist_Main(String api, String count) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.host).addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.latestseries(api,count);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    MainActivity.Feedback_seriesliist(response.body().toString());
                } catch (Exception e) {
                    MainActivity.Feedback_Error();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MainActivity.Feedback_Error();
                t.printStackTrace();
            }
        });
    }

    public static void requestfacebookJson(String fbid) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://graph.facebook.com/").addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.requestfacebookJson(fbid);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Seriesdetail.Facebookfeedback(response.body().toString());
                } catch (Exception e) {
                    Seriesdetail.Feedback_fbError();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Seriesdetail.Feedback_fbError();
                t.printStackTrace();
            }
        });




    }

    public static void requestfacebookJson_MD(String fbid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://graph.facebook.com/").addConverterFactory(new Stringconverter()).build();


        interfacec service = retrofit.create(interfacec.class);
        Call<String> result = service.requestfacebookJson(fbid);
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {

                    Moviedetail.Facebookfeedback(response.body().toString());
                } catch (Exception e) {
                    Moviedetail.Feedback_fbError();
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Moviedetail.Feedback_fbError();
                t.printStackTrace();
            }
        });

    }
}
