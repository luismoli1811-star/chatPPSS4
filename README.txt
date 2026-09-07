══════════════════════════════════════════════════════
                    CHATPPSS4
             Emulador de PlayStation 4 para Android
══════════════════════════════════════════════════════

📌 Filosofía:
   El usuario ELIGE el motor de traducción.
   NUNCA más se pierde compatibilidad al actualizar.

⚙️ Motores disponibles:
   ✅ FEX   → Máxima compatibilidad, juegos nuevos
   ✅ Box64 → Más rápido, juegos que fallan con FEX

📱 Plataforma: Android 12+
🔖 Versión: 0.0.1
🎨 Nombre y logo: Original

══════════════════════════════════════════════════════
📱 INTERFAZ — Menú y Secciones:
══════════════════════════════════════════════════════

🔝 Barra superior:
   📚 Biblioteca de juegos  |  🏠 Inicio  |  ⚙️ Ajustes
   ℹ️ Acerca de  |  ❓ Ayuda

🛠️ Herramientas integradas:
   🔄 Convertidor de formatos → entre todos los formatos soportados
   🎮 Editar controles táctiles → personalizar posición, tamaño y botones

🔻 Barra inferior — Botón desplegable de acciones rápidas:
   ▾ Acciones:
      📁 Folder Games   → Seleccionar carpeta de juegos
      📦 Instalar PKG   → Instalar archivos .pkg
      🎮 Instalar .PS4  → Instalar formato .ps4

══════════════════════════════════════════════════════
📂 FORMATOS SOPORTADOS (15 formatos):
══════════════════════════════════════════════════════

📦 Imágenes y paquetes:
   .iso   .pkg   .ps4   .card4   .xps4   .4iP
   .dic-ps4   .PS4-RiP   .vpp   .claveagameps4

🗜️ Comprimidos:
   .zip   .7z   .rar   .4z   .xRAR

══════════════════════════════════════════════════════
💡 Características únicas:
   ✅ 15 formatos nativos — sin conversión previa
   ✅ Convertidor de formatos integrado
   ✅ Controles táctiles totalmente personalizables
   ✅ El usuario elige el motor (FEX o Box64)
   ✅ Interfaz ordenada, limpia y accesible
══════════════════════════════════════════════════════
🔄 CONVERTIDOR DE FORMATOS:
   Convierte formatos de origen a formatos nativos de chatPPSS4:
   📥 Entrada:  .iso   .pkg
   📤 Salida:   .ps4   .dic-ps4

   ⚠️ Nota: Los formatos comprimidos (.zip .7z .rar .4z .xRar)
   y los demás formatos (.card4 .xps4 .4iP .PS4-RiP .vpp .claveagameps4)
   son soportados directamente sin necesidad de conversión.
══════════════════════════════════════════════════════
⚙️ GESTIÓN DE COMPILACIÓN — GRADLE WRAPPER
══════════════════════════════════════════════════════

📂 Ubicación: carpeta principal de chatPPSS4/

📁 Estructura:
   ├── 📂 gradle/wrapper/
   │    ├── 📄 gradle-wrapper.jar      ← Archivo binario (se genera/descarga)
   │    └── 📄 gradle-wrapper.properties  ← Configuración de Gradle
   ├── 📄 gradlew                      ← Script de compilación (Linux/Mac)
   ├── 📄 gradlew.bat                  ← Script de compilación (Windows)
   ├── 📄 settings.gradle              ← Configuración de módulos del proyecto
   ├── 📄 gradle.properties            ← Propiedades globales de Gradle
   ├── 📄 build.gradle (raíz)          ← Configuración de nivel de proyecto
   └── 📄 app/build.gradle             ← Configuración de la aplicación

📋 Contenido de gradle-wrapper.properties:
   distributionBase=GRADLE_USER_HOME
   distributionPath=wrapper/dists
   distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip
   networkTimeout=10000
   validateDistributionUrl=true
   zipStoreBase=GRADLE_USER_HOME
   zipStorePath=wrapper/dists

💡 ¿Para qué sirve?
   ✅ Permite compilar la app sin tener Gradle instalado manualmente
   ✅ Garantiza la MISMA versión de Gradle para todos los desarrolladores
   ✅ Funciona en Windows, Linux y Android (Termux)
   ✅ Se integra con Android Studio automáticamente

🔧 Versiones usadas:
   • Gradle: 8.2
   • Android Gradle Plugin: 8.1.0
   • NDK: 25.2.9519653
   • CMake: 3.22.1
   • Java: 17

══════════════════════════════════════════════════════

