package com.dam1.model

enum class Riesgo(val interesAplicado: Double) {
    BAJO(2.0), MEDIO(5.0), ALTO(10.0);

    companion object {
        fun getRiesgo(valor: String): Riesgo {
            return when (valor.lowercase().trim()) {
                "alto" -> ALTO
                "medio" -> MEDIO
                "bajo" -> BAJO
                else -> BAJO
            }
        }
    }
}