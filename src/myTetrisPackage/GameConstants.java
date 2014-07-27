package myTetrisPackage;

public interface GameConstants {
	public static final Boolean DEBUG  = true;
	public static final int EMPTY_BLOCK = -1;
	public static final int L_PIECE   = 0;
    public static final int J_PIECE   = 1;
    public static final int I_PIECE   = 2;
    public static final int Z_PIECE   = 3;
    public static final int S_PIECE   = 4;
    public static final int O_PIECE   = 5;
    public static final int T_PIECE   = 6;

    public static final int LEFT      = 10;
    public static final int RIGHT     = 11;
    public static final int ROTATE    = 12;
    public static final int DOWN      = 13;
    public static final int FALL      = 14;
    public static final int UP		=15;

    public static final int BRICKWIDTH  = 20;
    public static final int BRICKHEIGHT  = 20;
    
    /**no of bricks per row */
    public static final int NBRICKSperROW = 10; 
    
    /** no of bricks that game contains vertically*/
    public static final int NROWS = 20; //debug later, set no different ot nbrickperrow
   
    /** 8 and 27 is added bcos the getBounds().width difference needs to be accounted for*/
    public static final int APPLICATION_WIDTH = NBRICKSperROW*BRICKWIDTH;
	public static final int APPLICATION_HEIGHT = NROWS*BRICKHEIGHT;
	
	/** Dimensions of game board (usually the same) */
    public static final int GAMEWIDTH   = APPLICATION_WIDTH;
    public static final int GAMEHEIGHT  = APPLICATION_HEIGHT;

}
