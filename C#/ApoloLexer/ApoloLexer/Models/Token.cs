namespace ApoloLexer;

public readonly struct Token(TokenType type, string value)
{
    public TokenType Type { get; } = type;
    public string Value { get; } = value;
}

public enum TokenType
{
    Number,
    Variable,
    Function,
    Parenthesis,
    Operator,
    Comma,
    WhiteSpace
}
