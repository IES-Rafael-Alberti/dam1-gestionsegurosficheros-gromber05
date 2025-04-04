package com.dam1.data

import com.dam1.model.Seguro
import com.dam1.model.SeguroAuto
import com.dam1.model.SeguroHogar
import com.dam1.model.SeguroVida
import com.dam1.ui.IEntradaSalida
import com.dam1.utils.IUtilFicheros

class RepoSegurosFich(
    private val ui: IEntradaSalida,
    private val fich: IUtilFicheros,
    private val rutaArchivo: String,
    private val separador: String = ";"
): RepoSegurosMem(ui) {

    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizasHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizasAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizasVida = maxVida
    }

    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        val lineas = fich.leerArchivo(rutaArchivo)
        listaSeguros.clear()

        lineas.forEach { linea ->
            val datos = linea.split(";").toMutableList()
            val tipoSeguro = datos.removeAt(0)
            val parametros = datos
            val crearSeguro = mapa[tipoSeguro]

            if (crearSeguro != null) {
                listaSeguros.add(crearSeguro(parametros))
            } else {
                listaSeguros.clear()
                return false
            }
        }

        actualizarContadores(listaSeguros)
        return true
    }

    override fun agregar(seguro: Seguro): Boolean {
        fich.agregarLinea(rutaArchivo, seguro.serializar())
        return super.agregar(seguro)
    }

    override fun eliminar(numPoliza: Int): Boolean {
        val seguro = buscar(numPoliza)

        fich.escribirArchivo(rutaArchivo, listaSeguros.filter { it != seguro })
        return super.eliminar(numPoliza)
    }

    override fun eliminar(seguro: Seguro): Boolean {
        fich.escribirArchivo(rutaArchivo, listaSeguros)
        return super.eliminar(seguro)
    }
}