package com.dam1.model

class SeguroVida(
    numPoliza: String,
    dniTitular: String,
    importe: Double,
    val fechaNac: String,
    val nivelRiesgo: String,
    val indemnizacion: Double
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