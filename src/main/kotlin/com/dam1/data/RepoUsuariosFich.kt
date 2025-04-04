package com.dam1.data

import com.dam1.model.Usuario
import com.dam1.utils.IUtilFicheros

class RepoUsuariosFich(
    private val fich: IUtilFicheros,
    private val rutaArchivo: String,
) : RepoUsuariosMem(), ICargarUsuariosIniciales{

    override fun cargarUsuarios(): Boolean {
        if (fich.existeFichero(rutaArchivo)) {
            fich.leerArchivo(rutaArchivo).forEach { usuario ->
                val usuario1 = usuario.split(";")
                val usuarioTemp = Usuario.crearUsuario(listOf(usuario1[0].toString(), usuario1[1].toString(), usuario1[2].toString()))
                agregar(usuarioTemp)
                super.agregar(usuarioTemp)
            }
            return true
        } else return false
    }

    override fun agregar(usuario: Usuario): Boolean {
        if (fich.existeFichero(rutaArchivo)) {
            if (!fich.leerArchivo(rutaArchivo).contains(usuario.serializar())) {
                fich.agregarLinea(rutaArchivo, usuario.serializar())
                return super.agregar(usuario)
            } else return true
        } else return false
    }


    override fun eliminar(nombreUsuario: String): Boolean {
        val usuario = buscar(nombreUsuario)
        fich.escribirArchivo(rutaArchivo, listaUsuarios.filter { it != usuario })
        return super.eliminar(nombreUsuario)
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, listaUsuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }
}
