package com.dam1.data

import com.dam1.model.Perfil
import com.dam1.model.Usuario

open class RepoUsuariosMem : IRepoUsuarios {

    val listaUsuarios = mutableListOf<Usuario>()

    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) == null) {
            listaUsuarios.add(usuario)
            return true
        } else return false
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return listaUsuarios.find { it.nombre == nombreUsuario }
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        val usuario = buscar(nombreUsuario)

        if (usuario != null) {
            listaUsuarios.remove(usuario)
            return true
        } else {
            return false
        }
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (listaUsuarios.remove(usuario)) return true else return false
    }

    override fun obtenerTodos(): List<Usuario> {
        return listaUsuarios
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return listaUsuarios.filter { it.perfil == perfil }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return true
    }
}