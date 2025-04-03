package com.dam1.utils

import com.dam1.model.IExportable
import com.dam1.ui.Errores
import com.dam1.ui.IEntradaSalida
import java.io.File
import java.io.IOException

class Ficheros(private val consola: IEntradaSalida) :IUtilFicheros {

    override fun existeDirectorio(ruta: String): Boolean {
        return File(ruta).exists() && File(ruta).isDirectory
    }

    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).exists() && File(ruta).isFile
    }

    override fun leerArchivo(ruta: String): List<String> {
        return try {
            File(ruta).readLines()
        } catch (e: IOException) {
            consola.mostrarError(Errores.fileError.name)
            emptyList()
        }
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        return try {
            File(ruta).appendText("$linea\n")
            true
        } catch (e: IOException) {
            consola.mostrarError(Errores.fileError.name)
            false
        }
    }

    override fun <T : IExportable> escribirArchivo(ruta: String, elementos: List<T>): Boolean {
        return try {
            File(ruta).writeText(elementos.joinToString("\n") { it.serializar() })
            true
        } catch (e: IOException) {
            consola.mostrarError(Errores.fileError.name)
            false
        }
    }

}