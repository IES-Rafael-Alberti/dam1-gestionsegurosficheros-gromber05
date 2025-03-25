package com.dam1.model

abstract class Seguro(val numPoliza: String, val dniTitular: String, private val importe: Double): IExportable {

    companion object {
        fun validarDni(dni: String): Boolean {
            return dni.matches(Regex("^[0-9]{8}[A-Z]$"))
        }
    }

    open fun calcularImporteAnioSiguiente(interes: Double): Double {
        return importe * interes
    }

    abstract fun tipoSeguro(): String
}