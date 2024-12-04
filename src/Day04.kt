// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2024, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

class Grid(input: List<String>) {
    private val elems = mutableListOf<CharArray>()

    init {
        for (line in input) {
            elems.add(line.toCharArray())
        }
    }

    fun get(row: Int, col: Int): Char {
        if (row !in elems.indices || col !in elems[row].indices) {
            return ' '
        }

        return elems[row][col]
    }

    fun rows(): IntRange {
        return elems.indices
    }

    fun cols(row: Int): IntRange {
        return elems[row].indices
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        val grid = Grid(input)

        for (i in grid.rows()) {
            for (j in grid.cols(i)) {
                if (grid.get(i,j) == 'X' || grid.get(i,j) == 'S') {
                    var vertical = ""
                    var horizontal = ""
                    var leftDiag = ""
                    var rightDiag = ""
                    for (k in 0 .. 3) {
                        vertical += grid.get(i + k, j)
                        leftDiag += grid.get(i + k, j - k)
                        rightDiag += grid.get(i + k, j + k)
                        horizontal += grid.get(i, j + k)
                    }


                    if (horizontal == "XMAS" || horizontal == "SAMX") {
                        total++
                    }

                    if (vertical == "XMAS" || vertical == "SAMX") {
                        total++
                    }

                    if (leftDiag == "XMAS" || leftDiag == "SAMX") {
                        total++
                    }

                    if (rightDiag == "XMAS" || rightDiag == "SAMX") {
                        total++
                    }
                }
            }
        }

        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        val grid = Grid(input)

        for (i in grid.rows()) {
            for (j in grid.cols(i)) {
                if (grid.get(i, j) == 'A') {
                    var leftDiag = ""
                    var rightDiag = ""

                    for (k in -1 .. 1) {
                        leftDiag += grid.get(i + k, j + -k)
                        rightDiag += grid.get(i + k, j + k)
                    }

                    if ((leftDiag == "MAS" || leftDiag == "SAM") &&
                    (rightDiag == "MAS" || rightDiag == "SAM")) {
                        total++
                    }
                }
            }
        }

        return total
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day04_test.txt` file:
    // val testInput = readInput("Day04_test")
    // check(part1(testInput) == 1)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
