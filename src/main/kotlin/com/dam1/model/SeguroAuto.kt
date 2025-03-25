package com.dam1.model

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
): Seguro(numPoliza, dniTitular, importe) {

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return super.calcularImporteAnioSiguiente(interes)
    }

    override fun tipoSeguro(): String {
        TODO("Not yet implemented")
    }

    override fun serializar(separador: String): String {
        TODO("Not yet implemented")
    }
}