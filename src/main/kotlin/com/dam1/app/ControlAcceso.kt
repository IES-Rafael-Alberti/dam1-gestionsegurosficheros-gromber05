package com.dam1.app

import com.dam1.model.Perfil
import com.dam1.model.Usuario
import com.dam1.service.IServUsuarios
import com.dam1.ui.Errores
import com.dam1.ui.IEntradaSalida
import com.dam1.utils.IUtilFicheros

/**
 * Clase responsable del control de acceso de usuarios: alta inicial, inicio de sesión
 * y recuperación del perfil. Su objetivo es asegurar que al menos exista un usuario
 * en el sistema antes de acceder a la aplicación.
 *
 * Esta clase encapsula toda la lógica relacionada con la autenticación de usuarios,
 * separando así la responsabilidad del acceso del resto de la lógica de negocio.
 *
 * Utiliza inyección de dependencias (DIP) para recibir los servicios necesarios:
 * - La ruta del archivo de usuarios
 * - El gestor de usuarios para registrar o validar credenciales
 * - La interfaz de entrada/salida para interactuar con el usuario
 * - La utilidad de ficheros para comprobar la existencia y contenido del fichero
 *
 * @property rutaArchivo Ruta del archivo donde se encuentran los usuarios registrados.
 * @property gestorUsuarios Servicio encargado de la gestión de usuarios (login, alta...).
 * @property ui Interfaz para mostrar mensajes y recoger entradas del usuario.
 * @property ficheros Utilidad para operar con ficheros (leer, comprobar existencia...).
 */
class ControlAcceso(val rutaArchivo: String, val gestorUsuarios: IServUsuarios, val consola: IEntradaSalida, val ficheros: IUtilFicheros) {

    /**
     * Inicia el proceso de autenticación del sistema.
     *
     * Primero verifica si hay usuarios registrados. Si el archivo está vacío o no existe,
     * ofrece al usuario la posibilidad de crear un usuario ADMIN inicial.
     *
     * A continuación, solicita credenciales de acceso en un bucle hasta que sean válidas
     * o el usuario decida cancelar el proceso.
     *
     * @return Un par (nombreUsuario, perfil) si el acceso fue exitoso, o `null` si el usuario cancela el acceso.
     */
    fun autenticar(): Pair<String?, Perfil?> {
        if (verificarFicheroUsuarios()){
            val (nombre, perfil) = iniciarSesion()
            if (nombre != null && perfil != null) {
                return Pair(nombre, perfil)
            }
        }
        return Pair(null, null)
    }

    fun verificarFicheroUsuarios(): Boolean {
        if (!ficheros.existeFichero(rutaArchivo) || ficheros.leerArchivo(rutaArchivo).isEmpty()){
            consola.mostrarMsj("El fichero está vacío, no hay usuarios existentes.")
            if (consola.preguntar("¿Desea crear un usuario nuevo ADMIN?")){
                crearUsuario()
                return true
            }else{
                return false
            }
        }else{
            return true
        }
    }

    private fun crearUsuario(): Boolean{
        var usuarioCorrecto = false
        var nombreUsuario: String
        var clave: String
        do{
            try{
                ficheros.escribirArchivo(rutaArchivo, emptyList())

                nombreUsuario = consola.pedirInfo("Introduce un nombre de usuario")
                clave = consola.pedirInfo("Introduce una clave", Errores.contraseniaCaracteres){
                    it.length >= 5
                }
                if (gestorUsuarios.agregarUsuario(nombreUsuario, clave, Perfil.ADMIN)){
                    usuarioCorrecto = true
                }
            }catch(e:Exception){
                consola.mostrarError(e.message)
            }
        }while (!usuarioCorrecto)
        return usuarioCorrecto
    }

    private fun iniciarSesion(): Pair<String?, Perfil?>{
        var usuarioSalir = false
        var nombre: String?
        var clave: String
        var perfil: Perfil?
        do{
            try{
                consola.limpiarPantalla()
                nombre = consola.pedirInfo("Introduzca su nombre de usuario »» ")
                if ( nombre == "" ) usuarioSalir = true

                clave = consola.pedirInfo("Introduzca su clave »» ")
                perfil = gestorUsuarios.iniciarSesion(nombre,clave)

                if (perfil == null){
                    throw IllegalArgumentException(Errores.contraseniaEquivocada.name)
                }else{
                    usuarioSalir = true
                    return Pair(nombre, perfil)
                }
            }catch(e:IllegalArgumentException){
                consola.mostrarError(e.message)
            }catch (e:Exception){
                consola.mostrarError(e.message)
            }
        }while (!usuarioSalir)
        return Pair(null, null)
    }
}