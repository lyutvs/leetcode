//Given two sorted arrays nums1 and nums2 of size m and n respectively, return 
//the median of the two sorted arrays. 
//
// The overall run time complexity should be O(log (m+n)). 
//
// 
// Example 1: 
//
// 
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
// 
//
// Example 2: 
//
// 
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
// 
//
// 
// Constraints: 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics Array Binary Search Divide and Conquer 👍 31643 👎 3539


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val totalLength = nums1.size + nums2.size
        return if (totalLength % 2 == 1) {
            findKthElement(nums1, nums2, totalLength / 2 + 1).toDouble()
        } else {

            val leftMedian = findKthElement(nums1, nums2, totalLength / 2)

            val rightMedian = findKthElement(nums1, nums2, totalLength / 2 + 1)

            (leftMedian + rightMedian) / 2.0
        }

    }

    private fun findKthElement(nums1: IntArray, nums2: IntArray, k: Int): Int {
        if (nums1.isEmpty()) return nums2[k - 1]
        if (nums2.isEmpty()) return nums1[k - 1]

        val mid1 = nums1.size / 2
        val mid2 = nums2.size / 2
        val median1 = nums1[mid1]
        val median2 = nums2[mid2]

        return if (mid1 + mid2 < k - 1) {
            if (median1 > median2) {
                findKthElement(nums1, nums2.sliceArray(mid2 + 1 until nums2.size), k - mid2 - 1)
            } else {
                findKthElement(
                    nums1.sliceArray(mid1 + 1 until nums1.size), nums2, k - mid1 - 1
                )
            }
        } else {
            if (median1 > median2) {
                findKthElement(nums1.sliceArray(0 until mid1), nums2, k)
            } else {
                findKthElement(nums1, nums2.sliceArray(0 until mid2), k)
            }
        }
    }
}
