package com.dam1.ui

import com.dam1.model.Usuario

interface Output {
    fun mostrarMsj(msj: String)
    fun mostrarError(error: Errores)
}