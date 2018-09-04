package com.example.xiaojw.tamodel;

import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface TA {


    @Multipart
    @POST("upload")
    retrofit2.Call<ResponseBody> upload(@Part("description")RequestBody des, @Part MultipartBody.Part file);




}
