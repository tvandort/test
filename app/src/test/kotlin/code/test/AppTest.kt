package code.test

import kotlin.test.Test
import kotlin.test.Test.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AppTest {
    @Test fun testAppHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}

data class Point(val x: Int, val y: Int) {
    fun right(): Point {
        return Point(x + 1, y)
    }

    fun left(): Point {
        return Point(x - 1, y)
    }

    fun up(): Point {
        return Point(x, y + 1)
    }

    fun down(): Point {
        return Point(x, y + 1)
    }
}

enum class Marks {
    x,
    o,
    Empty
}

//   1 2 3
// 1
// 2
// 3

val BOARD_OFFSET = 3

class Board {
    private var squares = arrayOf(Marks.Empty, Marks.Empty, Marks.Empty, Marks.Empty, Marks.Empty, Marks.Empty, Marks.Empty, Marks.Empty, Marks.Empty)

    private fun pointToOffset(point: Point) =
        point.x - 1 + (point.y - 1) * BOARD_OFFSET

    private fun offsetToPoint(offset: Int): Point {
        val y = (offset / BOARD_OFFSET) + 1
        val x = offset - y + 1

        return Point(x, y)
    }

    fun mark(point: Point, mark: Marks) {
        squares[pointToOffset(point)] = mark
    }

    fun at(point: Point): Marks =
        squares[pointToOffset(point)]

    fun hasWinner(): Boolean {
        for (index in 1..3) {
            val point = Point(1, index)
            val marks = arrayOf(
                at(point),
                at(point.right()),
                at(point.right().right())
            )

            if (marks.distinct().size == 1) {
                return true
            }
        }

        for (index in 1..3) {
            val point = Point(index, 1)
            val marks = arrayOf(
                at(point),
                at(point.down()),
                at(point.down().down())
            )

            if (marks.distinct().size == 1) {
                return true
            }
        }

        return false
    }
}

class BoardTest {
    @Test fun testThatBoardSquaresCanBeChecked() {
        val board = Board()
        board.mark(Point(1, 1), Marks.o)
        assertEquals(board.at(Point(1, 1)), Marks.o)
    }

    @Test fun testThatSquaresThreeInARowWins() {
        var p1 = Point(1, 1)
        var p2 = Point(2, 1)
        var p3 = Point(3, 1)

        val board = Board()
        board.mark(p1, Marks.o)
        board.mark(p2, Marks.o)
        board.mark(p3, Marks.o)

        assertTrue(board.hasWinner())
    }

    @Test fun testThatSquaresThreeYAxisWins() {
        var p1 = Point(1, 1)
        var p2 = Point(1, 2)
        var p3 = Point(1, 3)

        val board = Board()
        board.mark(p1, Marks.o)
        board.mark(p2, Marks.o)
        board.mark(p3, Marks.o)

        assertTrue(board.hasWinner())
    }
}
