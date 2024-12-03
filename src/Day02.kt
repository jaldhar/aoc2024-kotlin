// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2024, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    val re = Regex("""\d+""")

    fun isSafe(levels: List<Int>, ascending: Boolean) : Boolean {

        for (i in 1 .. levels.lastIndex) {
            if (ascending && levels[i] < levels[i - 1] ||
            !ascending && levels[i] > levels[i - 1] ||
            Math.abs(levels[i] - levels[i - 1]) !in 1 .. 3) {
                return false
            }
        }

        return true
    }

    fun part1(input: List<String>): Int {
        var safe = 0

        for (line in input) {
            val levels = re.findAll(line).toList().map { it.value.toInt() }
            val ascending = levels[1] > levels[0]

            if (isSafe(levels, ascending)){
                safe++
            }
        }

        return safe
    }

    fun part2(input: List<String>): Int {
        var safe = 0

        for (line in input) {
            val levels = re.findAll(line).toList().map { it.value.toInt() }

            if (isSafe(levels, true) || isSafe(levels, false)) {
                safe++
                continue
            }

            for (i in levels.indices) {
                var l = levels.toMutableList();
                l.removeAt(i)

                if (isSafe(l, true) || isSafe(l, false)) {
                    safe++
                    break
                }
            }
        }

         return safe
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day02_test.txt` file:
    // val testInput = readInput("Day02_test")
    // check(part1(testInput) == 1)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
