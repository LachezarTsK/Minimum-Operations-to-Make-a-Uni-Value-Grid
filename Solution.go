
package main

import "math"

const NOT_POSSIBLE = -1
var RANGE_OF_VALUES = [2]int{1, int(math.Pow(10.0, 4.0))}

var rows int
var columns int

func minOperations(matrix [][]int, step int) int {
    rows = len(matrix)
    columns = len(matrix[0])
    return findMinOperationsToMakeMatrixUnivalue(matrix, step)
}

func findMinOperationsToMakeMatrixUnivalue(matrix [][]int, step int) int {
    totalOperations := 0
    medianValue := findMedianValue(matrix)

    for r := range rows {
        for c := range columns {
            if !canPerformOperationsOnElement(matrix[r][c], medianValue, step) {
                return NOT_POSSIBLE
            }
            totalOperations += getNumberOfOperations(matrix[r][c], medianValue, step)
        }
    }
    return totalOperations
}

func findMedianValue(matrix [][]int) int {
    frequency := make([]int, RANGE_OF_VALUES[1]+1)
    for r := range rows {
        for c := range columns {
            frequency[matrix[r][c]]++
        }
    }

    medianValue := 0
    numberOfElements := 0
    halfOfTotalNumberInputElements := 1 + (rows * columns) / 2

    for value := RANGE_OF_VALUES[0]; value <= RANGE_OF_VALUES[1]; value++ {
        numberOfElements += frequency[value]
        if numberOfElements >= halfOfTotalNumberInputElements {
            medianValue = value
            break
        }
    }

    return medianValue
}

func canPerformOperationsOnElement(currentValue int, medianValue int, step int) bool {
    return int(math.Abs(float64(medianValue - currentValue))) % step == 0
}

func getNumberOfOperations(currentValue int, medianValue int, step int) int {
    return int(math.Abs(float64(medianValue - currentValue))) / step
}
