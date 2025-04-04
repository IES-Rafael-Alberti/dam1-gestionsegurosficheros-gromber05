package com.dam1.app


import com.dam1.model.Auto
import com.dam1.model.Cobertura
import com.dam1.model.Perfil
import com.dam1.model.Riesgo
import com.dam1.service.IServSeguros
import com.dam1.service.IServUsuarios
import com.dam1.ui.Errores
import com.dam1.ui.IEntradaSalida
import java.time.LocalDate as LocalDate1

/**
 * Clase encargada de gestionar el flujo de menús y opciones de la aplicación,
 * mostrando las acciones disponibles según el perfil del usuario autenticado.
 *
 * @property nombreUsuario Nombre del usuario que ha iniciado sesión.
 * @property perfilUsuario Perfil del usuario: admin, gestion o consulta.
 * @property ui Interfaz de usuario.
 * @property gestorUsuarios Servicio de operaciones sobre usuarios.
 * @property gestorSeguros Servicio de operaciones sobre seguros.
 */

class GestorMenu(
    private val nombreUsuario: String,
    private val perfilUsuario: String,
    private val ui: IEntradaSalida,
    private val gestorUsuarios: IServUsuarios,
    private val gestorSeguros: IServSeguros,
) {

        private val validacionDNI = Regex("^[0-9]{8}[A-Z]$")


    /**
         * Inicia un menú según el índice correspondiente al perfil actual.
         *
         * @param indice Índice del menú que se desea mostrar (0 = principal).
         */
        fun iniciarMenu(indice: Int = 0) {
            val (opciones, acciones) = ConfiguracionesApp.obtenerMenuYAcciones(perfilUsuario, indice)
            ejecutarMenu(opciones, acciones)
        }

        /**
         * Formatea el menú en forma numerada.
         */
        private fun formatearMenu(opciones: List<String>): String {
            var cadena = ""
            opciones.forEachIndexed { index, opcion ->
                cadena += "${index + 1}. $opcion\n"
            }
            return cadena
        }

        /**
         * Muestra el menú limpiando pantalla y mostrando las opciones numeradas.
         */
        private fun mostrarMenu(opciones: List<String>) {
            ui.limpiarPantalla()
            ui.mostrarMsj(formatearMenu(opciones), salto = false)
        }

        /**
         * Ejecuta el menú interactivo.
         *
         * @param opciones Lista de opciones que se mostrarán al usuario.
         * @param ejecutar Mapa de funciones por número de opción.
         */
        private fun ejecutarMenu(opciones: List<String>, ejecutar: Map<Int, (GestorMenu) -> Boolean>) {
            do {
                mostrarMenu(opciones)
                val opcion = ui.pedirInfo("Elige opción > ").toIntOrNull()
                if (opcion != null && opcion in 1..opciones.size) {
                    // Buscar en el mapa las acciones a ejecutar en la opción de menú seleccionada
                    val accion = ejecutar[opcion]
                    // Si la accion ejecutada del menú retorna true, debe salir del menú
                    if (accion != null && accion(this)) return
                }
                else {
                    ui.mostrarError(Errores.datosEquivocado.name)
                }
            } while (true)
        }

        /** Crea un nuevo usuario solicitando los datos necesarios al usuario */
        fun nuevoUsuario() {
            try {
                val usuario = ui.pedirInfo("Introduzca el nombre de usuario »» ")
                val clave = ui.pedirInfoOculta("Introduzca una contraseña »» ")
                val perfil = ui.pedirInfo("Introduzca el tipo de perfil »» ", Errores.entradaCancelada) {it -> it.lowercase() in mutableListOf<String>("gestion", "consulta", "admin") }

                gestorUsuarios.agregarUsuario(usuario, clave, Perfil.getPerfil(perfil))

            } catch (e: Exception) {
                ui.mostrarError(e.message)
            }
        }

        /** Elimina un usuario si existe */
        fun eliminarUsuario() {
            try {
                val nombre = ui.pedirInfo("Introduzca el nombre del usuario que desee borrar »» ")

                gestorUsuarios.eliminarUsuario(nombre)
            } catch (e: Exception) {
                ui.mostrarError(e.message)
            }
        }

        /** Cambia la contraseña del usuario actual */
        fun cambiarClaveUsuario() {
            try {
                val nombre = ui.pedirInfo("Introduzca el nombre de usuario al que desee cambiar la contraseña »» ")
                val usuario = gestorUsuarios.buscarUsuario(nombre)
                val nuevaClave = ui.pedirInfoOculta("Introduzca la nueva clave »» ")

                if (usuario != null) {
                    gestorUsuarios.cambiarClave(usuario, nuevaClave)
                } else throw Exception()
            } catch (e: Exception) {
                ui.mostrarError(e.message)
            }
        }

        /**
         * Mostrar la lista de usuarios (Todos o filstrados por un perfil)
         */
        fun consultarUsuarios() {
            gestorUsuarios.consultarTodos()
        }

        /**
         * Solicita al usuario un DNI y verifica que tenga el formato correcto: 8 dígitos seguidos de una letra.
         *
         * @return El DNI introducido en mayúsculas.
         */
        private fun pedirDni(): String {
            var DNI = ""
            while (DNI.isEmpty()) {
                DNI = try {
                    DNI = ui.pedirInfo("Introduzca su DNI »» ")
                    require(DNI.matches(validacionDNI))

                    DNI
                } catch (ie: IllegalArgumentException) {
                    ui.mostrarError(ie.message)
                    ""
                }
                catch (e: Exception) {
                    ui.mostrarError(e.message)
                    ""
                }
            }
            return DNI

        }

        /**
         * Solicita al usuario un importe positivo, usado para los seguros.
         *
         * @return El valor introducido como `Double` si es válido.
         */
        private fun pedirImporte(): Double {
            var importe: Double? = null

            while (importe == null) {
                importe = try {
                    importe = ui.pedirDouble("Introduzca el importe »» ", Errores.datosEquivocado, Errores.errorDesconocido) {it > 0.0}

                    importe
                } catch(n: NumberFormatException) {
                    ui.mostrarError(n.message)
                    null
                } catch (ie: IllegalArgumentException) {
                    ui.mostrarError(ie.message)
                    null
                } catch (e: Exception) {
                    ui.mostrarError(e.message)
                    null
                }
            }

            return importe
        }

        /** Crea un nuevo seguro de hogar solicitando los datos al usuario */
        fun contratarSeguroHogar() {
            try {
                val dniTitular = pedirDni()
                val importe = ui.pedirDouble("Introduzca el importe »» ", Errores.datosEquivocado, Errores.errorLecturaContrasenia) { it > 0.0}
                val descripcion = ui.pedirInfo("Introduzca la descripción »» ")
                val combustible = ui.pedirInfo("Introduzca el tipo de combustible »» ")
                val tipoAuto = ui.pedirInfo("Introduzca el tipo de auto »»")
                val cobertura = ui.pedirInfo("Introduzca el tipo de cobertura »» ")
                val asistenciaCarretera = ui.preguntar("¿Tiene permitido la asistencia en carretera? (s/n) »» ")
                val numPartes = ui.pedirEntero("Introduzca el número de partes que posee el vehículo »» ", Errores.noNum, Errores.datosEquivocado) {it>0}

                gestorSeguros.contratarSeguroHogar(
                    dniTitular,
                    importe,
                    metrosCuadrados,
                    valorContenido,
                    direccion,
                    anioConstruccion,
                )
            } catch (e: Exception) {
                ui.mostrarError(e.message)
            }
        }

        /** Crea un nuevo seguro de auto solicitando los datos al usuario */
        fun contratarSeguroAuto() {
            try {
                val dniTitular = pedirDni()
                val importe = ui.pedirDouble("Introduzca el importe »» ", Errores.datosEquivocado, Errores.errorLecturaContrasenia) { it > 0.0}
                val descripcion = ui.pedirInfo("Introduzca la descripción »» ")
                val combustible = ui.pedirInfo("Introduzca el tipo de combustible »» ")
                val tipoAuto = ui.pedirInfo("Introduzca el tipo de auto »»")
                val cobertura = ui.pedirInfo("Introduzca el tipo de cobertura »» ")
                val asistenciaCarretera = ui.preguntar("¿Tiene permitido la asistencia en carretera? (s/n) »» ")
                val numPartes = ui.pedirEntero("Introduzca el número de partes que posee el vehículo »» ", Errores.noNum, Errores.datosEquivocado) {it>0}

                gestorSeguros.contratarSeguroAuto(
                    dniTitular,
                    importe,
                    descripcion,
                    combustible,
                    Auto.getAuto(tipoAuto),
                    Cobertura.getCobertura(cobertura),
                    asistenciaCarretera,
                    numPartes
                )
            } catch (e: Exception) {
                ui.mostrarError(e.message)
            }
        }

        /** Crea un nuevo seguro de vida solicitando los datos al usuario */
        fun contratarSeguroVida() {
            try {
                val dniTitular = pedirDni()
                val importe = ui.pedirDouble("Introduzca el importe »» ", Errores.datosEquivocado, Errores.errorLecturaContrasenia) { it > 0.0}
                val fechaNacimiento = LocalDate1.of(ui.pedirEntero("Introduzca el año de nacimiento »» ", Errores.opcionInvalida, Errores.datosEquivocado) { it in 0..3000 }, ui.pedirEntero("Introduzca el mes de nacimiento »» ", Errores.opcionInvalida, Errores.datosEquivocado) { it in (1..12) }, ui.pedirEntero("Introduzca el día de nacimiento »» ", Errores.opcionInvalida, Errores.datosEquivocado) { it in 1..31 })
                val nivelRiesgo = Riesgo.getRiesgo(ui.pedirInfo("Introduzca el nivel de Riesgo (ALTO/MEDIO/BAJO) »» ").trim())
                val indemnizacion = ui.pedirDouble("Introduzca la cantidad de indemnización »» ",  Errores.entradaCancelada, Errores.datosEquivocado) { it > 0 }


                gestorSeguros.contratarSeguroVida(
                    dniTitular,
                    importe,
                    fechaNacimiento,
                    nivelRiesgo,
                    indemnizacion
                )
            } catch (e: Exception) {
                ui.mostrarError(e.message)
            }
        }

        /** Elimina un seguro si existe por su número de póliza */
        fun eliminarSeguro() {
            try {
                val numPoliza = ui.pedirEntero("Introduzca el número de poliza del seguro que desee eliminar »» ", Errores.noNum, Errores.datosEquivocado) {it > 0}

                gestorSeguros.eliminarSeguro(numPoliza)
            } catch (e: Exception) {
                ui.mostrarError(e.message)
            }
        }

        /** Muestra todos los seguros existentes */
        fun consultarSeguros() {
            gestorSeguros.consultarTodos()
        }

        /** Muestra todos los seguros de tipo hogar */
        fun consultarSegurosHogar() {
            gestorSeguros.consultarPorTipo("segurohogar")
        }

        /** Muestra todos los seguros de tipo auto */
        fun consultarSegurosAuto() {
            gestorSeguros.consultarPorTipo("seguroauto")
        }

        /** Muestra todos los seguros de tipo vida */
        fun consultarSegurosVida() {
            gestorSeguros.consultarPorTipo("segurovida")
        }

    }
