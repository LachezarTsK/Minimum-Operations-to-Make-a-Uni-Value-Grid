
#include <span>
#include <cmath>
#include <ranges> 
#include <vector>
using namespace std;


class Solution {

    static const int NOT_POSSIBLE = -1;
    static constexpr array<int, 2> RANGE_OF_VALUES = { 1, 10000 };

    int rows{};
    int columns{};

public:
    int minOperations(const vector<vector<int>>& matrix, int step) {
            rows = matrix.size();
            columns = matrix[0].size();
            return findMinOperationsToMakeMatrixUnivalue(matrix, step);
    }

private:
    int findMinOperationsToMakeMatrixUnivalue(span<const vector<int>>matrix, int step) const {
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

    int findMedianValue(span<const vector<int>> matrix) const {
        array<int, RANGE_OF_VALUES[1] + 1> frequency{};
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

    /*
    This is an alternative implementation of method 'findMedianValue(span<const vector<int>> matrix)',
    which applies the in-built method 'nth_element'

    int findMedianValue(span<const vector<int>> matrix) const {
        vector<int> flattenedMatrix(rows * columns);
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < columns; ++c) {
                flattenedMatrix[r * columns + c] = matrix[r][c];
            }
        }
        ranges::nth_element(flattenedMatrix, flattenedMatrix.begin() + flattenedMatrix.size() / 2, greater<>{});
        int medianValue = flattenedMatrix[flattenedMatrix.size() / 2];
        return medianValue;
    }
    */

    bool canPerformOperationsOnElement(int currentValue, int medianValue, int step) const {
        return abs(medianValue - currentValue) % step == 0;
    }

    int getNumberOfOperations(int currentValue, int medianValue, int step) const {
        return abs(medianValue - currentValue) / step;
    }
};
