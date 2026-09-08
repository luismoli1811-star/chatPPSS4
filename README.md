# chatPPSS4 🎮
> Emulador de PlayStation 4 para Android — **Gratuito y de código abierto*

## ✨ Sobre este proyecto
- ✅ **Emulador real**, no simulador. Basado en el núcleo de shadPS4.
- ✅ Inspirado en **Bachata S4** de JICA98 — el primero en llevar shadPS4 a Android con FEX.
- ✅ Gratuito y de código abierto, igual que el trabajo de los proyectos en los que se basa.
- ✅ A diferencia de Bachata S4, aquí **puedes elegir motor de traducción**: FEX o Box64.
- ✅ Interfaz, controles táctiles e identidad visual 100% originales.

## 🛠️ Motores de traducción
- **FEX** → Mayor compatibilidad (usado por defecto en Bachata S4)
- **Box64** → Mayor rendimiento y velocidad (opción conservada aquí)

## 📱 Controles táctiles
- Panel táctil del mando de PS4 con función real
- Stick analógico con movimiento completo
- Botones: △ ○ □ × L1 L2 R1 R2 D-Pad Botón PS
- Todos con detección táctil completa
- Diseño personalizado con estilo neón az

**Versión 0.0.1 — Versión inicial**
> Basado en shadPS4 · Inspirado en Bachata S4 · Adaptado para Android
> 🎨 Identidad visual y controles táctiles diseñados desde cero
> 💖 100% gratuito y de código abierto ## 📂 Formatos soportados (15 en total)
## 📂 Formatos soportados

### 🎮 Formatos de juego:
✅ .iso        ✅ .pkg        ✅ .ps4       ✅ .card4     ✅ .xps4
✅ .4iP        ✅ .dic-ps4    ✅ .PS4-RiP   ✅ .vpp       ✅ .claveagameps4

### 📦 Archivos de datos/sistema:
✅ .sfo    ✅ .dat    ✅ .edat   ✅ .rap    ✅ .mnu    ✅ .gp4
✅ .img    ✅ .elf    ✅ .self   ✅ .sprx

### 🗜️ Formatos comprimidos:
✅ .zip    ✅ .7z     ✅ .rar    ✅ .4z     ✅ .xRAR

> Total: **29 formatos soportados**
> Coloca cualquier formato dentro de la carpeta **`PS4`** y la app lo reconocerá automáticamente.

🧠 NÚCLEO PROPIO DE chatPPSS4 — Desarrollado desde cero
 
chatPPSS4 no es un simple puerto de otro emulador: cuenta con su propio núcleo de emulación diseñado y construido desde cero, con arquitectura optimizada para dispositivos móviles Android. 📂 Estructura completa del núcleochatPPSS4-core/
├── cpu/                # 🧠 Procesador — FEX + Box64 integrados
├── memoria/            # 💾 Memoria RAM — 1025 MB (configuración personalizada)
├── hdd/                # 💿 Almacenamiento — 600 GB de espacio
├── video/              # 🖥️ Gráficos — 1080p nativo + escalado 2x/3x
├── audio/              # 🔊 Sonido — 48kHz estéreo 16-bit
├── red/                # 🌐 Conexión — multijugador en línea
├── periféricos/        # 🎮 Mando DUALSHOCK 4 completo
├── bus/                # 🔌 Bus de comunicación entre módulos
├── archivos_juego/     # 📂 Lectura de formatos internos (.sfo, .edat, .rap, etc.)
├── gestión de juegos/   # 📦 Carga de juegos — 15 formatos soportados
├── debug/              # 📊 Sistema de diagnóstico y registros
├── útiles/             # 🛠️ Herramientas generales
└── sistema/            # ⚙️ Núcleo unificado — coordinación de todos los módulos
🔧 Características del núcleo propio
 
- ✅ Diseño modular: cada componente es independiente y se comunica a través de un bus interno optimizado

- ✅ FEX + Box64 integrados: capa de compatibilidad para ejecutar código x86/x64 en arquitectura ARM de Android

- ✅ Valores personalizados: memoria, almacenamiento y resolución definidos específicamente para este proyecto

- ✅ Sistema de audio nativo: 48kHz estéreo, conversión automática de formatos

- ✅ Periféricos completos: panel táctil, análogos, gatillos analógicos, vibración y sensores de movimiento

- ✅ 15 formatos de juego soportados:  .iso .pkg .ps4 .card4 .xps4 .4iP .dic-ps4 .PS4-RiP .vpp .claveagameps4 .zip .7z .rar .4z .xRAR  + archivos internos  .sfo .dat .edat .rap .mnu .gp4 .img .elf .self .sprx 

- ✅ Ciclo de emulación completo: Inicializar → Ejecutar → Pausar → Reanudar → Detener → Apagar seguro

- ✅ Optimizado para móvil: consumo de recursos gestionado, compatible con controles táctiles integrados
 
📌 Filosofía del proyecto
 
"No se trata solo de emular una consola, sino de construir un núcleo flexible que evolucione con cada versión, diseñado desde el principio para llevar la experiencia de PlayStation 4 a dispositivos móviles."
