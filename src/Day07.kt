// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2024, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    val re = Regex("""\d+""")
    lateinit var op: (Int, Long) -> Boolean

    fun isValidTest(test: Long, values: List<Long>, operation: (Int, Long) -> Boolean): Boolean {
        val n = values.size
    
        if (n == 0) {
            return false
        }
    
        return operation(1, values[0])
    }

    fun part1(input: List<String>): Long {
        var total = 0L

        for (line in input) {
            var values = re.findAll(line).toList().map({ it.value.toLong() }).toMutableList()
            var test = values.removeAt(0)

            op = { index, current ->
                if (index == values.size) {
                    current == test
                } else {
                    op(index + 1, current + values[index]) ||
                    op(index + 1, current * values[index])
                }
            }

            if (isValidTest(test, values, op)) {
                total += test
            }
        }

        return total
    }


    fun part2(input: List<String>): Long {
        var total = 0L

        for (line in input) {
            var values = re.findAll(line).toList().map({ it.value.toLong() }).toMutableList()
            var test = values.removeAt(0)

            op = { index, current ->
                if (index == values.size) {
                    current == test
                } else {
                    op(index + 1, current + values[index]) ||
                    op(index + 1, current * values[index]) ||
                    op(index + 1, "$current${values[index]}".toLong())
                }
            }

            if (isValidTest(test, values, op)) {
                total += test
            }
        }

        return total
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day07_test.txt` file:
    // val testInput = readInput("Day07_test")
    // check(part1(testInput) == 1)

    // Read the input from the `src/Day07.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
