//You are given an integer array nums of length n. 
//
// A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n)
// for which there exist indices l < p < q < r such that: 
//
// 
// nums[l...p] is strictly increasing, 
// nums[p...q] is strictly decreasing, 
// nums[q...r] is strictly increasing. 
// 
//
// Return the maximum sum of any trionic subarray in nums. 
//
// 
// Example 1: 
//
// 
// Input: nums = [0,-2,-1,-3,0,2,-1] 
// 
//
// Output: -4 
//
// Explanation: 
//
// Pick l = 1, p = 2, q = 3, r = 5: 
//
// 
// nums[l...p] = nums[1...2] = [-2, -1] is strictly increasing (-2 < -1). 
// nums[p...q] = nums[2...3] = [-1, -3] is strictly decreasing (-1 > -3) 
// nums[q...r] = nums[3...5] = [-3, 0, 2] is strictly increasing (-3 < 0 < 2). 
// Sum = (-2) + (-1) + (-3) + 0 + 2 = -4. 
// 
//
// Example 2: 
//
// 
// Input: nums = [1,4,2,7] 
// 
//
// Output: 14 
//
// Explanation: 
//
// Pick l = 0, p = 1, q = 2, r = 3: 
//
// 
// nums[l...p] = nums[0...1] = [1, 4] is strictly increasing (1 < 4). 
// nums[p...q] = nums[1...2] = [4, 2] is strictly decreasing (4 > 2). 
// nums[q...r] = nums[2...3] = [2, 7] is strictly increasing (2 < 7). 
// Sum = 1 + 4 + 2 + 7 = 14. 
// 
//
// 
// Constraints: 
//
// 
// 4 <= n = nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// It is guaranteed that at least one trionic subarray exists. 
// 
//
// Related Topics Array Dynamic Programming 👍 71 👎 9


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun maxSumTrionic(nums: IntArray): Long {
        val n = nums.size
        if (n < 4) return 0

        val maxIncEnd = LongArray(n)
        maxIncEnd[0] = nums[0].toLong()
        for (i in 1 until n) {
            if (nums[i] > nums[i - 1]) {
                maxIncEnd[i] = nums[i].toLong() + maxOf(0L, maxIncEnd[i - 1])
            } else {
                maxIncEnd[i] = nums[i].toLong()
            }
        }

        val maxIncStart = LongArray(n)
        maxIncStart[n - 1] = nums[n - 1].toLong()
        for (i in n - 2 downTo 0) {
            if (nums[i] < nums[i + 1]) {
                maxIncStart[i] = nums[i].toLong() + maxOf(0L, maxIncStart[i + 1])
            } else {
                maxIncStart[i] = nums[i].toLong()
            }
        }

        var maxSum = Long.MIN_VALUE

        for (p in 1 until n - 2) {
            if (nums[p] > nums[p - 1]) {
                val bestLeftPart = maxIncEnd[p - 1] + nums[p]

                var q = p + 1
                var sumDecreasePart = 0L
                while (q < n - 1) {
                    if (nums[q] >= nums[q - 1]) break

                    sumDecreasePart += nums[q]

                    if (nums[q] < nums[q + 1]) {
                        val bestRightPart = maxIncStart[q + 1]
                        val currentTotal = bestLeftPart + sumDecreasePart + bestRightPart
                        if (currentTotal > maxSum) {
                            maxSum = currentTotal
                        }
                    }
                    q++
                }
            }
        }

        return maxSum
    }
}
