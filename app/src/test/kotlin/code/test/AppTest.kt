package code.test

import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    enum class Balls(val value: Int) {
        red(1),
        yellow(2),
        green(3),
        brown(4),
        blue(5),
        pink(6),
        black(7)

        fun valueFromColor(value: Int): Balls {
            return Balls.values().filter { it.value == value }.first()
        }
    }

    class Board(var target: Int) {
        val remaining = mutableListOf<Balls>()

        init {
            var nextHighestBall = Balls.black
            while (target > 0) {
                if (target >= nextHighestBall.value) {
                    remaining.add(nextHighestBall)
                    nextHighestBall = nextHighestBall.valueFromColor(nextHighestBall.value - 1)
                    target = target - nextHighestBall.value
                } else {
                    remaining.add(nextHighestBall)
                    break
                }
            }
        }
    }

    @Test fun getABallFromAValue() {
        val black = Balls.black
        val expectedPick = black.valueFromColor(black.value - 1)
        assertEquals(expectedPick, Balls.pink)
    }

    // @Test fun theBoardShouldOnlyHaveABlackBall() {
    //     val target = 7 // to 13
    //     val board = Board(target)

    //     assertEquals(board.remaining[0], Balls.black)
    // }

    // @Test fun shouldHaveBlackBall() {
    //     val target = 5 // to 13
    //     val board = Board(target)

    //     assertEquals(board.remaining[0], Balls.black)
    // }

    // @Test fun theBoardShouldOnlyHaveABlackAndPink() {
    //     val target = 8 // to  13
    //     val board = Board(target)

    //     assertEquals(board.remaining[0], Balls.black)
    //     assertEquals(board.remaining[1], Balls.pink)
    // }

    // @Test fun blackAndPink() {
    //     val target = 13
    //     val board = Board(target)

    //     assertEquals(board.remaining[0], Balls.black)
    //     assertEquals(board.remaining[1], Balls.pink)
    // }

    // @Test fun blackPinkBlue() {
    //     val target = 14 // to 18
    //     val board = Board(target)

    //     //14
    //     //7
    //     //6

    //     assertEquals(board.remaining[0], Balls.black)
    //     assertEquals(board.remaining[1], Balls.pink)
    //     assertEquals(board.remaining[2], Balls.blue)
    // }
}
