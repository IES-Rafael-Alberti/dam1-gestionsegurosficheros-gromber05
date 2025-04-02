package com.dam1.data

import com.dam1.model.Seguro
import com.dam1.model.SeguroAuto
import com.dam1.model.SeguroHogar
import com.dam1.model.SeguroVida
import com.dam1.ui.Output
import com.dam1.utils.IUtilFicheros

class RepoSegurosFich(private val ui: Output, private val fich: IUtilFicheros): RepoSegurosMem(ui) {

    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizasHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizasAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizasVida = maxVida
    }

}