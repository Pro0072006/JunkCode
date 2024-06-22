namespace Chess.Models.Pieces
{
    public class Rook(PieceColor color) : Piece(color, PieceType.Rook)
    {
        public override bool IsValidMove(Position oldPos, Position newPos)
        {
            if (oldPos.Row == newPos.Row || oldPos.Column == newPos.Column)
            {
                return true;
            }

            return false;
        }
    }
}
