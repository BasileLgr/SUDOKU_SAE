public boolean NumLigne (int Ligne, String value)
{
	if ( Ligne <= this.LIGNE)
	{
		for( int col = 0 ; col < this.COLONNES; col++)
		{
			if(this.board[Ligne][col].equals(value))
			{
				return true;
			}
		}
		
	}
	return false ;
}