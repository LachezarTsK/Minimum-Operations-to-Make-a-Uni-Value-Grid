
/**
 * @param {number[][]} matrix
 * @param {number} step
 * @return {number}
 */
var minOperations = function (matrix, step) {
    this.NOT_POSSIBLE = -1;
    this.RANGE_OF_VALUES = [1, Math.pow(10, 4)];

    this.rows = matrix.length;
    this.columns = matrix[0].length;
    return findMinOperationsToMakeMatrixUnivalue(matrix, step);
};

/**
 * @param {number[][]} matrix
 * @param {number} step
 * @return {number}
 */
function findMinOperationsToMakeMatrixUnivalue(matrix, step) {
    let totalOperations = 0;
    const medianValue = findMedianValue(matrix);

    for (let r = 0; r < this.rows; ++r) {
        for (let c = 0; c < this.columns; ++c) {
            if (!canPerformOperationsOnElement(matrix[r][c], medianValue, step)) {
                return this.NOT_POSSIBLE;
            }
            totalOperations += getNumberOfOperations(matrix[r][c], medianValue, step);
        }
    }
    return totalOperations;
}

/**
 * @param {number[][]} matrix
 * @return {number}
 */
function findMedianValue(matrix) {
    const frequency = new Array(this.RANGE_OF_VALUES[1] + 1).fill(0);
    for (let r = 0; r < this.rows; ++r) {
        for (let c = 0; c < this.columns; ++c) {
            ++frequency[matrix[r][c]];
        }
    }

    let medianValue = 0;
    let numberOfElements = 0;
    const halfOfTotalNumberInputElements = Math.ceil((this.rows * this.columns) / 2);

    for (let value = this.RANGE_OF_VALUES[0]; value <= this.RANGE_OF_VALUES[1]; ++value) {
        numberOfElements += frequency[value];
        if (numberOfElements >= halfOfTotalNumberInputElements) {
            medianValue = value;
            break;
        }
    }

    return medianValue;
}

/**
 * @param {number} currentValue
 * @param {number} medianValue
 * @param {number} step
 * @return {boolean}
 */
function  canPerformOperationsOnElement(currentValue, medianValue, step) {
    return Math.abs(medianValue - currentValue) % step === 0;
}

/**
 * @param {number} currentValue
 * @param {number} medianValue
 * @param {number} step
 * @return {number}
 */
function getNumberOfOperations(currentValue, medianValue, step) {
    return Math.floor(Math.abs(medianValue - currentValue) / step);
}
