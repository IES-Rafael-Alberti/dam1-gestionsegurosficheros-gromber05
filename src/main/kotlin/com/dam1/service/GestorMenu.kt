package com.dam1.service

import com.dam1.model.Perfil
import com.dam1.ui.Output

class GestorMenu(val ui: Output) {

    fun menu(usuario: Perfil) {
        when (usuario) {
            Perfil.GESTION -> {
                mostrarMenuGestion()
            }
            Perfil.CONSULTA -> {
                mostrarMenuConsulta()
            }
            Perfil.ADMIN -> {
                mostrarMenuAdmin()
            }
        }
    }

    fun apartadoGestion() {

    }

    fun apartadoAdmin() {

    }

    fun apartadoConsulta() {

    }

    fun mostrarMenuGestion() {
        ui.mostrarMsj("1. Seguros")
        ui.mostrarMsj("    1. Contratar")
        ui.mostrarMsj("        1. Hogar")
        ui.mostrarMsj("        2. Auto")
        ui.mostrarMsj("        3. Vida")
        ui.mostrarMsj("        4. Volver")
        ui.mostrarMsj("    2. Eliminar")
        ui.mostrarMsj("    3. Consultar")
        ui.mostrarMsj("        1. Todos")
        ui.mostrarMsj("        2. Hogar")
        ui.mostrarMsj("        3. Auto")
        ui.mostrarMsj("        4. Vida")
        ui.mostrarMsj("        5. Volver")
        ui.mostrarMsj("2. Salir")
    }

    fun mostrarMenuAdmin() {
        ui.mostrarMsj("1. Usuarios")
        ui.mostrarMsj("    1. Nuevo")
        ui.mostrarMsj("    2. Eliminar")
        ui.mostrarMsj("    3. Cambiar contraseña")
        ui.mostrarMsj("    4. Consultar")
        ui.mostrarMsj("    5. Volver")
        ui.mostrarMsj("2. Seguros")
        ui.mostrarMsj("    1. Contratar")
        ui.mostrarMsj("        1. Hogar")
        ui.mostrarMsj("        2. Auto")
        ui.mostrarMsj("        3. Vida")
        ui.mostrarMsj("        4. Volver")
        ui.mostrarMsj("    2. Eliminar")
        ui.mostrarMsj("    3. Consultar")
        ui.mostrarMsj("        1. Todos")
        ui.mostrarMsj("        2. Hogar")
        ui.mostrarMsj("        3. Auto")
        ui.mostrarMsj("        4. Vida")
        ui.mostrarMsj("        5. Volver")
        ui.mostrarMsj("3. Salir")
    }

    fun mostrarMenuConsulta() {
        ui.mostrarMsj("1. Seguros")
        ui.mostrarMsj("    1. Consultar")
        ui.mostrarMsj("        1. Todos")
        ui.mostrarMsj("        2. Hogar")
        ui.mostrarMsj("        3. Auto")
        ui.mostrarMsj("        4. Vida")
        ui.mostrarMsj("        5. Volver")
        ui.mostrarMsj("2. Salir")
    }
}