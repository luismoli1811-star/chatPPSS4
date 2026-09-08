package com.chatppss4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.net.Uri;
import android.content.Intent;

public class ConverterActivity extends AppCompatActivity {

    private Spinner spinnerEntrada, spinnerSalida;
    private TextView tvArchivoSeleccionado;
    private ProgressBar progressBar;
    private Button btnSeleccionar, btnConvertir;
    private Uri archivoEntrada;

    private static final String[] FORMATOS_ENTRADA = {".iso", ".pkg"};
    private static final String[] FORMATOS_SALIDA = {".ps4", ".dic-ps4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        spinnerEntrada = findViewById(R.id.spinner_entrada);
        spinnerSalida = findViewById(R.id.spinner_salida);
        tvArchivoSeleccionado = findViewById(R.id.tv_archivo);
        progressBar = findViewById(R.id.progress_conversion);
        btnSeleccionar = findViewById(R.id.btn_seleccionar);
        btnConvertir = findViewById(R.id.btn_convertir);

        // Configurar spinners
        spinnerEntrada.setAdapter(new android.widget.ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, FORMATOS_ENTRADA));
        spinnerSalida.setAdapter(new android.widget.ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, FORMATOS_SALIDA));

        // Seleccionar archivo
        btnSeleccionar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, 2001);
        });

        // Iniciar conversión
        btnConvertir.setOnClickListener(v -> iniciarConversion());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2001 && resultCode == RESULT_OK && data != null) {
            archivoEntrada = data.getData();
            tvArchivoSeleccionado.setText("Seleccionado: " + getNombreArchivo(archivoEntrada));
            btnConvertir.setEnabled(true);
        }
    }

    private String getNombreArchivo(Uri uri) {
        android.database.Cursor c = getContentResolver().query(uri, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            int idx = c.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME);
            String name = c.getString(idx);
            c.close();
            return name;
        }
        return "archivo_desconocido";
    }

    private void iniciarConversion() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        progressBar.setIndeterminate(true);
        
        // Llamada al código nativo que realiza la conversión real
        new Thread(() -> {
            boolean exito = convertirArchivoNativo(
                    archivoEntrada.toString(),
                    FORMATOS_SALIDA[spinnerSalida.getSelectedItemPosition()]
            );
            runOnUiThread(() -> {
                progressBar.setVisibility(ProgressBar.GONE);
                android.widget.Toast.makeText(ConverterActivity.this,
                        exito ? "✅ Conversión completada!" : "❌ Error en la conversión",
                        android.widget.Toast.LENGTH_LONG).show();
            });
        }).start();
    }

    // Puente con código nativo de conversión
    public native boolean convertirArchivoNativo(String rutaEntrada, String formatoSalida);
}
