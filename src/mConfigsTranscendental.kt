val mConfigsTranscendental =
    mapOf(
        "𝔟" to {
            symbol: Char, turingMachine: TuringMachine ->
            turingMachine
                .print('ə')
                .right()
                .print('ə')
                .right()
                .print('0')
                .right()
                .right()
                .print('0')
                .left()
                .left()
            "𝔳"
        },
        "𝔳" to {
                symbol: Char, turingMachine: TuringMachine ->
            when (symbol){
                '1' -> {
                    turingMachine
                        .right()
                        .print('x')
                        .left()
                        .left()
                        .left()
                    "𝔳"
                }
                else -> {
                    "𝔮"
                }
            }
        },
        "𝔮" to {
                symbol: Char, turingMachine: TuringMachine ->
            when (symbol){
                BLANK -> {
                    turingMachine
                        .print('1')
                        .left()
                    "𝔭"
                }
                else -> {
                    turingMachine
                        .right()
                        .right()
                    "𝔮"
                }
            }
        },
        "𝔭" to {
                symbol: Char, turingMachine: TuringMachine ->
            when (symbol){
                'x' -> {
                    turingMachine
                        .erase()
                        .right()
                    "𝔮"
                }
                'ə' -> {
                    turingMachine.right()
                    "𝔣"
                }
                else -> {
                    turingMachine
                        .left()
                        .left()
                    "𝔭"
                }
            }
        },
        "𝔣" to {
                symbol: Char, turingMachine: TuringMachine ->
            when (symbol){
                BLANK -> {
                    turingMachine
                        .print('0')
                        .left()
                        .left()
                    "𝔳"
                }
                else -> {
                    turingMachine
                        .right()
                        .right()
                    "𝔣"
                }
            }
        }

    )