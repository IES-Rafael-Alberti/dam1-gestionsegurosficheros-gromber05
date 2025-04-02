package com.dam1.ui

enum class Errores(val descripcion: String) {
    noNum("El valor introducido no es un número"),
    contraseniaEquivocada("La contraseña no es correcta"),
    datosEquivocado("Alguno de los datos introducidos es erróneo"),
    fileError("Error al realizar operaciones con ficheros"),
    incumpleCondiciones("No se cumplen las condiciones introducidas"),
    entradaCancelada("Entrada cancelada por el usuario (Ctrl + C)."),
    finArchivo("Se alcanzó el final del archivo (EOF ó Ctrl+D)."),
    errorLecturaContrasenia("Problema al leer la contraseña");

    override fun toString(): String {
        return descripcion
    }
}