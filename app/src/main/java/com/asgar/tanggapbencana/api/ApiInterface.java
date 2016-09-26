package com.asgar.tanggapbencana.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by skday on 9/26/16.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("tambahfeed")
    Call<ResponseBody> tmbFeed(
            @Field("nama_lokasi") String namaLokasi,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("deskripsi_korban") String deskripsiKorban,
            @Field("deskripsi_kebutuhan") String deskripsiKebutuhan,
            @Field("keterangan") String keterangan,
            @Field("nama_relawan") String namaRelawan,
            @Field("kontak_relawan") String kontakRelawan
    );
}
