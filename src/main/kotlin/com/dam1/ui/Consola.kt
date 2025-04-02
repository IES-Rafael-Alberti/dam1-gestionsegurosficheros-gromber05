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

    override fun mostrarError(error: Errores, pausa: Boolean) {
        mostrarMsj("**ERROR** - (${error.name}): ${error.descripcion} ")
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
        return try {
            var temp = ""

            while (temp.isEmpty()) {
                mostrarMsj(msj)
                temp = readln()
                require(debeCumplir(temp))
                // if (debeCumplir(temp)) temp = readln() else throw IllegalArgumentException()
            }
            temp
        } catch (ie: IllegalArgumentException) {
            mostrarError(error)
            ""
        }

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
            mostrarError(Errores.entradaCancelada, pausa = false)
            ""
        } catch (e: EndOfFileException) {
            mostrarError(Errores.finArchivo, pausa = false)
            ""
        } catch (e: Exception) {
            mostrarError(Errores.errorLecturaContrasenia, pausa = false)
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
        return try {
            mostrarMsj(mensaje)
            val opcion = readln().lowercase()

            if (opcion == "s") true else if (opcion == "n") false else throw Exception()

        } catch (e: Exception) {
            mostrarError(Errores.datosEquivocado)
            false
        }
    }

    override fun pedirEntero(
        prompt: String,
        error: Errores,
        errorConv: Errores,
        debeCumplir: (Int) -> Boolean
    ): Int {

            mostrarMsj(prompt)
            val opcion = readln().toIntOrNull()
            require(opcion != null) { errorConv.descripcion }
            require(debeCumplir(opcion)) { error.descripcion }

            return opcion

    }

    override fun pedirDouble(
        prompt: String,
        error: Errores,
        errorConv: Errores,
        debeCumplir: (Double) -> Boolean
    ): Double {
        return try {
            mostrarMsj(prompt)
            val opcion = readln().toDoubleOrNull()
            require(opcion != null) { errorConv }
            require(debeCumplir(opcion)) { error }

            opcion
        } catch (i: IllegalArgumentException) {
            mostrarError(errorConv)
            0.0
        } catch (e: Exception) {
            mostrarError(error)
            0.0
        }
    }
}