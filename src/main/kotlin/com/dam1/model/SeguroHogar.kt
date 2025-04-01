package com.dam1.model

import com.dam1.ui.Errores
import com.dam1.ui.Output

class SeguroHogar(
    numPoliza: String,
    dniTitular: String,
    importe: Double,
    val metrosCuadrados: Int,
    val valorContenido: Double,
    val direccion: String,
): Seguro(numPoliza, dniTitular, importe) {

    companion object {
        var numPolizasHogar : Int = 0
        val PORCENTAJE_INCREMENTOS_ANIOS = 0.02
        val CICLO_ANIOS_INCREMENTO = 5.0

        fun crearSeguro(datos: List<String>): SeguroHogar? {
            return try {
                val numPoliza = datos[0]
                val dniTitular = datos[1]
                val importe = datos[2].toDouble()
                val metrosCuadrados = datos[3].toInt()
                val valorContenido = datos[4].toDouble()
                val direccion = datos[5]

                SeguroHogar(numPoliza, dniTitular, importe, metrosCuadrados, valorContenido, direccion)
            } catch (e: Exception) {
                consola.mostrarError(Errores.datosEquivocado)
                null
            }
        }

    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        var interesAAplicar: Double = 0.0

        return super.calcularImporteAnioSiguiente(interes) + interesAAplicar
    }

    override fun tipoSeguro(): String {
        TODO("Not yet implemented")
    }

    override fun serializar(separador: String): String {
        return "$numPoliza$separador$dniTitular$separador$metrosCuadrados$separador$valorContenido$separador$direccion"
    }
}