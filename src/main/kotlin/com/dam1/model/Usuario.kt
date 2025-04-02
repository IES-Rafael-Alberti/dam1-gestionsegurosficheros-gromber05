package com.dam1.model

class Usuario(
    var nombre: String,
    clave: String,
    var perfil: Perfil
): IExportable {

    var clave: String = clave
        private set

    companion object {
        private val listaNombres = mutableListOf<String>()

        fun crearUsuario(datos: List<String>): Usuario {
            listaNombres.plus(datos[0])
            return Usuario(datos[0], datos[1], Perfil.getPerfil(datos[2]))
        }
    }

    init {
        require(nombre !in listaNombres)
    }

    fun cambiarClave(nuevaClave: String) {
        val antiguaClave = nuevaClave
    }

    override fun serializar(separador: String): String {
        return "$nombre$separador$clave$separador$perfil"
    }

    override fun hashCode(): Int {
        return nombre.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other !is Usuario) return false
        return this.nombre == other.nombre
    }

    override fun toString(): String {
        return "Usuario(nombre= $nombre, clave = $clave, perfil = $perfil)"
    }
}