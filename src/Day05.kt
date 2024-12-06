
// Advent of Code
// Solution by Jaldhar H. Vyas <jaldhar@braincells.com>
// Copyright (C) 2024, Consolidated Braincells Inc.  All rights reserved.
// "Do What Thou Wilt" shall be the whole of the license.

fun main() {

    fun isSorted(pages: MutableList<Int>, order: MutableMap<Int, MutableList<Int>>): Boolean {
        for (i in pages.indices) {
            if ((pages.slice(0..i - 1).intersect(order[pages[i]]!!)).size > 0) {
                return false
            }
        }
        return true
    }

    fun processInput(input: List<String>): Pair<MutableMap<Int, MutableList<Int>>, MutableList<String>> {
        var order = mutableMapOf<Int, MutableList<Int>>()
        var pageData = mutableListOf<String>()
        var ordering = true

        for (line in input) {
            if (line == "") {
                ordering = false
            } else {
                if (ordering) {
                    val (l, r) = line.split('|').map { it.toInt() }
                    if (!order.containsKey(l)) {
                        order[l] = mutableListOf<Int>()
                    }
                    order[l]?.add(r)
                } else {
                    pageData.add(line)
                }
            }
        }

        return Pair(order, pageData)
    }

    fun resort(pages: MutableList<Int>, order: MutableMap<Int, MutableList<Int>>): MutableList<Int> {
        for (i in pages.indices) {
            if ((pages.slice(0..i - 1).intersect(order[pages[i]]!!)).size > 0) {
                pages[i - 1] = pages[i].also { pages[i] = pages[i - 1] }
            }
        }
        return pages
    }

    fun part1(input: List<String>): Int {
        var total = 0
        var (order, pageData) = processInput(input)

        for (line in pageData) {
            var pages = line.split(',').map { it.toInt() }.toMutableList<Int>()

            if (isSorted(pages, order)) {
                total += pages[pages.size / 2]
            }

        }

        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0

        var (order, pageData) = processInput(input)

        for (line in pageData) {
            var pages = line.split(',').map { it.toInt() }.toMutableList<Int>()

            if (!isSorted(pages, order)) {
                while (!isSorted(pages, order)) {
                    pages = resort(pages, order)
                }
                total += pages[pages.size / 2]
            }
        }

        return total
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day05_test.txt` file:
    // val testInput = readInput("Day05_test")
    // check(part1(testInput) == 1)

    // Read the input from the `src/Day05.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
