package com.example.prestamos.user_interfaces;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prestamos.R;
import com.example.prestamos.model.Prestamo;
import com.example.prestamos.repository.PrestamoRepository;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrestamosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PrestamoAdapter prestamoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos);

        recyclerView = findViewById(R.id.recyclerViewPrestamos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PrestamoRepository prestamoRepository = new PrestamoRepository();
        prestamoRepository.getPrestamos(new Callback<List<Prestamo>>() {

            @Override
            public void onResponse(Call<List<Prestamo>> call, Response<List<Prestamo>> response) {
                if (response.isSuccessful()) {
                    List<Prestamo> prestamoList = response.body();
                    prestamoAdapter = new PrestamoAdapter(prestamoList);
                    recyclerView.setAdapter(prestamoAdapter);
                } else {
                    // Log the error details
                    Log.e("PrestamosActivity", "Error al cargar los préstamos: " + response.message());

                    // Show a toast to the user
                    Toast.makeText(PrestamosActivity.this, "Error al cargar los préstamos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Prestamo>> call, Throwable t) {
                Log.e("PrestamosActivity", "Error de red: " + t.getMessage());
                Toast.makeText(PrestamosActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
