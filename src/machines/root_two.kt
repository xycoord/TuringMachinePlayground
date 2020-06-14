package machines

import BLANK
import TuringMachine

/**
 * Turing machine which calculates the square root of two in binary to infinite accuracy.
 * Using the m-configurations on pages 102-108.
 *
 * Algorithm:
 *  - For each new digit it assumes 1
 *  - It squares the new number:
 *    - For each combination of bits it multiplies them using what is effectively a shortcut AND operation
 *    - The product is added to a running total, to the digit at index equal to the sum of the indices of the two bits
 *  - If the square is greater than 2 (i.e. the total ran over into the 2 column), the new digit must be 0
 *  - otherwise it is 1
 */
val root_two =
    mapOf(
        "begin" to {
            symbol: Char, turingMachine: TuringMachine ->
            turingMachine
                .print('@')
                .right()
                .print('1')
            "new"
        },
        "new" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '@' ->{
                    turingMachine.right()
                    "mark-digits"
                }
                else -> {
                    turingMachine.left()
                    "new"
                }
            }
        },
        "mark-digits" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '0','1' -> {
                    turingMachine
                        .right()
                        .print('x')
                        .right()
                    "mark-digits"
                }
                else -> { //none
                    turingMachine
                        .right()
                        .print('z')
                        .right().right()
                        .print('r')
                    "find-x"
                }
            }
        },
        "find-x" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'x' -> {
                    turingMachine.erase()
                    "first-r"
                }
                '@' ->{
                    "find-digits"
                }
                else -> {
                    turingMachine
                        .left().left()
                    "find-x"
                }
            }
        },
        "first-r" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'r' -> {
                    turingMachine
                        .right().right()
                    "last-r"
                }
                else -> {
                    turingMachine
                        .right().right()
                    "first-r"
                }
            }
        },
        "last-r" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'r' -> {
                    turingMachine
                        .right().right()
                    "last-r"
                }
                else -> { //none
                    turingMachine
                        .print('r')
                        .right().right()
                        .print('r')
                    "find-x"
                }
            }
        },
        "find-digits" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '@' -> {
                    turingMachine
                        .right().right()
                    "find-1st-digit"
                }
                else -> {
                    turingMachine
                        .left().left()
                    "find-digits"
                }
            }
        },
        "find-1st-digit" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'x','y' -> {
                    turingMachine
                        .left()
                    "found-1st-digit"
                }
                'z' -> {
                    turingMachine
                        .left()
                    "found-2nd-digit"
                }
                else -> { //none
                    turingMachine
                        .right().right()
                    "find-1st-digit"
                }
            }
        },
        "found-1st-digit" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '0' -> {
                    turingMachine
                        .right()
                    "add-zero"
                }
                else -> { //1
                    turingMachine
                        .right().right().right()
                    "find-2nd-digit"
                }
            }
        },
        "find-2nd-digit" to {
                symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'x','y' -> {
                    turingMachine
                        .left()
                    "found-2nd-digit"
                }
                else -> { //none
                    turingMachine
                        .right().right()
                    "find-2nd-digit"
                }
            }
        },
        "found-2nd-digit" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '0' -> {
                    turingMachine
                        .right()
                    "add-zero"
                }
                else -> { //1 or none
                    turingMachine
                        .right()
                    "add-one"
                }
            }
        },
        "add-zero" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'r' -> {
                    turingMachine
                        .print('s')
                    "add-finished"
                }
                'u' -> {
                    turingMachine
                        .print('v')
                    "add-finished"
                }
                else -> { //1 or none
                    turingMachine
                        .right().right()
                    "add-zero"
                }
            }
        },
        "add-one" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'r' -> {
                    turingMachine
                        .print('v')
                    "add-finished"
                }
                'u' -> {
                    turingMachine
                        .print('s')
                        .right().right()
                    "carry"
                }
                else -> { //1 or none
                    turingMachine
                        .right().right()
                    "add-one"
                }
            }
        },
        "carry" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'r' -> {
                    turingMachine
                        .print('u')
                    "add-finished"
                }
                'u' -> {
                    turingMachine
                        .print('r')
                        .right().right()
                    "carry"
                }
                else -> { //none
                    turingMachine
                        .print('u')
                    "new-digit-is-zero"
                }
            }
        },
        "add-finished" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '@' -> {
                    turingMachine
                        .right().right()
                    "erase-old-x"
                }
                else -> {
                    turingMachine
                        .left().left()
                    "add-finished"
                }
            }
        },
        "erase-old-x" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'x' -> {
                    turingMachine
                        .erase()
                        .left()
                        .left()
                    "print-new-x"
                }
                'z' -> {
                    turingMachine
                        .print('y')
                        .left().left()
                    "print-new-x"
                }
                else -> {
                    turingMachine
                        .right().right()
                    "erase-old-x"
                }
            }
        },
        "print-new-x" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '@' -> {
                    turingMachine
                        .right().right()
                    "erase-old-y"
                }
                'y' -> {
                    turingMachine
                        .print('z')
                    "find-digits"
                }
                else -> { //none
                    turingMachine
                        .print('x')
                    "find-digits"
                }
            }
        },
        "erase-old-y" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                'y' -> {
                    turingMachine
                        .erase()
                        .left().left()
                    "print-new-y"
                }
                else -> {
                    turingMachine
                        .right().right()
                    "erase-old-y"
                }
            }
        },
        "print-new-y" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '@' -> {
                    turingMachine
                        .right()
                    "new-digit-is-one"
                }
                else -> {
                    turingMachine
                        .print('y')
                        .right()
                    "reset-new-x"
                }
            }
        },
        "reset-new-x" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                BLANK -> {
                    turingMachine
                        .right()
                        .print('x')
                    "flag-result-digits"
                }
                else -> {
                    turingMachine
                        .right()
                        .right()
                    "reset-new-x"
                }
            }
        },
        "flag-result-digits" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                's' -> {
                    turingMachine
                        .print('t')
                        .right().right()
                    "unflag-result-digits"
                }
                'v' -> {
                    turingMachine
                        .print('w')
                        .right().right()
                    "unflag-result-digits"
                }
                else -> {
                    turingMachine
                        .right()
                        .right()
                    "flag-result-digits"
                }
            }
        },
        "unflag-result-digits" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                's' -> {
                    turingMachine
                        .print('r')
                        .right().right()
                    "unflag-result-digits"
                }
                'v' -> {
                    turingMachine
                        .print('u')
                        .right().right()
                    "unflag-result-digits"
                }
                else ->
                    "find-digits"
            }
        },
        "new-digit-is-zero" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '@' -> {
                    turingMachine
                        .right()
                    "print-zero-digit"
                }
                else -> {
                    turingMachine
                        .left()
                    "new-digit-is-zero"
                }
            }
        },
        "print-zero-digit" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '0','1' -> {
                    turingMachine
                        .right()
                        .erase()
                        .right()
                    "print-zero-digit"
                }
                else -> {//none
                    turingMachine
                        .print('0')
                        .right().right().right()
                    "cleanup"
                }
            }
        },
        "new-digit-is-one" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '@' -> {
                    turingMachine
                        .right()
                    "print-one-digit"
                }
                else -> {
                    turingMachine
                        .left()
                    "new-digit-is-one"
                }
            }
        },
        "print-one-digit" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                '0','1' -> {
                    turingMachine
                        .right()
                        .erase()
                        .right()
                    "print-one-digit"
                }
                else -> {//none
                    turingMachine
                        .print('1')
                        .right().right().right()
                    "cleanup"
                }
            }
        },
        "cleanup" to {
            symbol: Char, turingMachine: TuringMachine ->
            when(symbol){
                BLANK ->
                    "new"
                else -> {
                    turingMachine
                        .erase()
                        .right().right()
                    "cleanup"
                }
            }
        }
    )