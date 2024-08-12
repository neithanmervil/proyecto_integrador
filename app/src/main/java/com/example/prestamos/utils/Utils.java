package com.example.prestamos.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    // Método para mostrar un Toast con un mensaje corto
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // Método para mostrar un Toast con un mensaje largo
    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    // Método para validar una cadena no vacía (ejemplo de validación básica)
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Otros métodos utilitarios que puedas necesitar en tu proyecto
}
