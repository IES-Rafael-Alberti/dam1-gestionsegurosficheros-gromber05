package com.dam1.model

import com.dam1.ui.Output

class SeguroAuto(
    numPoliza: String,
    dniTitular: String,
    importe: Double,
    val descripcion: String,
    val combustible: String,
    val tipoAuto: tipoAuto,
    val tipoCobertura: String,
    var asistenciaCarretera: Boolean,
    val numPartes: Int,
    consola: Output
): Seguro(numPoliza, dniTitular, importe) {

    companion object {
        val numPolizasAuto: Int = 400000
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return super.calcularImporteAnioSiguiente(interes)
    }

    override fun tipoSeguro(): String {
        TODO("Not yet implemented")
    }

    override fun serializar(separador: String): String {
        return "$numPoliza$separador$dniTitular$separador$descripcion$separador$combustible$separador$tipoAuto$separador$tipoCobertura$separador$asistenciaCarretera$separador$numPartes"
    }
}