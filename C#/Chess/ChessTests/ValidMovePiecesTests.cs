using Chess.Enums;
using Chess.Models.Pieces;
using Chess.Util;

namespace ChessTests
{
    public class ValidMovePiecesTests
    {
        [Theory]
        [InlineData(1, 0, 2, 0, true)]
        [InlineData(6, 0, 5, 0, false)]
        [InlineData(1, 0, 3, 0, true)]
        [InlineData(1, 0, 1, 1, false)]
        [InlineData(1, 0, 2, 1, true)]
        public void PawnValidTest(int x, int y, int x2, int y2, bool expected)
        {
            Pawn pawn = new(PieceColor.White);
            Position from = new(x, y);
            Position to = new(x2, y2);

            bool result = pawn.IsValidMove(from, to);

            Assert.Equal(expected, result);
        }
    }
}
