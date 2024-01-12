package Task1

class Solution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        var rottingProcess: Boolean = true;
        var numberOfMinutes = 0

        var numberOfRottenOrange = 0
        var numberOFreshOrange = 0

        val rottenOrange = mutableMapOf<Int, List<Int>>()

        for (i in 0 until grid.size) {
            for(j in 0 until grid[i].size) {
                if(grid[i][j] == 2) {
                    numberOfRottenOrange++
                    rottenOrange[numberOfRottenOrange] = listOf(i, j)
                }
                else if (grid[i][j] == 1) {
                    numberOFreshOrange++
                }
            }
        }

        while (rottingProcess) {
            var topRotting: Boolean = true
            var downRotting: Boolean = true
            var leftRotting: Boolean = true
            var rightRotting: Boolean = true

            var bufRottingProcess = numberOfRottenOrange

            for (orange in 1 .. numberOfRottenOrange) {
                val i = rottenOrange[orange]!![0]
                val j = rottenOrange[orange]!![1]

                if(i - 1 >= 0 && grid[i - 1][j] == 1) {
                    topRotting = true
                    bufRottingProcess++
                    numberOFreshOrange--
                    rottenOrange[bufRottingProcess] = listOf(i - 1, j)
                    grid[i - 1][j] = 2
                }
                else {
                    topRotting = false
                }

                if(i + 1 < grid.size && grid[i + 1][j] == 1) {
                    downRotting = true
                    bufRottingProcess++
                    numberOFreshOrange--
                    rottenOrange[bufRottingProcess] = listOf(i + 1, j)
                    grid[i + 1][j] = 2
                }
                else {
                    downRotting = false
                }

                if(j - 1 >= 0 && grid[i][j - 1] == 1) {
                    leftRotting = true
                    bufRottingProcess++
                    numberOFreshOrange--
                    rottenOrange[bufRottingProcess] = listOf(i, j - 1)
                    grid[i][j - 1] = 2
                }
                else {
                    leftRotting = false
                }

                if(j + 1 < grid[0].size && grid[i][j + 1] == 1) {
                    rightRotting = true
                    bufRottingProcess++
                    numberOFreshOrange--
                    rottenOrange[bufRottingProcess] = listOf(i, j + 1)
                    grid[i][j + 1] = 2
                }
                else {
                    rightRotting = false
                }
            }

            numberOfRottenOrange = bufRottingProcess

            if (!topRotting && !downRotting && !leftRotting && !rightRotting) {
                rottingProcess = false
            }
            else {
                numberOfMinutes++
            }
        }

        return if(numberOFreshOrange == 0) {
            numberOfMinutes
        } else
            -1
    }
}