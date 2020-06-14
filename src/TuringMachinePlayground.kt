import kotlin.system.measureTimeMillis

fun main(){
    time {
//        TuringMachine().run(machines.transcendental, "𝔟", 100)
//        TuringMachine().run(machines.third, "𝔟", 100)
        TuringMachine().run(machines.increment, "begin", 100)
    }
}

//https://en.wikipedia.org/wiki/Mathematical_Alphanumeric_Symbols
// 𝔟 𝔳 𝔮 𝔭 𝔣

fun time(block: () -> Unit){
    println(
        "${measureTimeMillis(block)}ms"
    )
}







