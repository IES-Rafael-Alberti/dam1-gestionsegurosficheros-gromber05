package com.dam1.service

import com.dam1.data.IRepoSeguros
import com.dam1.model.*
import java.time.LocalDate

class GestorSeguros(private val repo: IRepoSeguros): IServSeguros {

    override fun consultarPorTipo(tipoSeguro: String): List<Seguro> {
        return repo.obtener(tipoSeguro)
    }

    override fun consultarTodos(): List<Seguro> {
        return repo.obtenerTodos()
    }

    override fun eliminarSeguro(numPoliza: Int): Boolean {
        return repo.eliminar(numPoliza)
    }

    override fun contratarSeguroAuto(
        dniTitular: String,
        importe: Double,
        descripcion: String,
        combustible: String,
        tipoAuto: Auto,
        cobertura: Cobertura,
        asistenciaCarretera: Boolean,
        numPartes: Int
    ): Boolean {
        val seguro = SeguroAuto.crearSeguro(mutableListOf(dniTitular, importe.toString(), descripcion, combustible, tipoAuto.toString(), cobertura.toString(), asistenciaCarretera.toString(), numPartes.toString()))
        if (repo.agregar(seguro)) return true else return false
    }

    override fun contratarSeguroHogar(
        dniTitular: String,
        importe: Double,
        metrosCuadrados: Int,
        valorContenido: Double,
        direccion: String,
        anioConstruccion: Int
    ): Boolean {
        val seguro = SeguroHogar.crearSeguro(mutableListOf(dniTitular, importe.toString(), metrosCuadrados.toString(), valorContenido.toString(), direccion, anioConstruccion.toString()))
        if (repo.agregar(seguro)) return true else return false
    }

    override fun contratarSeguroVida(
        dniTitular: String,
        importe: Double,
        fechaNacimiento: LocalDate,
        nivelRiesgo: Riesgo,
        indemnizacion: Double
    ): Boolean {
        val seguro = SeguroVida.crearSeguro(mutableListOf(dniTitular, importe.toString(), fechaNacimiento.toString(), nivelRiesgo.toString(), indemnizacion.toString()))
        if (repo.agregar(seguro)) return true else return false
    }

}