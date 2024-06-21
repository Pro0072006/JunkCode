using Chess.Enums;
using Chess.Util;

namespace Chess.Models.Pieces
{
    public class Pawn(PieceColor color) : Piece(color, PieceType.Pawn)
    {
        public bool HasMoved { get; private set; } = false;

        public override bool IsValidMove(Position from, Position to)
        {
            // Checks if the pawn is moving diagonally
            if (Math.Abs(from.Column - to.Column) == 1)
            {
                if (Color == PieceColor.White && to.Row == from.Row + 1)
                {
                    return true;
                }
                else if (Color == PieceColor.Black && to.Row == from.Row - 1)
                {
                    return true;
                }
            }

            if (Color == PieceColor.White)
            {
                if (to.Row == from.Row + 1)
                {
                    return from.Column == to.Column;
                }
                else if (from.Row == 1 && to.Row == from.Row + 2)
                {
                    return from.Column == to.Column;
                }
            }
            else if (Color == PieceColor.Black)
            {
                if (to.Row == from.Row - 1)
                {
                    return from.Column == to.Column;
                }
                else if (from.Row == 6 && to.Row == from.Row - 2)
                {
                    return from.Column == to.Column;
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
