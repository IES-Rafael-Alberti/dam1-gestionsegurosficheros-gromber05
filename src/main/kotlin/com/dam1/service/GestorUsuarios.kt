package com.dam1.service

import com.dam1.data.IRepoUsuarios
import com.dam1.model.Perfil
import com.dam1.model.Usuario
import com.dam1.utils.IUtilSeguridad

class GestorUsuarios(private val seguridad: IUtilSeguridad, private val repo: IRepoUsuarios): IServUsuarios {

    override fun consultarTodos(): List<Usuario> {
        return repo.obtenerTodos()
    }

    override fun buscarUsuario(nombre: String): Usuario? {
        return repo.buscar(nombre)
    }

    override fun consultarPorPerfil(perfil: Perfil): List<Usuario> {
        return repo.obtener(perfil)
    }

    override fun eliminarUsuario(nombre: String): Boolean {
        return repo.eliminar(nombre)
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        val nuevaClave2 = seguridad.encriptarClave(nuevaClave)
        return repo.cambiarClave(usuario, nuevaClave2)
    }

    override fun agregarUsuario(nombre: String, clave: String, perfil: Perfil): Boolean {
        return repo.agregar(Usuario.crearUsuario(mutableListOf(nombre, clave, perfil.toString())))
    }

    override fun iniciarSesion(nombre: String, clave: String): Perfil? {
        val usuario = repo.buscar(nombre)

        if (usuario != null && seguridad.verificarClave(clave, usuario.clave)) return usuario.perfil else return null
    }


}