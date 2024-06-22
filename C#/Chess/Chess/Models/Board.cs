namespace Chess.Models
{
    public class Board
    {
        public Square[,] ChessBoard { get; private set; } = new Square[8, 8];

        public Board()
        {
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    ChessBoard[i, j] = new Square { Row = i, Column = j };
                }
            }
        }

        public void InitializeBoard()
        {
            // Initialize white pieces
            ChessBoard[0, 0].Piece = new Rook(PieceColor.White);
            ChessBoard[0, 1].Piece = new Knight(PieceColor.White);
            ChessBoard[0, 2].Piece = new Bishop(PieceColor.White);
            ChessBoard[0, 3].Piece = new Queen(PieceColor.White);
            ChessBoard[0, 4].Piece = new King(PieceColor.White);
            ChessBoard[0, 5].Piece = new Bishop(PieceColor.White);
            ChessBoard[0, 6].Piece = new Knight(PieceColor.White);
            ChessBoard[0, 7].Piece = new Rook(PieceColor.White);

            // Initialize black pieces
            ChessBoard[7, 0].Piece = new Rook(PieceColor.Black);
            ChessBoard[7, 1].Piece = new Knight(PieceColor.Black);
            ChessBoard[7, 2].Piece = new Bishop(PieceColor.Black);
            ChessBoard[7, 3].Piece = new Queen(PieceColor.Black);
            ChessBoard[7, 4].Piece = new King(PieceColor.Black);
            ChessBoard[7, 5].Piece = new Bishop(PieceColor.Black);
            ChessBoard[7, 6].Piece = new Knight(PieceColor.Black);
            ChessBoard[7, 7].Piece = new Rook(PieceColor.Black);

            for (int i = 0; i < 8; i++)
            {
                ChessBoard[1, i].Piece = new Pawn(PieceColor.White);
                ChessBoard[6, i].Piece = new Pawn(PieceColor.Black);
            }
        }

        public void PlacePiece(Piece piece, Position position)
        {
            if (position.Row < 0 || position.Row > 7 || position.Column < 0 || position.Column > 7)
                throw new ArgumentException("Invalid row or column");

            ChessBoard[position.Row, position.Column].Piece = piece;
        }

        public Piece? GetPiece(Position position)
        {
            if (position.Row < 0 || position.Row > 7 || position.Column < 0 || position.Column > 7)
                throw new ArgumentException("Invalid row or column");

            return ChessBoard[position.Row, position.Column].Piece;
        }
    }
}
