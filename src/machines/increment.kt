package machines

import BLANK
import TuringMachine

val increment =
    mapOf(
        "begin" to {
            symbol: Char, turingMachine: TuringMachine ->
                turingMachine
                    .print('ə')
                    .right()
                    .print('0')
                "increment"
        },
        "increment" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '0' -> {
                    turingMachine.print('1')
                    "rewind"
                }
                '1' -> {
                    turingMachine
                        .print('0')
                        .left()
                    "increment"
                }
                else -> { //'ə'
                    turingMachine
                        .right()
                        .print('1')
                        .right()
                    "new_digit"
                }
            }
        },
        "rewind" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                BLANK -> {
                    turingMachine
                        .left()
                    "increment"
                }
                else -> {
                    turingMachine.right()
                    "rewind"
                }
            }
        },
        "new_digit" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '0' -> {
                    turingMachine
                        .right()
                    "new_digit"
                }
                else -> { //blank
                    turingMachine
                        .print('0')
                    "increment"
                }
            }
        }
    )