package com.chatppss4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CoverManager {
    private static CoverManager instancia;
    private LruCache<String, Bitmap> cache;
    private File carpetaPortadas;

    public static CoverManager getInstancia(android.content.Context ctx) {
        if (instancia == null) instancia = new CoverManager(ctx);
        return instancia;
    }

    private CoverManager(android.content.Context ctx) {
        // Caché de imágenes (25% de la memoria disponible)
        int maxMemoria = (int)(Runtime.getRuntime().maxMemory() / 1024);
        int tamañoCache = maxMemoria / 4;
        cache = new LruCache<String, Bitmap>(tamañoCache);
        
        // Carpeta para guardar portadas
        carpetaPortadas = new File(ctx.getFilesDir(), "portadas");
        if (!carpetaPortadas.exists()) carpetaPortadas.mkdirs();
    }

    // Obtener portada (caché → archivo → por defecto)
    public Bitmap getPortada(Game juego) {
        // 1. Buscar en caché
        Bitmap bmp = cache.get(juego.getNombre());
        if (bmp != null) return bmp;

        // 2. Buscar en archivo local
        File archivoPortada = new File(carpetaPortadas, juego.getNombre() + ".png");
        if (archivoPortada.exists()) {
            bmp = BitmapFactory.decodeFile(archivoPortada.getAbsolutePath());
            cache.put(juego.getNombre(), bmp);
            return bmp;
        }

        // 3. Devolver imagen por defecto
        return null; // La app usará el ícono por defecto
    }

    // Guardar portada descargada
    public void guardarPortada(Game juego, Bitmap imagen) {
        new Thread(() -> {
            try {
                File salida = new File(carpetaPortadas, juego.getNombre() + ".png");
                FileOutputStream fos = new FileOutputStream(salida);
                imagen.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.close();
                cache.put(juego.getNombre(), imagen);
            } catch (IOException e) { e.printStackTrace(); }
        }).start();
    }
}
