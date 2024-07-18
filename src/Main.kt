//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //CRIAÇÃO DE USUÁRIOS
    val raimundo = Usuario("Raimundo Neto")
    val yasmim = Usuario("Yasmim Rodrigues")
    val davi = Usuario("Davi Lima")

    //CRIAÇÃO DE FORMAÇÕES
    val cursosMobKotlin = listOf("kotlin", "AndroidStudio", "POO", "Desafio Pratico")
    val formacaoMobKotlin = ConteudoEducacional(cursosMobKotlin, 160)

    val cursosBackPython = listOf("Intro a Python", "IDE para Python", "POO", "Framworks", "Desafio")
    val formacaoBackPython = ConteudoEducacional(cursosBackPython,80)

    //CRIAÇÃO DE FORMAÇÕES
    val FormacaoKotlinMobal = formacao("Mobile com Kotlin INTERMEDIARIO", conteudos = formacaoMobKotlin, Nivel.INTERMEDIARIO)
    val cursosPythonBack = formacao("Backend com Python Basico", formacaoBackPython, Nivel.BASICO)

    //MATRICULA DE USUÁRIOS NO KOTLIN MOBAL
    FormacaoKotlinMobal.matricular(raimundo)
    FormacaoKotlinMobal.matricular(yasmim)
    FormacaoKotlinMobal.matricular(davi)

    //MATRICULA DE USUÁRIOS NO PYTHON BACK
    cursosPythonBack.matricular(raimundo)
    cursosPythonBack.matricular(yasmim)
    cursosPythonBack.matricular(davi)

    //println(FomacaoKotlinMobal)
    println("Inscritos na formação ${FormacaoKotlinMobal.nome}:")
    FormacaoKotlinMobal.inscritos.forEach { usuario ->
        println("- ${usuario.nome}")
    }
    println("Inscritos na formação ${cursosPythonBack.nome}:")
    cursosPythonBack.inscritos.forEach { usuario ->
        println("- ${usuario.nome}")
    }
}