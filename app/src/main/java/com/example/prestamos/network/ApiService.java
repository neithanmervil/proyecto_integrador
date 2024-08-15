package com.example.prestamos.network;

import com.example.prestamos.model.Prestamo;
import com.example.prestamos.model.Dispositivo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("api/prestamos/")
    Call<List<Prestamo>> getPrestamos();

    @GET("api/dispositivos/")
    Call<List<Dispositivo>> getDispositivos();

    @POST("api/prestamos/")
    Call<Prestamo> hacerPrestamo(@Body Prestamo prestamo);
}
