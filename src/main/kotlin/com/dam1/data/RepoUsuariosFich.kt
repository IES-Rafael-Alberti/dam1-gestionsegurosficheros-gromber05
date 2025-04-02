package com.dam1.data

import com.dam1.model.Perfil
import com.dam1.model.Usuario
import com.dam1.ui.Output
import com.dam1.utils.IUtilFicheros
import java.io.File

class RepoUsuariosFich(
    private val ui: Output,
    private val fich: IUtilFicheros,
    private val rutaArchivo: String,
    private val separador: String = ";",
) : RepoUsuariosMem(ui), ICargarUsuariosIniciales {
    override fun cargarUsuarios(): Boolean {
        if (fich.existeFichero(rutaArchivo)) {
            fich.leerArchivo(rutaArchivo).forEach { usuario ->
                Usuario.crearUsuario(listOf(usuario[0].toString(), usuario[1].toString(), usuario[2].toString()))
            }
            return true
        } else return false
    }

    override fun obtenerTodos(): List<Usuario> {
        return super.obtenerTodos()
    }

    override fun agregar(usuario: Usuario): Boolean {
        fich.leerArchivo(rutaArchivo).forEach { usuarioRan ->

        }
        TODO()
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return super.buscar(nombreUsuario)
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        return super.eliminar(nombreUsuario)
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return super.obtener(perfil)
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        return super.cambiarClave(usuario, nuevaClave)
    }
}
