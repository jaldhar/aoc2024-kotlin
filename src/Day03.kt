// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2024, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    fun part1(input: List<String>): Int {
        val re = Regex("""mul\( (\d{1,3}) \, (\d{1,3}) \)""",
            RegexOption.COMMENTS)
        var total = 0

        for (line in input) {
            var ops = re.findAll(line).toList()
            for (op in ops) {
                total += op.groupValues[1].toInt() * op.groupValues[2].toInt()
            }
        }

        return total
    }

    fun part2(input: List<String>): Int {
        val re = Regex("""
            ( mul )\( (\d{1,3}) \, (\d{1,3}) \) |
            ( do )\(\) |
            ( don\'t )\(\)
        """, RegexOption.COMMENTS)
        var total = 0
        var doIt: Boolean = true

        for (line in input) {
            var ops = re.findAll(line).toList()
            for (op in ops) {
                var parts = op.groupValues

                when {
                    parts[1] == "mul" -> {
                        if (doIt) {
                            total += parts[2].toInt() * parts[3].toInt()
                        }
                    }

                    parts[4] == "do" -> { doIt = true }

                    parts[5] == "don't" -> { doIt = false }
                }
            }
        }

        return total
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day03_test.txt` file:
    // val testInput = readInput("Day03_test")
    // check(part1(testInput) == 1)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
