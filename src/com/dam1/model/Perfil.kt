package com.dam1.model

enum class Perfil {
    ADMIN, GESTION, CONSULTA;

    fun getPerfil(valor: String): Perfil {
        return when (valor.lowercase().trim()) {
            "gestion" -> GESTION
            "consulta" -> CONSULTA
            "admin" -> ADMIN
            else -> GESTION
        }
    }
}