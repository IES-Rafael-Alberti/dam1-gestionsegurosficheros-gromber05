package com.dam1.ui

import jdk.internal.org.jline.reader.EndOfFileException
import jdk.internal.org.jline.reader.LineReaderBuilder
import jdk.internal.org.jline.reader.UserInterruptException
import jdk.internal.org.jline.terminal.TerminalBuilder

class Consola: IEntradaSalida {

    override fun mostrarMsj(msj: String, salto: Boolean, pausa: Boolean) {
        println(msj)
        if (salto) println()
        if (pausa) pausar()
    }

    override fun mostrarError(error: String?, pausa: Boolean) {
        mostrarMsj("**ERROR** - (${error ?: "ERROR DESCONOCIDO"}): (${Errores.getDescripcionError(error)})")
    }

    override fun pedirInfo(msj: String): String {
        var temp = ""

        while (temp.isEmpty()) {
            if (msj.isNotEmpty()) mostrarMsj(msj)

            temp = readln()
        }
        return temp
    }

    override fun pedirInfo(msj: String, error: Errores, debeCumplir: (String) -> Boolean): String {
        var temp = ""

        while (temp.isEmpty()) {
            mostrarMsj(msj)
            temp = readln()
            require(debeCumplir(temp))
                // if (debeCumplir(temp)) temp = readln() else throw IllegalArgumentException()
            }
        return temp
    }

    override fun pedirInfoOculta(prompt: String): String {
        return try {
            val terminal = TerminalBuilder.builder()
                .dumb(true) // Para entornos no interactivos como IDEs
                .build()

            val reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build()

            reader.readLine(prompt, '*') // Oculta la contraseña con '*'
        } catch (e: UserInterruptException) {
            mostrarError(
                Errores.entradaCancelada.name,
                pausa = false
            )
            ""
        } catch (e: EndOfFileException) {
            mostrarError(
                Errores.finArchivo.name,
                pausa = false
            )
            ""
        } catch (e: Exception) {
            mostrarError(
                Errores.errorLecturaContrasenia.name,
                pausa = false
            )
            ""
        }
    }

    override fun limpiarPantalla(numSaltos: Int) {
        if (System.console() != null) {
            mostrarMsj("\u001b[H\u001b[2J", false)
            System.out.flush()
        } else {
            repeat(numSaltos) {
                mostrarMsj("")
            }
        }
    }

    override fun pausar(msj: String) {
        mostrarMsj(msj)
        readln()
    }

    override fun preguntar(mensaje: String): Boolean {
        mostrarMsj(mensaje)
        val opcion = readln().lowercase()

        if (opcion == "s") return true else if (opcion == "n") return false else throw Exception(Errores.datosEquivocado.descripcion)
    }

    override fun pedirEntero(
        prompt: String,
        error: Errores,
        errorConv: Errores,
        debeCumplir: (Int) -> Boolean
    ): Int {
            mostrarMsj(prompt)
            val opcion = readln().toIntOrNull()
            require(opcion != null) { errorConv }
            require(debeCumplir(opcion)) { error }

            return opcion

    }

    override fun pedirDouble(
        prompt: String,
        error: Errores,
        errorConv: Errores,
        debeCumplir: (Double) -> Boolean
    ): Double {
            mostrarMsj(prompt)
            val opcion = readln().toDoubleOrNull()
            require(opcion != null) { errorConv }
            require(debeCumplir(opcion)) { error }

            return opcion
    }
}