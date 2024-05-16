namespace TestLexer;

using ApoloLexer;

public class Program
{
    public static void Main(string[] args)
    {
        Parser parser = new();
        using StreamReader reader = new("operation.txt");
        var result = parser.Tokenize(reader);
        var resultParse = result.ToList();
        System.Console.WriteLine("hola");
    }
}
