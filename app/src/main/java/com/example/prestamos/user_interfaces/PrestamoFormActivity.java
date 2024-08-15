package com.example.prestamos.user_interfaces;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.prestamos.R;
import com.example.prestamos.model.Dispositivo;
import com.example.prestamos.model.Prestamo;
import com.example.prestamos.network.ApiClient;
import com.example.prestamos.network.ApiService;
import com.example.prestamos.repository.DispositivoRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PrestamoFormActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextTelefono;
    private Spinner spinnerDispositivos;
    private EditText editTextFechaPrestamo;
    private EditText editTextFechaDevolucion;
    private DispositivoRepository dispositivoRepository;
    private ArrayAdapter<Dispositivo> spinnerAdapter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamo_form);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextFechaPrestamo = findViewById(R.id.editTextFechaPrestamo);
        editTextFechaDevolucion = findViewById(R.id.editTextFechaDevolucion);
        spinnerDispositivos = findViewById(R.id.spinnerDispositivos);

        dispositivoRepository = new DispositivoRepository();

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDispositivos.setAdapter(spinnerAdapter);


        editTextFechaPrestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextFechaPrestamo);
            }
        });

        editTextFechaDevolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(editTextFechaDevolucion);
            }
        });

        cargarDispositivos();

        findViewById(R.id.btnHacerPrestamo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerPrestamo();

            }

        });
    }

    private void showDatePickerDialog(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                editText.setText(dateFormat.format(calendar.getTime()));
            }
        }, year, month, day);


        datePickerDialog.show();


    }


    private void cargarDispositivos() {
        dispositivoRepository.getDispositivos(new Callback<List<Dispositivo>>() {
            @Override
            public void onResponse(Call<List<Dispositivo>> call, Response<List<Dispositivo>> response) {
                if (response.isSuccessful()) {
                    List<Dispositivo> dispositivos = response.body();
                    mostrarDispositivos(dispositivos);
                } else {
                    Toast.makeText(PrestamoFormActivity.this, "Error al cargar dispositivos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Dispositivo>> call, Throwable t) {
                Toast.makeText(PrestamoFormActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarDispositivos(List<Dispositivo> dispositivos) {
        spinnerAdapter.clear();
        spinnerAdapter.addAll(dispositivos);
        spinnerAdapter.notifyDataSetChanged();
    }

    private void hacerPrestamo() {
        Dispositivo dispositivoSeleccionado = (Dispositivo) spinnerDispositivos.getSelectedItem();
        String nombreEstudiante = editTextNombre.getText().toString();
        String telefonoEstudiante = editTextTelefono.getText().toString();
        String fechaPrestamo = editTextFechaPrestamo.getText().toString();
        String fechaDevolucion = editTextFechaDevolucion.getText().toString();


        if (nombreEstudiante.isEmpty() || telefonoEstudiante.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dispositivoSeleccionado == null) {
            Toast.makeText(this, "No se seleccionó un dispositivo", Toast.LENGTH_SHORT).show();
            return;
        }


        int id = dispositivoSeleccionado.getId();


        Prestamo prestamo = new Prestamo(nombreEstudiante, telefonoEstudiante, id, fechaPrestamo.toString(), fechaDevolucion.toString());

        // Configura Retrofit y llama al servicio
        Retrofit retrofit = ApiClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Prestamo> call = apiService.hacerPrestamo(prestamo);

        call.enqueue(new Callback<Prestamo>() {
            @Override
            public void onResponse(Call<Prestamo> call, Response<Prestamo> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PrestamoFormActivity.this, "Préstamo realizado con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Log detallado del error
                    String errorDetail = "Código de error: " + response.code() + "\n"
                            + "Mensaje de error: " + response.message() + "\n";

                    try {
                        if (response.errorBody() != null) {
                            errorDetail += "Cuerpo del error: " + response.errorBody().string();
                        }
                    } catch (IOException e) {
                        Log.e("PrestamoFormActivity", "Error al leer el cuerpo del error", e);
                        errorDetail += "Error al leer el cuerpo del error: " + e.getMessage();
                    }

                    Log.e("PrestamoFormActivity", "Error al realizar el préstamo: " + errorDetail);

                    // Mostrar mensaje de error al usuario
                    Toast.makeText(PrestamoFormActivity.this, "Error al realizar el préstamo. Revisa el log para más detalles.", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Prestamo> call, Throwable t) {
                Toast.makeText(PrestamoFormActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
