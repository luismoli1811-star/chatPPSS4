cmake_minimum_required(VERSION 3.22.1)
project("chatppss4")

# Estándar de C++
set(CMAKE_CXX_STANDARD 17)

# Archivos fuente nativos
file(GLOB_RECURSE SOURCES
        "src/main/cpp/*.cpp"
        "src/main/cpp/*.c"
)

# Incluir directorios de encabezados
include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}/include
        ${CMAKE_CURRENT_SOURCE_DIR}/../Box64
        ${CMAKE_CURRENT_SOURCE_DIR}/../FEX
        ${CMAKE_CURRENT_SOURCE_DIR}/../núcleo SHAD PS4
)

# Librería nativa principal
add_library(
        chatppss4
        SHARED
        ${SOURCES}
)

# Librerías del sistema que se enlazan
find_library(log-lib log)
find_library(android-lib android)
find_library(EGL-lib EGL)
find_library(GLESv3-lib GLESv3)

target_link_libraries(
        chatppss4
        ${log-lib}
        ${android-lib}
        ${EGL-lib}
        ${GLESv3-lib}
)
