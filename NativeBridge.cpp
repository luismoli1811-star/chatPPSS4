#include <jni.h>
#include <string>
#include <android/log.h>

#define LOG_TAG "chatPPSS4-Native"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

// Declaraciones de los motores externos
extern "C" {
    // Núcleo shadPS4
    bool shadps4_init();
    bool shadps4_load_game(const char* ruta);
    void shadps4_shutdown();

    // FEX
    bool fex_init();
    bool fex_translate_instruction(void* code);

    // Box64
    bool box64_init();
    bool box64_run_code(void* code, size_t size);
}

// Estado global
static bool emulacionActiva = false;
static std::string motorActual = "FEX";

// ====== Función llamada desde Java: cargarJuego() ======
extern "C" JNIEXPORT jboolean JNICALL
Java_com_chatppss4_MainActivity_cargarJuego(
        JNIEnv* env,
        jobject /* this */,
        jstring rutaJuego,
        jstring motorSeleccionado) {

    const char* ruta = env->GetStringUTFChars(rutaJuego, nullptr);
    const char* motor = env->GetStringUTFChars(motorSeleccionado, nullptr);

    motorActual = std::string(motor);
    LOGI("Cargando juego: %s | Motor: %s", ruta, motor);

    // Inicializar motor seleccionado
    bool motorOK = false;
    if (motorActual == "FEX") {
        motorOK = fex_init();
        LOGI("Motor FEX inicializado: %s", motorOK ? "OK" : "FALLÓ");
    } else if (motorActual == "Box64") {
        motorOK = box64_init();
        LOGI("Motor Box64 inicializado: %s", motorOK ? "OK" : "FALLÓ");
    }

    if (!motorOK) {
        LOGE("Error al inicializar motor de traducción");
        env->ReleaseStringUTFChars(rutaJuego, ruta);
        env->ReleaseStringUTFChars(motorSeleccionado, motor);
        return JNI_FALSE;
    }

    // Cargar juego en el núcleo PS4
    bool juegoOK = shadps4_init() && shadps4_load_game(ruta);
    emulacionActiva = juegoOK;

    env->ReleaseStringUTFChars(rutaJuego, ruta);
    env->ReleaseStringUTFChars(motorSeleccionado, motor);

    return juegoOK ? JNI_TRUE : JNI_FALSE;
}

// ====== Función llamada desde Java: detenerEmulacion() ======
extern "C" JNIEXPORT void JNICALL
Java_com_chatppss4_MainActivity_detenerEmulacion(
        JNIEnv* env,
        jobject /* this */) {

    if (emulacionActiva) {
        shadps4_shutdown();
        emulacionActiva = false;
        LOGI("Emulación detenida correctamente");
    }
}
