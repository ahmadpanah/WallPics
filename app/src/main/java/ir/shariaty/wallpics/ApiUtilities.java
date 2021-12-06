package ir.shariaty.wallpics;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit = null;
    public static final String API="563492ad6f917000010000010be44381dbae47f0b1a9e3a69aca457a";

    public static ApiInterface getApiInterface()
    {
        if (retrofit == null)
        {
            retrofit= new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }





}
