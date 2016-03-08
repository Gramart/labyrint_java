package ija.gameinfo;

import ija.board.MazeBoard;
import ija.board.MazeCard;
import ija.board.MazeCard.CANGO;
import ija.design.gui.MyMenu;


public class BoardPlayersTreasures implements java.io.Serializable {
	
	public int name;
	public int position;
	public int looking_for;
	public int already_get[];
	public int radek=-1;
	public int sloupec=-1;
	public int get_treasures = 0;
	
	public BoardPlayersTreasures(int name, int radek, int sloupec, int looking_for, int already_get[], int get_treasures){
		this.name = name;
        this.radek = radek;
        this.sloupec = sloupec;
        this.looking_for = looking_for;
        this.already_get = already_get;
        //this.get_treasures = get_treasures;
        this.get_treasures = 4;
	}
	
	
	
	public static  BoardPlayersTreasures create(int cname, int cradek, int csloupec){
		int name = cname;
		int radek = cradek;
		int sloupec = csloupec;
		int looking_for = -1;
		int already_get[] = new int[12];
		int get_treasures = 0;
		
		BoardPlayersTreasures player = new BoardPlayersTreasures(name, radek, sloupec, looking_for, already_get, get_treasures);
		return player;
		
	}
	
	
	
	
}
