package com.dam1.data

import com.dam1.model.Seguro
import com.dam1.ui.IEntradaSalida

open class RepoSegurosMem(private val ui: IEntradaSalida): IRepoSeguros {

    val listaSeguros = mutableListOf<Seguro>()

    override fun obtenerTodos(): List<Seguro> {
        return listaSeguros
    }

    override fun agregar(seguro: Seguro): Boolean {
        listaSeguros.plus(seguro)
        return true
    }

    override fun buscar(numPoliza: Int): Seguro? {
        return listaSeguros.find { it -> it.numPoliza == numPoliza }
    }

    override fun eliminar(numPoliza: Int): Boolean {
        if (buscar(numPoliza) != null) {
            listaSeguros.remove(buscar(numPoliza))
            return true
        } else return false
    }

    override fun eliminar(seguro: Seguro): Boolean {
        return listaSeguros.remove(seguro)
    }

    override fun obtener(tipoSeguro: String): List<Seguro> {
        return listaSeguros.filter { it -> it.tipoSeguro().lowercase() ==  tipoSeguro }
    }

}