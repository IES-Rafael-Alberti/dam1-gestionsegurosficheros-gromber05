package com.dam1.utils

import com.dam1.ui.Consola

object utils {

    val consola = Consola()

    fun pedirDatos(valor: String): String {
        var opcion: String? = null

        while (opcion.isNullOrEmpty()) {
            consola.mostrarMsj(valor)
            opcion = readln()
        }

        return opcion
    }
}