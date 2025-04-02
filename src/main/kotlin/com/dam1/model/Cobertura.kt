package com.dam1.model

enum class Cobertura(val desc: String) {
    TERCEROS("TERCEROS"),
    TERCEROS_AMPLIADO("TERCEROS +"),
    FRANQUICIA_200("Todo Riesgo con Franquicia de 200€"),
    FRANQUICIA_300("Todo Riesgo con Franquicia de 300€"),
    FRANQUICIA_400("Todo Riesgo con Franquicia de 400€"),
    FRANQUICIA_500("Todo Riesgo con Franquicia de 500€"),
    TODO_RIESGO("Todo Riesgo");

    companion object {
        fun getCobertura(valor: String): Cobertura {
            return when (valor.lowercase().trim()) {
                "terceros" -> TERCEROS
                "terceros +" -> TERCEROS_AMPLIADO
                "todo riesgo con franquicia de 200€" -> FRANQUICIA_200
                "todo riesgo con franquicia de 300€" -> FRANQUICIA_300
                "todo riesgo con franquicia de 400€" -> FRANQUICIA_400
                "todo riesgo con franquicia de 500€" -> FRANQUICIA_500
                "todo riesgo" -> TODO_RIESGO
                else -> TERCEROS
            }
        }
    }
}