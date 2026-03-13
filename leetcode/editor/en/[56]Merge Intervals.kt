//Given an array of intervals where intervals[i] = [starti, endi], merge all 
//overlapping intervals, and return an array of the non-overlapping intervals that 
//cover all the intervals in the input. 
//
// 
// Example 1: 
//
// 
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
// 
//
// Example 3: 
//
// 
//Input: intervals = [[4,7],[1,4]]
//Output: [[1,7]]
//Explanation: Intervals [1,4] and [4,7] are considered overlapping.
// 
//
// 
// Constraints: 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics Array Sorting 👍 24517 👎 902


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        // 간격이 하나만 있으면 그대로 반환
        if (intervals.size <= 1) return intervals


        // 시작점 기준으로 정렬
        intervals.sortBy { it[0] }

        // 결과를 저장할 리스트
        val result = mutableListOf<IntArray>()

        // 첫 번째 간격을 현재 병합 중인 간격으로 설정
        var current = intervals[0]

        // 두 번째 간격부터 순회
        for (i in 1 until intervals.size) {
            val next = intervals[i]

            // 현재 간격과 다음 간격이 겹치는지 확인
            if (current[1] >= next[0]) {
                // 겹치면 병합: 끝점을 두 간격 중 더 큰 값으로 업데이트
                current[1] = maxOf(current[1], next[1])
            } else {
                // 겹치지 않으면 현재 간격을 결과에 추가
                result.add(current)
                // 다음 간격을 새로운 현재 간격으로 설정
                current = next
            }
        }

        // 마지막 간격 추가
        result.add(current)

        return result.toTypedArray()
    }
}
//leetcode submit region end(Prohibit modification and deletion)
