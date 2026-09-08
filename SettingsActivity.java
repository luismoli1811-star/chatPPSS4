package com.chatppss4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.SeekBar;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    private Switch swVibracion, swFullscreen, swVsync, swAudio;
    private SeekBar seekVolumen, seekResolucion, seekBrillo;
    private Spinner spinnerRender, spinnerFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicializar elementos
        swVibracion = findViewById(R.id.sw_vibracion);
        swFullscreen = findViewById(R.id.sw_fullscreen);
        swVsync = findViewById(R.id.sw_vsync);
        swAudio = findViewById(R.id.sw_audio);
        seekVolumen = findViewById(R.id.seek_volumen);
        seekResolucion = findViewById(R.id.seek_resolucion);
        spinnerRender = findViewById(R.id.spinner_render);
        spinnerFiltro = findViewById(R.id.spinner_filtro);

        // Cargar valores guardados
        cargarPreferencias();

        // Guardar automáticamente al cambiar
        swVibracion.setOnCheckedChangeListener((b, v) -> guardarPreferencia("vibracion", v));
        swFullscreen.setOnCheckedChangeListener((b, v) -> guardarPreferencia("fullscreen", v));
        swVsync.setOnCheckedChangeListener((b, v) -> guardarPreferencia("vsync", v));
        swAudio.setOnCheckedChangeListener((b, v) -> guardarPreferencia("audio", v));
    }

    private void cargarPreferencias() {
        android.content.SharedPreferences prefs = getSharedPreferences("chatPPSS4_settings", MODE_PRIVATE);
        swVibracion.setChecked(prefs.getBoolean("vibracion", true));
        swFullscreen.setChecked(prefs.getBoolean("fullscreen", true));
        swVsync.setChecked(prefs.getBoolean("vsync", true));
        swAudio.setChecked(prefs.getBoolean("audio", true));
        seekVolumen.setProgress(prefs.getInt("volumen", 80));
        seekResolucion.setProgress(prefs.getInt("resolucion", 100));
    }

    private void guardarPreferencia(String clave, boolean valor) {
        getSharedPreferences("chatPPSS4_settings", MODE_PRIVATE).edit()
                .putBoolean(clave, valor).apply();
    }
}
// Dentro de onCreate(), agrega:

findViewById(R.id.btn_touch_controls).setOnClickListener(v -> {
    Intent intent = new Intent(this, TouchControlsActivity.class);
    startActivity(intent);
});

