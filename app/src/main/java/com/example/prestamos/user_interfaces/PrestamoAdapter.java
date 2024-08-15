package com.example.prestamos.user_interfaces;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prestamos.R;
import com.example.prestamos.model.Prestamo;
import java.util.List;

public class PrestamoAdapter extends RecyclerView.Adapter<PrestamoAdapter.PrestamoViewHolder> {

    private List<Prestamo> prestamoList;

    public PrestamoAdapter(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @Override
    public PrestamoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestamo, parent, false);
        return new PrestamoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestamoViewHolder holder, int position) {
        Prestamo prestamo = prestamoList.get(position);
        holder.nombreTextView.setText(prestamo.getNombre_estudiante());
        holder.telefonoTextView.setText(prestamo.getTelefono_estudiante());
        holder.textViewDispositivo.setText(String.valueOf(prestamo.getDispositivo()));
        holder.fechaPrestamoTextView.setText(prestamo.getFecha_prestamo());
        holder.fechaDevolucionTextView.setText(prestamo.getFecha_devolucion());
        // Puedes agregar más campos del modelo Prestamo a mostrar aquí
    }

    @Override
    public int getItemCount() {
        return prestamoList.size();
    }

    public static class PrestamoViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView telefonoTextView;
        TextView textViewDispositivo;
        TextView fechaPrestamoTextView;
        TextView fechaDevolucionTextView;
        public PrestamoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            telefonoTextView = itemView.findViewById(R.id.textViewTelefono);
            textViewDispositivo = itemView.findViewById(R.id.textViewDispositivo);
            fechaPrestamoTextView = itemView.findViewById(R.id.fechaPrestamoTextView);
            fechaDevolucionTextView = itemView.findViewById(R.id.fechaDevolucionTextView);
        }
        }
    }

