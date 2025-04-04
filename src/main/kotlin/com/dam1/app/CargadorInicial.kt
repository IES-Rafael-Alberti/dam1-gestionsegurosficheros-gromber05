package com.dam1.app

import com.dam1.data.IRepoSeguros
import com.dam1.data.IRepoUsuarios
import com.dam1.model.Seguro
import com.dam1.ui.Errores
import com.dam1.ui.IEntradaSalida
import com.dam1.utils.IUtilFicheros

/**
 * Clase encargada de cargar los datos iniciales de usuarios y seguros desde ficheros,
 * necesarios para el funcionamiento del sistema en modo persistente.
 *
 * @param ui Interfaz de entrada/salida para mostrar mensajes de error.
 * @param repoUsuarios Repositorio que permite cargar usuarios desde un fichero.
 * @param repoSeguros Repositorio que permite cargar seguros desde un fichero.
 */
class CargadorInicial(
    private val ui: IEntradaSalida,
    private val repoUsuarios: IRepoUsuarios,
    private val repoSeguros: IRepoSeguros, )
{

    /**
     * Carga los usuarios desde el archivo configurado en el repositorio.
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarUsuarios() {
        try {
            repoUsuarios.cargarUsuarios()
        } catch (e: Exception) {
            ui.mostrarMsj(Errores.fileError.descripcion)
        }
    }

    /**
     * Carga los seguros desde el archivo configurado en el repositorio.
     * Utiliza el mapa de funciones de creación definido en la configuración de la aplicación
     * (ConfiguracionesApp.mapaCrearSeguros).
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarSeguros() {
        try {
            repoSeguros.cargarSeguros(ConfiguracionesApp.mapaCrearSeguros)
        } catch (e: Exception) {
            ui.mostrarMsj(Errores.fileError.descripcion)
        }
    }

}