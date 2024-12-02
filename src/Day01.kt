// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2024, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {
    val re = Regex("""\d+""")

    fun part1(input: List<String>): Int {
        var left = mutableListOf<Int>()
        var right = mutableListOf<Int>()

        for (line in input) {
            val matches = re.findAll(line).toList()
            left.add(matches[0].value.toInt())
            right.add(matches[1].value.toInt())
        }

        left.sort()
        right.sort()

        var distances = mutableListOf<Int>()

        for (p in (left zip right)) {
            distances.add(Math.abs(p.first - p.second))
        }

        return distances.sum()
    }

    fun part2(input: List<String>): Int {
        var left = mutableListOf<Int>()
        var right = mutableListOf<Int>()

        for (line in input) {
            val matches = re.findAll(line).toList()
            left.add(matches[0].value.toInt())
            right.add(matches[1].value.toInt())
         }

         var similarity: Int = 0
         for (n in left) {
             similarity += right.filter({it == n}).sum()
         }

         return similarity
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
