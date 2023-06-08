package com.candra.eksplorindonesia.API;

import com.candra.eksplorindonesia.Model.ModelAllResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData
{

    // Retrieve ALl Data
    // User
    @GET("user/retrieve_user.php")
    Call<ModelAllResponse> ardRetrieveDataUser();
    // Kuliner
    @GET("kuliner/retrieve_kuliner.php")
    Call<ModelAllResponse> ardRetrieveDataKuliner();
    // Wisata
    @GET("wisata/retrieve_wisata.php")
    Call<ModelAllResponse> ardRetrieveDataWisata();

    // Create ALl Data
    // User
    @FormUrlEncoded
    @POST("user/create_user.php")
    Call<ModelAllResponse> ardCreateDataUser(
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("role") String role,
            @Field("phone") String phone,
            @Field("foto") String foto,
            @Field("password") String password
    );
    // Kuliner
    @FormUrlEncoded
    @POST("kuliner/create_kuliner.php")
    Call<ModelAllResponse> ardCreateDataKuliner(
            @Field("nama_kuliner") String nama_kuliner,
            @Field("asal_kuliner") String asal_kuliner,
            @Field("foto_kuliner") String foto_kuliner,
            @Field("deskripsi_kuliner") String deskripsi_kuliner
    );
    // Wisata
    @FormUrlEncoded
    @POST("wisata/create_wisata.php")
    Call<ModelAllResponse> ardCreateDataWisata(
            @Field("nama_wisata") String nama_wisata,
            @Field("lokasi_wisata") String lokasi_wisata,
            @Field("maps_wisata") String maps_wisata,
            @Field("foto_wisata") String foto_wisata,
            @Field("deskripsi_wisata") String deskripsi_wisata
    );


    // Update All Data
    @FormUrlEncoded
    @POST("user/update_user.php")
    Call<ModelAllResponse> ardUpdateDataUser(
            @Field("id_user") String id_user,
            @Field("fullname") String fulname,
            @Field("email") String email,
            @Field("role") String role,
            @Field("phone") String phone,
            @Field("foto") String foto,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("kuliner/update_kuliner.php")
    Call<ModelAllResponse> ardUpdateDataKuliner(
            @Field("id_kuliner") String id_kuliner,
            @Field("nama_kuliner") String nama_kuliner,
            @Field("asal_kuliner") String asal_kuliner,
            @Field("foto_kuliner") String foto_kuliner,
            @Field("deskripsi_kuliner") String deskripsi_kuliner
    );

    @FormUrlEncoded
    @POST("wisata/update_wisata.php")
    Call<ModelAllResponse> ardUpdateDataWisata(
            @Field("id_wisata") String id_wisata,
            @Field("nama_wisata") String nama_wisata,
            @Field("lokasi_wisata") String lokasi_wisata,
            @Field("maps_wisata") String maps_wisata,
            @Field("foto_wisata") String foto_wisata,
            @Field("deskripsi_wisata") String deskripsi_wisata
    );

    // Delete All Data
    // User
    @FormUrlEncoded
    @POST("user/delete_user.php")
    Call<ModelAllResponse> ardDeleteDataUser(
            @Field("id_user") String id_user
    );
    // Kuliner
    @FormUrlEncoded
    @POST("kuliner/delete_kuliner.php")
    Call<ModelAllResponse> ardDeleteDataKuliner(
            @Field("id_kuliner") String id_kuliner
    );
    // Wisata
    @FormUrlEncoded
    @POST("wisata/delete_wisata.php")
    Call<ModelAllResponse> ardDeleteDataWisata(
            @Field("id_wisata") String id_wisata
    );

    // Login User
    @FormUrlEncoded
    @POST("user/login_user.php")
    Call<ModelAllResponse> ardLoginUser(
            @Field("email") String email,
            @Field("password") String password
    );


}
