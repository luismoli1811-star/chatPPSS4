;package com.chatppss4;
// ... los imports que ya tienes ...

public class TouchControlsActivity extends View {
    // ... todo el código que te di antes ...
}


import java.io.File;

public class Game {
    private String nombre;
    private String ruta;
    private String formato;
    private long tamaño;
    private String motorRecomendado;
    private String portada;
    private boolean favorito;

    // Constructor
    public Game(String rutaArchivo) {
        this.ruta = rutaArchivo;
        File f = new File(rutaArchivo);
        this.nombre = f.getName();
        this.tamaño = f.length();
        this.formato = extraerFormato(nombre);
        this.motorRecomendado = determinarMotorRecomendado(formato);
        this.favorito = false;
    }

    // Detectar formato y recomendar motor
    private String determinarMotorRecomendado(String formato) {
        switch(formato.toLowerCase()) {
            case ".ps4": case ".card4": case ".4ip":
                return "Box64"; // Más rápido para formatos ligeros
            case ".pkg": case ".iso": case ".dic-ps4":
                return "FEX"; // Mejor compatibilidad
            default: return "FEX";
        }
    }

    private String extraerFormato(String nombre) {
        int punto = nombre.lastIndexOf('.');
        return (punto != -1) ? nombre.substring(punto).toLowerCase() : ".desconocido";
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getRuta() { return ruta; }
    public String getFormato() { return formato; }
    public long getTamañoMB() { return tamaño / (1024*1024); }
    public String getMotorRecomendado() { return motorRecomendado; }
    public void setFavorito(boolean estado) { favorito = estado; }
    public boolean isFavorito() { return favorito; }
}
