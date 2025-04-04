package com.dam1.model

enum class Perfil(val indiceMenu: Int) {
    ADMIN(0), GESTION(1), CONSULTA(2);

    companion object {
        fun getPerfil(valor: String): Perfil {
            return when (valor.lowercase().trim()) {
                "gestion" -> GESTION
                "consulta" -> CONSULTA
                "admin" -> ADMIN
                else -> GESTION
            }
        }
    }
}