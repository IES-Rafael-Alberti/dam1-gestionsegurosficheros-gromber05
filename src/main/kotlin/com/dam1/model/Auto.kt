package com.dam1.model

enum class Auto {
    COCHE, MOTO, CAMION;

    companion object {
        fun getAuto(valor: String): Auto {
            return when (valor.lowercase().trim()) {
                "coche" -> COCHE
                "moto" -> MOTO
                "camion" -> CAMION
                else -> MOTO
            }
        }
    }
}