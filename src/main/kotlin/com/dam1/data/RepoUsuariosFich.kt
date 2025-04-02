package com.dam1.data

import com.dam1.model.Perfil
import com.dam1.model.Usuario
import com.dam1.utils.IUtilFicheros

class RepoUsuariosFich(
    private val fich: IUtilFicheros,
    private val rutaArchivo: String,
    private val separador: String = ";",
) : RepoUsuariosMem(), ICargarUsuariosIniciales {

    override fun cargarUsuarios(): Boolean {
        if (fich.existeFichero(rutaArchivo)) {
            fich.leerArchivo(rutaArchivo).forEach { usuario ->
                val usuarioTemp = Usuario.crearUsuario(listOf(usuario[0].toString(), usuario[1].toString(), usuario[2].toString()))
                usuarios.plus(usuarioTemp)
            }
            return true
        } else return false
    }

    override fun obtenerTodos(): List<Usuario> {
        if (fich.existeFichero(rutaArchivo)) {
            var listaUsuarioTemp = mutableListOf<Usuario>()
            fich.leerArchivo(rutaArchivo).forEach { usuario ->
                TODO()
            }
        }
        TODO()
    }

    override fun agregar(usuario: Usuario): Boolean {
        if (!fich.leerArchivo(rutaArchivo).contains(usuario.serializar())) {
            val usuarioTemp = Usuario.crearUsuario(listOf(usuario.nombre, usuario.clave, usuario.perfil.toString()))
            usuarios.plus(usuarioTemp)
            return true
        } else return false
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        TODO()
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        TODO()
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        TODO()
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        TODO()
    }
}
