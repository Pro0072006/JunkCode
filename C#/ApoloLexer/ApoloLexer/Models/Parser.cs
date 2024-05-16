using System.Text;

namespace ApoloLexer;

public class Parser
{
    private readonly Dictionary<string, Operator> Operators = new Dictionary<string, Operator>
    {
        ["+"] = new Operator { Name = "+", Precedence = 1 },
        ["-"] = new Operator { Name = "-", Precedence = 1 },
        ["*"] = new Operator { Name = "*", Precedence = 2 },
        ["/"] = new Operator { Name = "/", Precedence = 2 },
        ["^"] = new Operator { Name = "^", Precedence = 3 }
    };

    private bool CompareOperators(Operator op1, Operator op2)
    {
        return op1.Associative ? op1.Precedence < op2.Precedence : op1.Precedence <= op2.Precedence;
    }

    private bool CompareOperators(string op1, string op2)
    {
        return CompareOperators(Operators[op1], Operators[op2]);
    }

    private TokenType DetermineType(char ch)
    {
        if (char.IsLetter(ch))
            return TokenType.Variable;
        if (char.IsNumber(ch))
            return TokenType.Number;
        if (char.IsWhiteSpace(ch))
            return TokenType.WhiteSpace;
        if (ch == ',')
            return TokenType.Comma;
        if (ch == '(' || ch == ')')
            return TokenType.Parenthesis;
        if (Operators.ContainsKey(Convert.ToString(ch)))
            return TokenType.Operator;

        throw new Exception("Wrong character");
    }

    public IEnumerable<Token> Tokenize(TextReader reader)
    {
        StringBuilder token = new();

        int current;
        while ((current = reader.Read()) != -1)
        {
            var ch = (char)current;
            TokenType currentType = DetermineType(ch);
            if (currentType == TokenType.WhiteSpace)
                continue;

            token.Append(ch);

            var nextCh = reader.Peek();
            var nextType = nextCh != -1 ? DetermineType(ch) : TokenType.WhiteSpace;
            if (currentType != nextType)
            {
                if (nextCh == '(')
                    yield return new Token(TokenType.Function, token.ToString());
                else
                    yield return new Token(currentType, token.ToString());
                token.Clear();
            }
        }
    }
}
