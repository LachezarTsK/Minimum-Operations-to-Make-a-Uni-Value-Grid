# Minimum-Operations-to-Make-a-Uni-Value-Grid
Challenge at LeetCode.com. Tags: Math, Sorting.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Due to the small range of input [1, pow(10, 4)], as of March 2025, the median (which is needed to calculate the final solution) can be found efficiently with a frequency array. The solution for C++, along with the solution with a frequency array, offers an alternative with the in-built method nth_element. 

When the number of input elements is even, one of the two middle values around the (would be) median value is taken since for the purpose of this solution there is no need to calculate the actual median value in this case.

And when the number of input elements is odd, the actual median value is applied to the solution.

