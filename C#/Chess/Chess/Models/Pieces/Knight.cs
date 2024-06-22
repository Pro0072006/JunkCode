namespace Chess.Models.Pieces
{
    public class Knight(PieceColor color) : Piece(color, PieceType.Knight)
    {
        public override bool IsValidMove(Position oldPos, Position newPos)
        {
            if (Math.Abs(oldPos.Row - newPos.Row) == 2 && Math.Abs(oldPos.Column - newPos.Column) == 1)
            {
                return true;
            }

            if (Math.Abs(oldPos.Row - newPos.Row) == 1 && Math.Abs(oldPos.Column - newPos.Column) == 2)
            {
                return true;
            }

            return false;
        }
    }
}
