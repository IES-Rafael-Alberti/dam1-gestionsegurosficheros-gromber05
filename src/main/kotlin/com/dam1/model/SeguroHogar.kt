package com.dam1.model

import com.dam1.ui.Errores

class SeguroHogar: Seguro {

    private val metrosCuadrados: Int
    private val valorContenido: Double
    private val direccion: String
    private val anioConstruccion: Int

    constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        metrosCuadrados: Int,
        valorContenido: Double,
        direccion: String,
        anioConstruccion: Int
    ) : super(numPoliza, dniTitular, importe) {
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
        this.anioConstruccion = anioConstruccion
    }

    companion object {
        var numPolizasHogar : Int = 100000
        val PORCENTAJE_INCREMENTOS_ANIOS = 0.02
        val CICLO_ANIOS_INCREMENTO = 5
        val anioActual = 2025

        fun generarPoliza() : Int {
            return ++numPolizasHogar
        }

        fun crearSeguro(datos: List<String>): SeguroHogar {

                val dniTitular = datos[1]
                val importe = datos[2].toDouble()
                val metrosCuadrados = datos[3].toInt()
                val valorContenido = datos[4].toDouble()
                val direccion = datos[5]
                val anioConstruccion = datos[6].toInt()

                return SeguroHogar(generarPoliza(), dniTitular, importe, metrosCuadrados, valorContenido, direccion, anioConstruccion)
        }

    }

    private constructor(
        numPoliza: Int = numPolizasHogar,
        dniTitular: String,
        importe: Double,
        direccion: String,
        metrosCuadrados: Int,
        valorContenido: Double,
        anioConstruccion: Int
    ) : this(numPoliza, dniTitular, importe, metrosCuadrados, valorContenido, direccion, anioConstruccion) {
        numPolizasHogar++
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        var interesAAplicar = 0.0
        var cont = 0

        for (i in anioConstruccion..anioActual) {
            cont++
            if (cont % CICLO_ANIOS_INCREMENTO == 0) interesAAplicar += PORCENTAJE_INCREMENTOS_ANIOS
        }

        return importe + (importe * interesAAplicar)
    }

    override fun toString(): String {
        return "${super.toString().replace("Seguro(", "SeguroHogar(").dropLast(1)},metrosCuadrados= $metrosCuadrados, valorContenido = $valorContenido, direccion = $direccion)"
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$metrosCuadrados$separador$valorContenido$separador$direccion"
    }


}