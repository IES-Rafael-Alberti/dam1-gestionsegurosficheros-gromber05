import com.dam1.app.CargadorInicial
import com.dam1.app.ControlAcceso
import com.dam1.app.GestorMenu
import com.dam1.data.*
import com.dam1.model.Perfil
import com.dam1.service.GestorSeguros
import com.dam1.service.GestorUsuarios
import com.dam1.ui.Consola
import com.dam1.utils.Ficheros
import com.dam1.utils.Seguridad

fun main() {

    // Crear dos variables con las rutas de los archivos de texto donde se almacenan los usuarios y seguros.
    // Estos ficheros se usarán solo si el programa se ejecuta en modo de almacenamiento persistente.
    val rutaArchivoSeguros = "./bd/seguros.txt"
    val rutaArchivoUsuarios = "./bd/usuarios.txt"

    // Instanciamos los componentes base del sistema: la interfaz de usuario, el gestor de ficheros y el módulo de seguridad.
    // Estos objetos serán inyectados en los diferentes servicios y utilidades a lo largo del programa.

    val interfazUsuario = Consola()
    val gestorFicheros = Ficheros(interfazUsuario)
    val moduloSeguridad = Seguridad()

    // Limpiamos la pantalla antes de comenzar, para que la interfaz esté despejada al usuario.

    interfazUsuario.limpiarPantalla()

    // Preguntamos al usuario si desea iniciar en modo simulación.
    // En modo simulación los datos no se guardarán en archivos, solo estarán en memoria durante la ejecución.

    val modo = interfazUsuario.preguntar("¿Desea ejecutar en modo simulación? (s/n) »» ")

    // Declaramos los repositorios de usuarios y seguros.
    // Se asignarán más abajo dependiendo del modo elegido por el usuario.

    val repoUsuarios: IRepoUsuarios
    val repoSeguros: IRepoSeguros

    // Si se ha elegido modo simulación, se usan repositorios en memoria.
    // Si se ha elegido almacenamiento persistente, se instancian los repositorios que usan ficheros.
    // Además, creamos una instancia del cargador inicial de información y lanzamos la carga desde los ficheros.

    if (!modo) {
        repoUsuarios = RepoUsuariosFich(gestorFicheros, rutaArchivoUsuarios)

        repoSeguros = RepoSegurosFich(
            gestorFicheros,
            rutaArchivoSeguros

        )

        val cargadorInicial = CargadorInicial(interfazUsuario, repoUsuarios, repoSeguros)

        cargadorInicial.cargarSeguros()
        cargadorInicial.cargarUsuarios()
    } else {
        repoUsuarios = RepoUsuariosMem()
        repoSeguros = RepoSegurosMem()
    }

    // Se crean los servicios de lógica de negocio, inyectando los repositorios y el componente de seguridad.

    val gestorSeguros = GestorSeguros(repoSeguros)
    val gestorUsuarios = GestorUsuarios(moduloSeguridad, repoUsuarios)

    // Se inicia el proceso de autenticación. Se comprueba si hay usuarios en el sistema y se pide login.
    // Si no hay usuarios, se permite crear un usuario ADMIN inicial.

    if (!modo) {
        val controlAcceso = ControlAcceso(rutaArchivoUsuarios, gestorUsuarios,interfazUsuario, gestorFicheros)
        val (nombre, perfil) = controlAcceso.autenticar()

        if (nombre != null && perfil != null) {
            val gestorMenu = GestorMenu(nombre, perfil.toString().lowercase(), interfazUsuario, gestorUsuarios, gestorSeguros)
            gestorMenu.iniciarMenu(perfil.indiceMenu)
        }

    } else {
        val (nombre, perfil) = Pair("admin", Perfil.ADMIN)

        if (nombre != null && perfil != null) {
            val gestorMenu = GestorMenu(nombre, perfil.toString().lowercase(), interfazUsuario, gestorUsuarios, gestorSeguros)
            gestorFicheros.escribirArchivo(rutaArchivoSeguros, emptyList())
            gestorMenu.iniciarMenu(perfil.indiceMenu)
        }
    }

    // Si el login fue exitoso (no es null), se inicia el menú correspondiente al perfil del usuario autenticado.
    // Se lanza el menú principal, **iniciarMenu(0)**, pasándole toda la información necesaria.

}