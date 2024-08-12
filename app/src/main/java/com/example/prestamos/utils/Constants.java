package com.example.prestamos.utils;


public class Constants {

    // URL base de la API (esto debería apuntar a la URL de tu servidor Django)
    public static final String BASE_URL = "http://127.0.0.1:8000/api/";

    // Claves para los datos que se pasan a través de Intents
    public static final String EXTRA_DISPOSITIVO = "extra_dispositivo";

    // Otros valores constantes del proyecto
    public static final int TIMEOUT_CONNECT = 30; // Tiempo de espera en segundos para la conexión
    public static final int TIMEOUT_READ = 30;    // Tiempo de espera en segundos para la lectura
}
