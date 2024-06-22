
namespace Chess.Models.Pieces
{
    public class King(PieceColor color) : Piece(color, PieceType.King)
    {
        public override bool IsValidMove(Position oldPos, Position newPos)
        {
            int deltaRow = Math.Abs(oldPos.Row - newPos.Row);
            int deltaColumn = Math.Abs(oldPos.Column - newPos.Column);

            if (deltaRow == 1 && deltaColumn == 1)
            {
                return true;
            }

            return false;
        }
    }
}
