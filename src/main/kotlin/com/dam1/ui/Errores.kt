package com.dam1.ui

enum class Errores(val descripcion: String) {
    noNum("El valor introducido no es un número"),
    contraseniaEquivocada("La contraseña no es correcta"),
    datosEquivocado("Alguno de los datos introducidos es erróneo");

    override fun toString(): String {
        return descripcion
    }
}