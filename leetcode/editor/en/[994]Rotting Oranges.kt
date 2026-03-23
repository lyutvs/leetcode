//You are given an m x n grid where each cell can have one of three values: 
//
// 
// 0 representing an empty cell, 
// 1 representing a fresh orange, or 
// 2 representing a rotten orange. 
// 
//
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten 
//orange becomes rotten. 
//
// Return the minimum number of minutes that must elapse until no cell has a 
//fresh orange. If this is impossible, return -1. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//Output: -1
//Explanation: The orange in the bottom left corner (row 2, column 0) is never 
//rotten, because rotting only happens 4-directionally.
// 
//
// Example 3: 
//
// 
//Input: grid = [[0,2]]
//Output: 0
//Explanation: Since there are already no fresh oranges at minute 0, the answer 
//is just 0.
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 10 
// grid[i][j] is 0, 1, or 2. 
// 
//
// Related Topics Array Breadth-First Search Matrix 👍 15075 👎 474


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val queue = ArrayDeque<Pair<Int, Int>>()
        var fresh = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                when (grid[i][j]) {
                    2 -> queue.add(Pair(i, j))
                    1 -> fresh++
                }
            }
        }

        if (fresh == 0) return 0

        val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
        var minutes = 0

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val (r, c) = queue.removeFirst()
                for (d in dirs) {
                    val nr = r + d[0]
                    val nc = c + d[1]
                    if (nr in 0 until m && nc in 0 until n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2
                        fresh--
                        queue.add(Pair(nr, nc))
                    }
                }
            }
            minutes++
        }

        return if (fresh == 0) minutes - 1 else -1
    }
}
//leetcode submit region end(Prohibit modification and deletion)
