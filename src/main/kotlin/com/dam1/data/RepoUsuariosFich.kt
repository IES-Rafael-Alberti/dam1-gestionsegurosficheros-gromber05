package com.dam1.data

import com.dam1.model.Perfil
import com.dam1.model.Usuario
import com.dam1.utils.IUtilFicheros

class RepoUsuariosFich(
    private val fich: IUtilFicheros,
    private val rutaArchivo: String,
) : RepoUsuariosMem(){

    override fun cargarUsuarios(): Boolean {
        if (fich.existeFichero(rutaArchivo)) {
            fich.leerArchivo(rutaArchivo).forEach { usuario ->
                val usuarioTemp = Usuario.crearUsuario(listOf(usuario[0].toString(), usuario[1].toString(), usuario[2].toString()))
                RepoUsuariosMem().agregar(usuarioTemp)
            }
            return true
        } else return false
    }

    override fun agregar(usuario: Usuario): Boolean {
        if (!fich.leerArchivo(rutaArchivo).contains(usuario.serializar())) {
            fich.agregarLinea(rutaArchivo, usuario.serializar())
            return super.agregar(usuario)
        } else return false
    }


    override fun eliminar(nombreUsuario: String): Boolean {
        val usuario = buscar(nombreUsuario)
        fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })
        return super.eliminar(nombreUsuario)
    }

    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }
}
