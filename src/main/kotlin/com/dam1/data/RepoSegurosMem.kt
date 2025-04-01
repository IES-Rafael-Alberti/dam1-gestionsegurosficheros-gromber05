package com.dam1.data

import com.dam1.model.Seguro
import com.dam1.ui.Output

class RepoSegurosMem(
    private val ui: Output
): IRepoSeguros {

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
        if (listaSeguros.remove(seguro)) return true else return false
    }

    override fun obtener(tipoSeguro: String): List<Seguro> {
        return listaSeguros.filter { it -> it.hashCode() == tipoSeguro.hashCode() }
    }

}