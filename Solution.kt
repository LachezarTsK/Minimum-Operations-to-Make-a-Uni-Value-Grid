
import kotlin.math.abs
import kotlin.math.pow

class Solution {

    private companion object {
        const val NOT_POSSIBLE = -1
        val RANGE_OF_VALUES = intArrayOf(1, (10.0).pow(4).toInt())
    }

    private var rows = 0
    private var columns = 0

    fun minOperations(matrix: Array<IntArray>, step: Int): Int {
        rows = matrix.size
        columns = matrix[0].size
        return findMinOperationsToMakeMatrixUnivalue(matrix, step)
    }

    private fun findMinOperationsToMakeMatrixUnivalue(matrix: Array<IntArray>, step: Int): Int {
        var totalOperations = 0
        val medianValue = findMedianValue(matrix)

        for (r in 0..<rows) {
            for (c in 0..<columns) {
                if (!canPerformOperationsOnElement(matrix[r][c], medianValue, step)) {
                    return NOT_POSSIBLE
                }
                totalOperations += getNumberOfOperations(matrix[r][c], medianValue, step)
            }
        }
        return totalOperations
    }

    private fun findMedianValue(matrix: Array<IntArray>): Int {
        val frequency = IntArray(RANGE_OF_VALUES[1] + 1)
        for (r in 0..<rows) {
            for (c in 0..<columns) {
                ++frequency[matrix[r][c]]
            }
        }

        var medianValue = 0
        var numberOfElements = 0
        val halfOfTotalNumberInputElements = 1 + (rows * columns) / 2

        for (value in RANGE_OF_VALUES[0]..RANGE_OF_VALUES[1]) {
            numberOfElements += frequency[value]
            if (numberOfElements >= halfOfTotalNumberInputElements) {
                medianValue = value
                break
            }
        }

        return medianValue
    }

    private fun canPerformOperationsOnElement(currentValue: Int, medianValue: Int, step: Int): Boolean {
        return abs(medianValue - currentValue) % step == 0
    }

    private fun getNumberOfOperations(currentValue: Int, medianValue: Int, step: Int): Int {
        return abs(medianValue - currentValue) / step
    }
}
