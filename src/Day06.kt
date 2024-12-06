// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2024, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

data class Cell(val row: Int, val col: Int) 
data class Move(val row: Int, val col: Int, val dir: String) 

class Lab(input: List<String>) {
    private val elems = mutableListOf<CharArray>()

    private val directions = mapOf<String, Pair<Int, Int>>(
        Pair("UP",    Pair(-1, 0)),
        Pair("RIGHT", Pair(0,  1)),
        Pair("DOWN",  Pair(1,  0)),
        Pair("LEFT",  Pair(0, -1)),
        Pair("NONE",  Pair(0,  0))
    )

    init {
        for (line in input) {
            elems.add(line.toCharArray())
        }
    }

    fun countX(): Int {
        var count = 0

        for (row in elems.indices) {
            for (col in elems[row].indices) {
                if (elems[row][col] == 'X') {
                    count++
                }
            }
        }

        return count
    }

    private fun detectLoop(row: Int, col: Int): Boolean {
        var r = row
        var c = col
        var d = "UP"
        var visited = mutableSetOf<String>()

        while (true) {
            visited.add("$r-$c-$d")

            var result = move(r, c, d)
            r = result.row
            c = result.col
            d = result.dir

            if (d == "OUT") {
                return false
            }

            if (visited.contains("$r-$c-$d")) {
                return true
            }
        }
    }

    fun findGuard(): Cell {
        for (row in elems.indices) {
            for (col in elems[row].indices) {
                if (isGuard(row, col)) {
                    return Cell(row, col)
                }
            }
        }

        return Cell(-1, -1)
    }

    fun findObstructions(guardRow: Int, guardCol: Int): Int {
        var obstructions = 0

        for (row in elems.indices) {
            for (col in elems[row].indices) {
                if (elems[row][col] != 'X') {
                    continue
                }

                if (row == guardRow && col == guardCol) {
                    continue
                } 

                elems[row][col] = '#'
                if (detectLoop(guardRow, guardCol)) {
                    obstructions++
                    elems[row][col] = 'O'
                } else {
                    elems[row][col] ='.'
                }
            }
        }
        return obstructions
    }

    fun findPath(row: Int, col: Int) {
        var r = row
        var c = col
        var d = "UP"

        while(true) {
            elems[r][c] = 'X'

            var result = move(r, c, d)
            r = result.row
            c = result.col
            d = result.dir

            if (d == "OUT") {
                break
            }
        }
    }

    private fun isGuard(row: Int, col: Int): Boolean {
        return elems[row][col] == '^'
    }

    private fun isObstacle(row: Int, col: Int): Boolean {
        return elems[row][col] == '#'
    }

    private fun move(row: Int, col: Int, dir: String): Move {
        var r = row
        var c = col
        var d = dir

        var nextRow = r + directions[d]!!.first
        var nextCol = c + directions[d]!!.second

        if (nextRow !in elems.indices || nextCol !in elems[0].indices) {
            return Move(-1, -1, "OUT")
        }

        if (isObstacle(nextRow, nextCol)) {
            d = when (d) {
                "UP"   ->  "RIGHT"
                "RIGHT"->  "DOWN"
                "DOWN" ->  "LEFT"
                "LEFT" ->  "UP"
                else   ->  "HONE"
            }
        } else {
            r = nextRow
            c = nextCol
        }

        return Move(r, c, d)
    }
}


fun main() {
    fun part1(input: List<String>): Int {
        val lab = Lab(input)
        val guard = lab.findGuard()

        lab.findPath(guard.row, guard.col)

        return lab.countX()
    }

    fun part2(input: List<String>): Int {
        val lab = Lab(input)
        val guard = lab.findGuard()

        lab.findPath(guard.row, guard.col)

        return lab.findObstructions(guard.row, guard.col)
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day06_test.txt` file:
    // val testInput = readInput("Day06_test")
    // check(part1(testInput) == 41)

    // Read the input from the `src/Day06.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
