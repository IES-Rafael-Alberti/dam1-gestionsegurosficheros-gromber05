package com.dam1.utils

import com.dam1.ui.Consola
import com.dam1.ui.IEntradaSalida

object Utils {

    private val consola: IEntradaSalida = Consola()

    fun pedirDatos(valor: String): String {
        var opcion: String? = null

        while (opcion.isNullOrEmpty()) {
            consola.mostrarMsj(valor)
            opcion = readln()
        }

        return opcion
    }
}