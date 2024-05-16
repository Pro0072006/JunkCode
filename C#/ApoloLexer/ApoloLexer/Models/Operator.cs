namespace ApoloLexer;

public class Operator
{
    public string Name { get; set; } = "";
    public int Precedence { get; set; }
    public bool Associative { get; set; }
}
