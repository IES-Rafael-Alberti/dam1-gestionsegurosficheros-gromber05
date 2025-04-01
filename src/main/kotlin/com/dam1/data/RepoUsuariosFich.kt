package com.dam1.data

import com.dam1.model.Perfil
import com.dam1.model.Usuario

class RepoUsuariosFich() : RepoUsuariosMem(), ICargarUsuariosIniciales {
    override fun cargarUsuarios(): Boolean {
        TODO("Not yet implemented")
    }

    override fun obtenerTodos(): List<Usuario> {
        return super.obtenerTodos()
    }

    override fun agregar(usuario: Usuario): Boolean {
        return super.agregar(usuario)
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return super.buscar(nombreUsuario)
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        return super.eliminar(nombreUsuario)
    }

    override fun eliminar(usuario: Usuario): Boolean {
        return super.eliminar(usuario)
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return super.obtener(perfil)
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        return super.cambiarClave(usuario, nuevaClave)
    }
}
