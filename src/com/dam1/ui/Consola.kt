package com.dam1.ui

import com.dam1.model.Perfil

class Consola: Output {

    override fun mostrarMsj(msj: String) {
        println(msj)
    }

    override fun mostrarError(error: Errores) {
        mostrarMsj("**ERROR** (${error.name}): ${error.descripcion} ")
    }
}