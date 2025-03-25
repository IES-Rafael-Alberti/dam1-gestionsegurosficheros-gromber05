package com.dam1.model

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
        fun crearSeguro(datos: List<String>): SeguroHogar {
            return SeguroHogar(datos[0], datos[1], datos[2].toDouble(), datos[3].toInt(), datos[4].toDouble(), datos[5])
        }
    }

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