
using System;

public class Solution
{
    private static readonly int NOT_POSSIBLE = -1;
    private static readonly int[] RANGE_OF_VALUES = { 1, (int)Math.Pow(10, 4) };

    private int rows;
    private int columns;

    public int MinOperations(int[][] matrix, int step)
    {
        rows = matrix.Length;
        columns = matrix[0].Length;
        return FindMinOperationsToMakeMatrixUnivalue(matrix, step);
    }

    private int FindMinOperationsToMakeMatrixUnivalue(int[][] matrix, int step)
    {
        int totalOperations = 0;
        int medianValue = FindMedianValue(matrix);

        for (int r = 0; r < rows; ++r)
        {
            for (int c = 0; c < columns; ++c)
            {
                if (!CanPerformOperationsOnElement(matrix[r][c], medianValue, step))
                {
                    return NOT_POSSIBLE;
                }
                totalOperations += GetNumberOfOperations(matrix[r][c], medianValue, step);
            }
        }
        return totalOperations;
    }

    private int FindMedianValue(int[][] matrix)
    {
        int[] frequency = new int[RANGE_OF_VALUES[1] + 1];
        for (int r = 0; r < rows; ++r)
        {
            for (int c = 0; c < columns; ++c)
            {
                ++frequency[matrix[r][c]];
            }
        }

        int medianValue = 0;
        int numberOfElements = 0;
        int halfOfTotalNumberInputElements = 1 + (rows * columns) / 2;

        for (int value = RANGE_OF_VALUES[0]; value <= RANGE_OF_VALUES[1]; ++value)
        {
            numberOfElements += frequency[value];
            if (numberOfElements >= halfOfTotalNumberInputElements)
            {
                medianValue = value;
                break;
            }
        }

        return medianValue;
    }
    private bool CanPerformOperationsOnElement(int currentValue, int medianValue, int step)
    {
        return Math.Abs(medianValue - currentValue) % step == 0;
    }

    private int GetNumberOfOperations(int currentValue, int medianValue, int step)
    {
        return Math.Abs(medianValue - currentValue) / step;
    }
}
