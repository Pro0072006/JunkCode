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
        public void PawnWhiteValidMoveTest(int x, int y, int x2, int y2, bool expected)
        {
            Pawn pawn = new(PieceColor.White);
            Position from = new(x, y);
            Position to = new(x2, y2);

            bool result = pawn.IsValidMove(from, to);

            Assert.Equal(expected, result);
        }

        [Theory]
        [InlineData(6, 0, 5, 0, true)]
        [InlineData(1, 0, 2, 0, false)]
        [InlineData(6, 0, 4, 0, true)]
        [InlineData(6, 0, 6, 1, false)]
        [InlineData(6, 0, 5, 1, true)]
        public void PawnBlackValidMoveTest(int x, int y, int x2, int y2, bool expected)
        {
            Pawn pawn = new(PieceColor.Black);
            Position from = new(x, y);
            Position to = new(x2, y2);

            bool result = pawn.IsValidMove(from, to);

            Assert.Equal(expected, result);
        }

        [Theory]
        [InlineData(5, 5, 7, 5, true)]
        [InlineData(7, 6, 7, 1, true)]
        [InlineData(1, 2, 2, 3, false)]
        public void RookValidMoveTest(int x, int y, int x2, int y2, bool expected)
        {
            Rook rook = new(PieceColor.White);
            Position oldPos = new(x, y);
            Position newPos = new(x2, y2);

            bool result = rook.IsValidMove(oldPos, newPos);

            Assert.Equal(expected, result);
        }

        [Theory]
        [InlineData(5, 5, 7, 6, true)]
        [InlineData(7, 6, 7, 1, false)]
        [InlineData(1, 2, 2, 3, false)]
        public void KnightValidMoveTest(int x, int y, int x2, int y2, bool expected)
        {
            Knight knight = new(PieceColor.White);
            Position oldPos = new(x, y);
            Position newPos = new(x2, y2);

            bool result = knight.IsValidMove(oldPos, newPos);

            Assert.Equal(expected, result);
        }

        [Theory]
        [InlineData(5, 5, 7, 7, true)]
        [InlineData(7, 6, 7, 1, false)]
        [InlineData(1, 2, 2, 3, true)]
        public void BishopValidMoveTest(int x, int y, int x2, int y2, bool expected)
        {
            Bishop bishop = new(PieceColor.White);
            Position oldPos = new(x, y);
            Position newPos = new(x2, y2);

            bool result = bishop.IsValidMove(oldPos, newPos);

            Assert.Equal(expected, result);
        }

        [Theory]
        [InlineData(5, 5, 6, 6, true)]
        [InlineData(7, 6, 7, 1, false)]
        [InlineData(1, 2, 2, 3, true)]
        public void ValidMoveTest(int x, int y, int x2, int y2, bool expected)
        {
            King king = new(PieceColor.White);
            Position oldPos = new(x, y);
            Position newPos = new(x2, y2);

            bool result = king.IsValidMove(oldPos, newPos);

            Assert.Equal(expected, result);
        }


    }
}
