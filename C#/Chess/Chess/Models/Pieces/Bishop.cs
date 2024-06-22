namespace Chess.Models.Pieces
{
    public class Bishop(PieceColor color) : Piece(color, PieceType.Bishop)
    {
        public override bool IsValidMove(Position oldPos, Position newPos)
        {
            if (Math.Abs(oldPos.Row - newPos.Row) == Math.Abs(oldPos.Column - newPos.Column))
            {
                return true;
            }

            return false;
        }
    }
}
