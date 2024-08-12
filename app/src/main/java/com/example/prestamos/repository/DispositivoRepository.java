package com.example.prestamos.repository;


import com.example.prestamos.model.Dispositivo;
import com.example.prestamos.network.ApiClient;
import com.example.prestamos.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DispositivoRepository {

    private ApiService apiService;

    public DispositivoRepository() {
        // Inicializa el ApiService usando el cliente de Retrofit
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    // Método para obtener la lista de dispositivos desde la API
    public void getDispositivos(Callback<List<Dispositivo>> callback) {
        Call<List<Dispositivo>> call = apiService.getDispositivos();
        call.enqueue(callback);
    }
}
