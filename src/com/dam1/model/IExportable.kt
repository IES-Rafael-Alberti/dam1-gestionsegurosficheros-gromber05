package com.dam1.model

interface IExportable {
    fun serializar(separador: String = ";"): String
}