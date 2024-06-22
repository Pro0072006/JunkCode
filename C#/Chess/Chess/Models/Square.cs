namespace Chess.Models
{
    public class Square
    {
        public int Row { get; set; }
        public int Column { get; set; }
        public Piece? Piece { get; set; }
    }
}
