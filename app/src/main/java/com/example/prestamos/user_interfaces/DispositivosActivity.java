package com.example.prestamos.user_interfaces;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prestamos.R;
import com.example.prestamos.model.Dispositivo;
import com.example.prestamos.repository.DispositivoRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispositivosActivity extends AppCompatActivity {

    private ListView listViewDispositivos;
    private DispositivoRepository dispositivoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);

        listViewDispositivos = findViewById(R.id.listViewDispositivos);
        dispositivoRepository = new DispositivoRepository();

        // Cargar la lista de dispositivos desde el repositorio
        cargarDispositivos();
    }

    private void cargarDispositivos() {
        dispositivoRepository.getDispositivos(new Callback<List<Dispositivo>>() {
            @Override
            public void onResponse(Call<List<Dispositivo>> call, Response<List<Dispositivo>> response) {
                if (response.isSuccessful()) {
                    List<Dispositivo> dispositivos = response.body();
                    mostrarDispositivos(dispositivos);
                } else {
                    Toast.makeText(DispositivosActivity.this, "Error al cargar dispositivos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Dispositivo>> call, Throwable t) {
                Toast.makeText(DispositivosActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarDispositivos(List<Dispositivo> dispositivos) {
        ArrayAdapter<Dispositivo> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, dispositivos);

        listViewDispositivos.setAdapter(adapter);

        listViewDispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dispositivo dispositivoSeleccionado = dispositivos.get(position);
                abrirFormularioPrestamo(dispositivoSeleccionado);
            }
        });
    }

    private void abrirFormularioPrestamo(Dispositivo dispositivo) {
        Intent intent = new Intent(DispositivosActivity.this, PrestamoFormActivity.class);
        intent.putExtra("dispositivo", dispositivo);
        startActivity(intent);
    }
}
