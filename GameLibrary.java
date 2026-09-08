package com.chatppss4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.net.Uri;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLibrary extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GameAdapter adapter;
    private static final List<String> FORMATOS_SOPORTADOS = Arrays.asList(
            ".iso", ".pkg", ".ps4", ".card4", ".xps4", ".4iP",
            ".dic-ps4", ".ps4-rip", ".vpp", ".claveagameps4",
            ".zip", ".7z", ".rar", ".4z", ".xrar"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        recyclerView = findViewById(R.id.recycler_biblioteca);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GameAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        cargarJuegos();
    }

    // Escanear carpeta de juegos y filtrar formatos soportados
    private void cargarJuegos() {
        List<String> juegos = new ArrayList<>();
        Uri carpetaBase = Uri.parse("content://com.android.externalstorage.documents/document/primary:chatPPSS4/Games");
        
        // Escaneo real usando ContentResolver
        Uri hijosUri = DocumentsContract.buildChildDocumentsUriUsingTree(carpetaBase,
                DocumentsContract.getDocumentId(carpetaBase));
        
        String[] proyeccion = {
                DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                DocumentsContract.Document.COLUMN_MIME_TYPE,
                DocumentsContract.Document.COLUMN_SIZE
        };

        Cursor cursor = getContentResolver().query(hijosUri, proyeccion, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String nombre = cursor.getString(0).toLowerCase();
                if (esFormatoSoportado(nombre)) {
                    juegos.add(nombre);
                }
            }
            cursor.close();
        }
        adapter.actualizarLista(juegos);
    }

    private boolean esFormatoSoportado(String nombreArchivo) {
        for (String ext : FORMATOS_SOPORTADOS) {
            if (nombreArchivo.endsWith(ext.toLowerCase())) return true;
        }
        return false;
    }

    // Adaptador para mostrar la lista
    private class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
        List<String> listaJuegos;

        GameAdapter(List<String> juegos) { this.listaJuegos = juegos; }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(GameLibrary.this);
            tv.setPadding(32, 24, 32, 24);
            tv.setTextSize(16f);
            tv.setTextColor(0xFFFFFFFF);
            tv.setBackgroundColor(0xFF1E1E1E);
            return new ViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(listaJuegos.get(position));
            holder.textView.setOnClickListener(v -> {
                // Al tocar un juego → lanzar emulación
                String rutaSeleccionada = listaJuegos.get(position);
                setResult(RESULT_OK, getIntent().putExtra("juego_seleccionado", rutaSeleccionada));
                finish();
            });
        }

        @Override public int getItemCount() { return listaJuegos.size(); }
        void actualizarLista(List<String> nuevos) {
            listaJuegos = nuevos;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ViewHolder(TextView v) { super(v); textView = v; }
        }
    }
}
