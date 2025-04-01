package com.dam1.model

class SeguroAuto : Seguro {
    val descripcion: String
    val combustible: String
    val tipoAuto: tipoAuto
    val tipoCobertura: String
    var asistenciaCarretera: Boolean
    val numPartes: Int

    constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: tipoAuto,
        tipoCobertura: String,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ) : super(numPoliza, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.tipoCobertura = tipoCobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    companion object {
        val numPolizasAuto: Int = 400000
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return Double
    }

    override fun tipoSeguro(): String {
        TODO("Not yet implemented")
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$separador$descripcion$separador$combustible$separador$tipoAuto$separador$tipoCobertura$separador$asistenciaCarretera$separador$numPartes"
    }
}