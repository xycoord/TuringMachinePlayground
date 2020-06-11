import kotlin.system.measureTimeMillis

fun main(){
    println(measureTimeMillis {
        TuringMachine().run(mConfigsTranscendental, "𝔟",1000)
    })
}
//https://en.wikipedia.org/wiki/Mathematical_Alphanumeric_Symbols
// 𝔟 𝔳 𝔮 𝔭 𝔣





