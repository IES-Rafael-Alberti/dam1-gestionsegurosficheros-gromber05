package com.dam1.model

import com.dam1.ui.Consola
import com.dam1.ui.Errores
import com.dam1.utils.utils

class Usuario(var nombre: String, clave: String, var perfil: String): IExportable {
    val consola = Consola()

    var clave: String = clave
        private set

    companion object {
        private val listaNombres = mutableListOf<String>()

        fun crearUsuario(datos: List<String>): Usuario {
            listaNombres.plus(datos[0])
            return Usuario(datos[0], datos[1], datos[2])
        }
    }

    init {
        require(nombre !in listaNombres)
    }

    fun cambiarClave() {
        val antiguaClave = utils.pedirDatos("Introduzca la antigua contraseña »» ")

        if (antiguaClave == this.clave) {
            this.clave = utils.pedirDatos("Introduzca la nueva contraseña »» ")
        }
        else {
            consola.mostrarError(Errores.contraseniaEquivocada)
        }
    }

    override fun serializar(separador: String): String {
        return "$nombre$separador$clave$separador$perfil"
    }
}