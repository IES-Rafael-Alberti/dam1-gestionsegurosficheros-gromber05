package com.dam1.data

import com.dam1.model.Seguro

interface IRepoSeguros : ICargarSegurosIniciales {
    fun agregar(seguro: Seguro): Boolean
    fun buscar(numPoliza: Int): Seguro?
    fun eliminar(seguro: Seguro): Boolean
    fun eliminar(numPoliza: Int): Boolean
    fun obtenerTodos(): List<Seguro>
    fun obtener(tipoSeguro: String): List<Seguro>
}