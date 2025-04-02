package com.dam1.utils

import com.dam1.ui.Consola
import com.dam1.ui.Output

object utils {

    private val consola: Output = Consola()

    fun pedirDatos(valor: String): String {
        var opcion: String? = null

        while (opcion.isNullOrEmpty()) {
            consola.mostrarMsj(valor)
            opcion = readln()
        }

        return opcion
    }
}