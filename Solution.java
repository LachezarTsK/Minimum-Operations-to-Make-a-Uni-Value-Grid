
public class Solution {

    private static final int NOT_POSSIBLE = -1;
    private static final int[] RANGE_OF_VALUES = {1, (int) Math.pow(10, 4)};

    private int rows;
    private int columns;

    public int minOperations(int[][] matrix, int step) {
        rows = matrix.length;
        columns = matrix[0].length;
        return findMinOperationsToMakeMatrixUnivalue(matrix, step);
    }

    private int findMinOperationsToMakeMatrixUnivalue(int[][] matrix, int step) {
        int totalOperations = 0;
        int medianValue = findMedianValue(matrix);

        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < columns; ++c) {
                if (!canPerformOperationsOnElement(matrix[r][c], medianValue, step)) {
                    return NOT_POSSIBLE;
                }
                totalOperations += getNumberOfOperations(matrix[r][c], medianValue, step);
            }
        }
        return totalOperations;
    }

    private int findMedianValue(int[][] matrix) {
        int[] frequency = new int[RANGE_OF_VALUES[1] + 1];
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < columns; ++c) {
                ++frequency[matrix[r][c]];
            }
        }

        int medianValue = 0;
        int numberOfElements = 0;
        int halfOfTotalNumberInputElements = 1 + (rows * columns) / 2;

        for (int value = RANGE_OF_VALUES[0]; value <= RANGE_OF_VALUES[1]; ++value) {
            numberOfElements += frequency[value];
            if (numberOfElements >= halfOfTotalNumberInputElements) {
                medianValue = value;
                break;
            }
        }

        return medianValue;
    }

    private boolean canPerformOperationsOnElement(int currentValue, int medianValue, int step) {
        return Math.abs(medianValue - currentValue) % step == 0;
    }

    private int getNumberOfOperations(int currentValue, int medianValue, int step) {
        return Math.abs(medianValue - currentValue) / step;
    }
}
