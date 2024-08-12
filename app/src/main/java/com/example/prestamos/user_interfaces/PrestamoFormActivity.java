package com.example.prestamos.user_interfaces;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.prestamos.R;
import com.example.prestamos.model.Dispositivo;
import com.example.prestamos.model.Prestamo;
import com.example.prestamos.repository.PrestamoRepository;
import com.example.prestamos.utils.Constants;

import java.time.LocalDate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrestamoFormActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextTelefono;

    private PrestamoRepository prestamoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamo_form);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        prestamoRepository = new PrestamoRepository();

        findViewById(R.id.btnHacerPrestamo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hacerPrestamo();
            }
        });
    }

    private void hacerPrestamo() {
        String nombre_estudiante = editTextNombre.getText().toString();
        String telefono_estudiante = editTextTelefono.getText().toString();

        if (nombre_estudiante.isEmpty() || telefono_estudiante.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Suponiendo que obtienes el Dispositivo de alguna parte del código
        Dispositivo dispositivo = obtenerDispositivo();  // Método que necesitas implementar

        // Usar LocalDate para las fechas
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(2); // Ejemplo: 7 días de préstamo
        int dispositivoId = dispositivo.getId_dispositivo();
        // Creación del objeto Prestamo
        Prestamo prestamo = new Prestamo(nombre_estudiante, telefono_estudiante, dispositivoId, fechaPrestamo.toString(), fechaDevolucion.toString());

        prestamoRepository.hacerPrestamo(prestamo, new Callback<Prestamo>() {
            @Override
            public void onResponse(Call<Prestamo> call, Response<Prestamo> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PrestamoFormActivity.this, "Préstamo realizado con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PrestamoFormActivity.this, "Error al realizar el préstamo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Prestamo> call, Throwable t) {
                Toast.makeText(PrestamoFormActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método de ejemplo para obtener el dispositivo, debes implementarlo según tu lógica
    private Dispositivo obtenerDispositivo() {
        // Obtiene el dispositivo desde el Intent
        Dispositivo dispositivo = (Dispositivo) getIntent().getSerializableExtra(Constants.EXTRA_DISPOSITIVO);

        // Verifica si el dispositivo es nulo
        if (dispositivo == null) {
            // Maneja el caso donde no se pasó un dispositivo
            Toast.makeText(this, "Error: No se seleccionó un dispositivo", Toast.LENGTH_SHORT).show();
        }

        return dispositivo;
    }
}
