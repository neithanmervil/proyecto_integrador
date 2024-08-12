package com.example.prestamos.repository;



import com.example.prestamos.model.Prestamo;
import com.example.prestamos.network.ApiClient;
import com.example.prestamos.network.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrestamoRepository {

    private ApiService apiService;

    public PrestamoRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void getPrestamos(Callback<List<Prestamo>> callback) {
        Call<List<Prestamo>> call = apiService.getPrestamos();
        call.enqueue(callback);
    }

    public void hacerPrestamo(Prestamo prestamo, Callback<Prestamo> callback) {
        Call<Prestamo> call = apiService.hacerPrestamo(prestamo);
        call.enqueue(callback);
    }
}
