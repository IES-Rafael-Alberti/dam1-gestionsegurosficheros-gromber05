package com.dam1.model

class SeguroAuto : Seguro {
    private val descripcion: String
    private val combustible: String
    private val tipoAuto: Auto
    private val tipoCobertura: Cobertura
    private var asistenciaCarretera: Boolean
    private val numPartes: Int

    constructor(
        numPoliza: Int = generarPoliza(),
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: Auto,
        tipoCobertura: Cobertura,
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
        var numPolizasAuto: Int = 400000
        val PORCENTAJE_INCREMENTO_PARTES = 2.0

        fun crearSeguro(datos: List<String>): SeguroAuto {
                val numPoliza = generarPoliza()
                val dniTitular = datos[1]
                val importe = datos[2].toDouble()
                val descripcion = datos[3]
                val combustible = datos[4]
                val tipoAuto = Auto.getAuto(datos[5])
                val tipoCobertura = Cobertura.getCobertura(datos[6])
                val asistenciaCarretera = datos[7].toBoolean()
                val numPartes = datos[8].toInt()

                return SeguroAuto(numPoliza, dniTitular, importe, descripcion, combustible, tipoAuto, tipoCobertura, asistenciaCarretera, numPartes)
        }

        fun generarPoliza(): Int {
            return ++numPolizasAuto
        }
    }

    private constructor(
        dniTitular: String,
        numPoliza: Int = numPolizasAuto,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: Auto,
        tipoCobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int,
    ) : super(numPoliza, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.tipoCobertura = tipoCobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
        numPolizasAuto++
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        var interesAAplicar = 0.0
        for (i in 0..numPartes) {
            interesAAplicar += PORCENTAJE_INCREMENTO_PARTES
        }

        val finalInteresAAplicar: Double = (interesAAplicar / 100)

        return importe + (importe * finalInteresAAplicar)
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$separador$descripcion$separador$combustible$separador$tipoAuto$separador$tipoCobertura$separador$asistenciaCarretera$separador$numPartes"
    }

    override fun toString(): String {
        return "${super.toString().replace("Seguro(", "SeguroAuto(").dropLast(1)}, descripción=$descripcion, combustible=$combustible, tipoAuto=$tipoAuto, tipoCobertura=$tipoAuto, asistenciaCarretera=$asistenciaCarretera, numPartes=$numPartes)"

    }
}