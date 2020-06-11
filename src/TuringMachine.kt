class TuringMachine(){
    var tape : MutableList<Char> = mutableListOf()
    var index : Int = 0

    override fun toString(): String {
        val unmarkedTape = "[,\\[\\]]".toRegex().replace(tape.toString(), " ")
        return  unmarkedTape.substring(0, (index) * 3) + "{" +
                unmarkedTape.substring((index) * 3 + 1, (index + 1) * 3 - 1) + "}" +
                unmarkedTape.substring((index + 1) * 3, unmarkedTape.length)
    }

    fun print(symbol : Char) : TuringMachine{
        if (index < tape.size)
            tape.set(index,symbol)
        else
            tape.add(index,symbol)
        return this
    }

    fun erase(): TuringMachine {
        tape.set(index, BLANK)
        return this
    }

    fun right(): TuringMachine {
        index++
        if(index >= tape.size)
            tape.add(BLANK)
        return this
    }

    fun left(): TuringMachine {
        if(index != 0)
            index --
        return this
    }

    fun run(mConfigurations: Map<String, (Char, TuringMachine) -> String>, firstMConfigId: String, itterations: Int){
        tape = mutableListOf(BLANK)
        var mConfigId = firstMConfigId
        var mConfig = mConfigurations[mConfigId]
        
        for (i in 0..itterations){
            print("<$mConfigId>")
            if (mConfig != null) {
                mConfigId = mConfig(tape[index], this)
                mConfig = mConfigurations[mConfigId]
            }
            println(this)
        }
    }
}
const val BLANK = '_'