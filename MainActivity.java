package com.chatppss4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    // Elementos de la interfaz
    private RecyclerView recyclerViewJuegos;
    private Spinner spinnerMotor;
    private Button btnFolderGames, btnInstallPkg, btnInstallPS4;

    // Motores disponibles
    private final String[] motores = {"FEX (Compatibilidad)", "Box64 (Rendimiento)"};
    public static String MOTOR_ACTUAL = "FEX"; // Predeterminado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Barra superior
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("chatPPSS4");

        // Selector de motor de traducción
        spinnerMotor = findViewById(R.id.spinner_motor);
        ArrayAdapter<String> adapterMotor = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, motores);
        spinnerMotor.setAdapter(adapterMotor);
        spinnerMotor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MOTOR_ACTUAL = (position == 0) ? "FEX" : "Box64";
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Botón: Folder Games
        btnFolderGames = findViewById(R.id.btn_folder_games);
        btnFolderGames.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameLibrary.class);
            intent.putExtra("accion", "seleccionar_carpeta");
            startActivity(intent);
        });

        // Botón: Instalar PKG
        btnInstallPkg = findViewById(R.id.btn_install_pkg);
        btnInstallPkg.setOnClickListener(v -> instalarArchivo("pkg"));

        // Botón: Instalar .PS4
        btnInstallPS4 = findViewById(R.id.btn_install_ps4);
        btnInstallPS4.setOnClickListener(v -> instalarArchivo("ps4"));

        // Navegación: Biblioteca → Ajustes → Convertidor → Controles
        findViewById(R.id.btn_biblioteca).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, GameLibrary.class)));
        findViewById(R.id.btn_ajustes).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
        findViewById(R.id.btn_convertidor).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ConverterActivity.class)));
        findViewById(R.id.btn_controles).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TouchControlsActivity.class)));
    }

    // Lógica de instalación de archivos
    private void instalarArchivo(String formato) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        String[] mimeTypes = {"application/octet-stream", "application/x-pkg"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, 1001);
    }

    // Puente con código nativo C++ (Box64 / FEX / Núcleo PS4)
    public native boolean cargarJuego(String rutaJuego, String motorSeleccionado);
    public native void detenerEmulacion();
}
