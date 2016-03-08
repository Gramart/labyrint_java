package ija.board;

public class MazeCard implements java.io.Serializable{
	/**
	 *  My_part
	 */
	private static final long serialVersionUID = -1155514089673159782L;
	//private MazeCard mc;
	public CANGO[] outway;
	public static enum CANGO{
		LEFT,
		UP,
		RIGHT,
		DOWN;
	}
    public char name;
    public  int poklad;
    public int player_on[];
    public int rotation;
    public int poloha;
    
	public MazeCard(CANGO[] outway, char name, int poklad, int player_on[], int rotation, int poloha){
		this.outway = outway;
        this.name = name;   
        this.poklad = poklad;
        this.player_on = player_on;
        this.poloha = poloha;
	}

	public static MazeCard create(String type, int poloha_karticky){
		CANGO[] outway = new CANGO[3];
		char name;
		int poklad;
		int player_on[] = new int[4];
		int rotation;
		switch(type){
		//karty vcetne otocenych
			case "x":
				outway[0] = CANGO.DOWN;
				outway[1] = CANGO.UP;
	            name = 'x';
	            poklad = 0;
			break;
			case "z":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.RIGHT;
	            name = 'z';
	            poklad = 0;
			break;
			case "A":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
	            name = 'A';
	            poklad = 0;
			break;
			case "B":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.DOWN;
	            name = 'B';
	            poklad = 0;
			break;
			case "C":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
	            name = 'C';
	            poklad = 0;
			break;
			case "D":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.DOWN;
	            name = 'D';
	            poklad = 0;
			break;
			case "E":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.RIGHT;
				outway[2] = CANGO.DOWN;
	            name = 'E';
	            poklad = 0;
			break;
			case "F":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.RIGHT;
				outway[2] = CANGO.UP;
	            name = 'F';
	            poklad = 0;
			break;
			case "G":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'G';
                poklad = 0;
			break;
			case "H":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'H';
                poklad = 0;
			break;
			//karty s pokladama
			case "I":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'I';
                poklad = 1;
			break;
			case "J":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'J';
                poklad = 2;
			break;
			case "K":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'K';
                poklad = 3;
			break;
			case "L":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'L';
                poklad = 4;
			break;
			case "M":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'M';
                poklad = 5;
			break;
			case "N":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.DOWN;
                name = 'N';
                poklad = 6;
			break;
			case "O":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.LEFT;
                name = 'O';
                poklad = 7;
			break;
			case "P":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.LEFT;
                name = 'P';
                poklad = 8;
			break;
			case "Q":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
				outway[2] = CANGO.LEFT;
                name = 'Q';
                poklad = 9;
			break;
			case "R":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.LEFT;
				outway[2] = CANGO.DOWN;
                name = 'R';
                poklad = 10;
			break;
			case "S":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.LEFT;
				outway[2] = CANGO.DOWN;
                name = 'S';
                poklad = 11;
			break;
			case "T":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.LEFT;
				outway[2] = CANGO.DOWN;
                name = 'T';
                poklad = 12;
			break;
			case "U":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.DOWN;
                name = 'U';
                poklad = 13;
			break;
			case "V":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.DOWN;
                name = 'V';
                poklad = 14;
			break;
			case "W":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.DOWN;
                name = 'W';
                poklad = 15;
			break;
			case "X":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
                name = 'X';
                poklad = 16;
			break;
			case "Y":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
                name = 'Y';
                poklad = 17;
			break;
			case "Z":
				outway[0] = CANGO.LEFT;
				outway[1] = CANGO.UP;
                name = 'Z';
                poklad = 18;
			break;
			case "a":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
                name = 'a';
                poklad = 19;
			break;
			case "b":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
                name = 'b';
                poklad = 20;
			break;
			case "c":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.UP;
                name = 'c';
                poklad = 21;
			break;
			case "d":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.DOWN;
                name = 'd';
                poklad = 22;
			break;
			case "e":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.DOWN;
                name = 'e';
                poklad = 23;
			break;
			case "f":
				outway[0] = CANGO.RIGHT;
				outway[1] = CANGO.DOWN;
                name = 'f';
                poklad = 24;
			break;
			
			default:
				throw new IllegalArgumentException(type);
			
		}
        player_on[0] = -1;
        player_on[1] = -1;
        player_on[2] = -1;
        player_on[3] = -1;
        rotation = 0;
        MazeCard mc = new MazeCard(outway, name, poklad, player_on, rotation, poloha_karticky);
		//MazeCard.outway = outway;
		return mc;
	}
    public void print(){
        //System.out.print(this.name);
    }
    
	public boolean canGo(MazeCard.CANGO dir){
		if (outway[0] == dir || outway[1] == dir || outway[2] == dir){
			return true;
		}
		else
			return false;
	}
	
	


	
}
