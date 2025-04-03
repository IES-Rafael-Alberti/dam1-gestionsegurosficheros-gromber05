package com.dam1.ui

interface IEntradaSalida {
    fun mostrarMsj(msj: String, salto: Boolean = true, pausa: Boolean = false)
    fun mostrarError(error: String?, pausa: Boolean = true)
    fun pedirInfo(msj: String = ""): String
    fun pedirInfo(msj: String, error: Errores, debeCumplir: (String) -> Boolean): String
    fun pedirDouble(prompt: String, error: Errores, errorConv: Errores, debeCumplir: (Double) -> Boolean): Double
    fun pedirEntero(prompt: String, error: Errores, errorConv: Errores, debeCumplir: (Int) -> Boolean): Int
    fun pedirInfoOculta(prompt: String): String
    fun pausar(msj: String = "Pulse Enter para Continuar...")
    fun limpiarPantalla(numSaltos: Int = 20)
    fun preguntar(mensaje: String): Boolean
}