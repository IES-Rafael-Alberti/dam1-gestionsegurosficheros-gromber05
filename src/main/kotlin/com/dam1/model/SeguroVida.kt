package com.dam1.model

import java.time.LocalDate

class SeguroVida: Seguro {

    val fechaNac: LocalDate
    val nivelRiesgo: Riesgo
    val indemnizacion: Double

    constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        fechaNac: LocalDate,
        nivelRiesgo: Riesgo,
        indemnizacion: Double
    ) : super(numPoliza, dniTitular, importe) {
        this.nivelRiesgo = nivelRiesgo
        this.fechaNac = fechaNac
        this.indemnizacion = indemnizacion
    }


    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        return super.calcularImporteAnioSiguiente(interes)
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$separador$fechaNac$separador$nivelRiesgo$separador$indemnizacion"
    }
}