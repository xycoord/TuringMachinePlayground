val mConfigsThird =
    mapOf(
        "𝔟" to {
            symbol: Char, turingMachine: TuringMachine ->
            when (symbol){
                BLANK -> {
                    turingMachine
                        .print('0')
                }
                '0' -> {
                    turingMachine
                        .right()
                        .right()
                        .print('1')
                }
                '1' -> {
                    turingMachine
                        .right()
                        .right()
                        .print('0')
                }
            }
            "𝔟"
        }
    )