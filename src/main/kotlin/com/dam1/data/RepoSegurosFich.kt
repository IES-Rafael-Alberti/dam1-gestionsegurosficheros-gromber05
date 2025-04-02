package com.dam1.data

import com.dam1.model.Seguro
import com.dam1.model.SeguroAuto
import com.dam1.model.SeguroHogar
import com.dam1.model.SeguroVida
import com.dam1.ui.IEntradaSalida
import com.dam1.utils.IUtilFicheros

class RepoSegurosFich(private val ui: IEntradaSalida, private val fich: IUtilFicheros): RepoSegurosMem(ui) {

    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizasHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizasAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizasVida = maxVida
    }

    override fun buscar(numPoliza: Int): Seguro? {
        return super.buscar(numPoliza)
    }

    override fun obtenerTodos(): List<Seguro> {
        return super.obtenerTodos()
    }

    override fun agregar(seguro: Seguro): Boolean {
        return super.agregar(seguro)
    }

    override fun eliminar(numPoliza: Int): Boolean {
        return super.eliminar(numPoliza)
    }

    override fun eliminar(seguro: Seguro): Boolean {
        return super.eliminar(seguro)
    }

    override fun obtener(tipoSeguro: String): List<Seguro> {
        return super.obtener(tipoSeguro)
    }
}