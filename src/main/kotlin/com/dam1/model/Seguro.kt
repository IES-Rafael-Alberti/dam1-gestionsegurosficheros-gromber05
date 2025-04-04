package com.dam1.model

abstract class Seguro(
    val numPoliza: Int,
    private val dniTitular: String,
    protected val importe: Double,
): IExportable {

    companion object {
        fun validarDni(dni: String): Boolean {
            return dni.matches(Regex("^[0-9]{8}[A-Z]$"))
        }
    }

    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    open fun tipoSeguro(): String {
        return this::class.simpleName ?: "Desconocido"
    }

    override fun hashCode(): Int {
        return numPoliza.hashCode()
    }

    override fun toString(): String {
        return "Seguro(numPoliza=$numPoliza, dniTitular=$dniTitular, importe=$importe)"
    }

    override fun equals(other: Any?): Boolean {
        return other.hashCode() == this.hashCode()
    }

    override fun serializar(separador: String): String {
        return "${tipoSeguro()}$separador$numPoliza$separador$dniTitular$separador$importe"
    }
}