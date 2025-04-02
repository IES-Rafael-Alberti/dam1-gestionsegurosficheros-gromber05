package com.dam1.model

import com.dam1.ui.Errores
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class SeguroVida: Seguro {

    private val fechaNac: LocalDate
    private val nivelRiesgo: Riesgo
    private val indemnizacion: Double

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

    companion object {
        var numPolizasVida = 800000

        fun crearSeguro(datos: List<String>): SeguroVida {
                val numPoliza = generarPoliza()
                val dniTitular = datos[1]
                val importe = datos[2].toDouble()
                val nivelRiesgo = Riesgo.getRiesgo(datos[3])
                val fechaNac = LocalDate.parse(datos[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                val indemnizacion = datos[5].toDouble()


                return SeguroVida(numPoliza, dniTitular, importe, fechaNac, nivelRiesgo,  indemnizacion)
        }

        fun generarPoliza(): Int {
            return ++numPolizasVida
        }
    }


    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        var interesAAplicar = nivelRiesgo.interesAplicado
        val edad = ChronoUnit.YEARS.between(fechaNac, LocalDate.now())

        for (anio in 0..edad) {
            interesAAplicar += 0.05
        }

        return importe + (importe * interesAAplicar)
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$separador$fechaNac$separador$nivelRiesgo$separador$indemnizacion"
    }

    override fun toString(): String {
        return "${super.toString().replace("Seguro(", "SeguroVida(").dropLast(1)},nivelRiesgo=$nivelRiesgo, fechaNac=$fechaNac, indemnizacion=$indemnizacion)"

    }
}