package com.dam1.ui

enum class Errores(val descripcion: String) {
    noNum("El valor introducido no es un número"),
    contraseniaEquivocada("La contraseña no es correcta"),
    datosEquivocado("Alguno de los datos introducidos es erróneo"),
    fileError("Error al realizar operaciones con ficheros"),
    incumpleCondiciones("No se cumplen las condiciones introducidas"),
    entradaCancelada("Entrada cancelada por el usuario (Ctrl + C)."),
    finArchivo("Se alcanzó el final del archivo (EOF ó Ctrl+D)."),
    errorLecturaContrasenia("Problema al leer la contraseña"),
    opcionInvalida("Opción no válida"),
    errorDesconocido("Error desconocido"),
    usuarioNoEncontrado("El usuario especificado no ha sido encontrado"),
    contraseniaCaracteres("La contraseña debe cumplir los parámetros");

    override fun toString(): String {
        return descripcion
    }

    companion object {
        fun getDescripcionError(nombreError: String?): String {
            return entries.find { it.name.equals(nombreError, ignoreCase = true) }?.descripcion
                ?: "Error no encontrado"
        }
    }
}