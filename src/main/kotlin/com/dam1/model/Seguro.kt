package com.dam1.model

import com.dam1.ui.Consola
import com.dam1.ui.Output

abstract class Seguro(
    val numPoliza: String,
    val dniTitular: String,
    private val importe: Double,
): IExportable {

    companion object {
        fun validarDni(dni: String): Boolean {
            return dni.matches(Regex("^[0-9]{8}[A-Z]$"))
        }

        val consola: Output = Consola()
    }

    open fun calcularImporteAnioSiguiente(interes: Double): Double {
        return importe * interes
    }

    abstract fun tipoSeguro(): String
}