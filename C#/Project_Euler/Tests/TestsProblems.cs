namespace Tests;

public class TestProblems
{
    [Theory(DisplayName = "Problem 1")]
    [InlineData(49, 543)]
    [InlineData(1000, 233168)]
    [InlineData(8456, 16687353)]
    [InlineData(19564, 89301183)]
    public void Test_Multiplies3Or5(int number, int expected)
    {
        int result = Problems.Problem_1_MultiplesOf3Or5(number);
        Assert.Equal(expected, result);
    }

    [Theory]
    [InlineData(8, 10)]
    [InlineData(10, 10)]
    [InlineData(34, 44)]
    [InlineData(4000000, 4613732)]
    public void Test_EvenFibonacci(int number, int expected)
    {
        int result = Problems.Problem_2_EvenFibonacci(number);
        Assert.Equal(expected, result);
    }

    [Theory]
    [InlineData(2, 2)]
    [InlineData(3, 3)]
    [InlineData(7, 7)]
    [InlineData(8, 2)]
    [InlineData(13195, 29)]
    public void Test_LargestPrimeFactor(int number, int expected)
    {
        int result = Problems.Problem_3_LargestPrimeFactor(number);
        Assert.Equal(expected, result);
    }
}
