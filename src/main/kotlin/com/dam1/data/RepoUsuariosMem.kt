package com.dam1.data

import com.dam1.model.Perfil
import com.dam1.model.Usuario

open class RepoUsuariosMem : IRepoUsuarios {

    val usuarios = mutableListOf<Usuario>()

    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) == null) {
            usuarios.add(usuario)
            return true
        } else return false
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return usuarios.find { it.nombre == nombreUsuario }
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        val usuario = buscar(nombreUsuario)

        if (usuario != null) {
            usuarios.remove(usuario)
            return true
        } else {
            return false
        }
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (usuarios.remove(usuario)) return true else return false
    }

    override fun obtenerTodos(): List<Usuario> {
        return usuarios
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return usuarios.filter { it.perfil == perfil }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return true
    }
}