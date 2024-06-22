namespace Chess.Models.Pieces
{
    public class Pawn(PieceColor color) : Piece(color, PieceType.Pawn)
    {
        public bool HasMoved { get; private set; } = false;

        public override bool IsValidMove(Position oldPos, Position newPos)
        {
            // Checks if the pawn is moving diagonally
            if (Math.Abs(oldPos.Column - newPos.Column) == 1)
            {
                if (Color == PieceColor.White && oldPos.Row + 1 == newPos.Row)
                {
                    return true;
                }
                if (Color == PieceColor.Black && oldPos.Row - 1 == newPos.Row)
                {
                    return true;
                }
            }

            if (Color == PieceColor.White)
            {
                if (oldPos.Row + 1 == newPos.Row)
                {
                    return oldPos.Column == newPos.Column;
                }

                if (oldPos.Row == 1 && oldPos.Row + 2 == newPos.Row && !HasMoved)
                {
                    return oldPos.Column == newPos.Column;
                }
            }
            else if (Color == PieceColor.Black)
            {
                if (oldPos.Row - 1 == newPos.Row)
                {
                    return oldPos.Column == newPos.Column;
                }

                if (oldPos.Row == 6 && oldPos.Row - 2 == newPos.Row && !HasMoved)
                {
                    return oldPos.Column == newPos.Column;
                }
            }


            return false;
        }

        public void MarkAsMoved()
        {
            HasMoved = true;
        }
    }
}
