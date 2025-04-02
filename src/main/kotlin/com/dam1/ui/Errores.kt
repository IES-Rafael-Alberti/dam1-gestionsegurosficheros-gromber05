package com.dam1.ui

enum class Errores(val descripcion: String) {
    noNum("El valor introducido no es un número"),
    contraseniaEquivocada("La contraseña no es correcta"),
    datosEquivocado("Alguno de los datos introducidos es erróneo"),
    fileError("Error al realizar operaciones con ficheros");

    override fun toString(): String {
        return descripcion
    }
}