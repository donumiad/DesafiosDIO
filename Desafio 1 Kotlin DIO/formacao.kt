import Nivel
data class formacao(val nome: String, var conteudos: ConteudoEducacional, val nivel : Nivel) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("Usuario ${usuario.nome}, inscrito com sucesso")
    }
}