namespace Chess.Util
{
    public class ChessBoardBuilder
    {
        private readonly Board _board;

        public ChessBoardBuilder()
        {
            _board = new Board();
        }

        public ChessBoardBuilder AddPiece(Piece piece, int row, int column)
        {
            _board.PlacePiece(piece, new Position(row, column));
            return this;
        }

        public Board Build()
        {
            return _board;
        }
    }
}

