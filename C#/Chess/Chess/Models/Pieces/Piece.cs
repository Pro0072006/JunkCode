using Chess.Enums;
using Chess.Util;

namespace Chess.Models.Pieces
{
    public abstract class Piece(PieceColor color, PieceType type)
    {
        public PieceColor Color { get; private set; } = color;
        public PieceType Type { get; private set; } = type;

        public abstract bool IsValidMove(Position oldPos, Position newPos);
    }
}
