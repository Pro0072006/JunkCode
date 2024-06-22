namespace Chess.Models.Pieces
{
    public class Queen(PieceColor color) : Piece(color, PieceType.Queen)
    {
        public override bool IsValidMove(Position oldPos, Position newPos)
        {
            if (oldPos.Row == newPos.Row || oldPos.Column == oldPos.Column)
            {
                return true;
            }

            if (Math.Abs(oldPos.Row - newPos.Row) == Math.Abs(oldPos.Column - newPos.Column))
            {
                return true;
            }

            return false;
        }
    }
}
