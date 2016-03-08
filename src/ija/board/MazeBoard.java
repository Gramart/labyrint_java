package ija.board;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import ija.board.MazeCard.CANGO;
import ija.design.gui.MainGui;
import ija.design.gui.MyMenu;
import ija.gameinfo.BoardPlayersTreasures;

@SuppressWarnings("serial")
public class MazeBoard implements java.io.Serializable{ 
	
	private MazeBoard mz;
	public static MazeField[][] board;
	public static BoardPlayersTreasures[] players_arr; 
	public int id = 4445;
	public int chci_pokladu;
	//private String[] sutrtype  = new String[3];
	public static String[] sutrtype = {"x","z","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f" };
	
	public static String[] leve_horni = {"B", "d", "e", "f"};
	public static String[] prave_horni = {"D", "U", "V", "W"};
	public static String[] leve_dolni = {"C", "a", "b", "c"};
	public static String[] prave_dolni = {"A", "X", "Y", "Z"};
	public static String[] tecka = {"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	public static String[] leva_tecka = {"H", "I", "J", "K"};
	public static String[] prava_tecka = {"G", "L", "M", "N"};
	public static String[] dolni_tecka = {"F", "O", "P", "Q"};
	public static String[] horni_tecka = {"E","R", "S", "T"};
	
	public static int cislo_ukolu = 0;
	
	public static int[] poklady_na_plose = new int[24];

	public static MazeCard freesutr;
    public static int bsize;
    private boolean createflag = false;
    

	public MazeBoard createMazeBoard(int n){
		board = new MazeField[n][n];
		MazeBoard mz = new MazeBoard();
		bsize = n;
		for (int i = 0; i < n;i++){
			for (int j = 0; j < n;j++){
				board[i][j] = new MazeField(i+1,j+1);
			}
		}
		return mz;
	}
	
	public void newGame(){
		int q = 0;
		int max = 33;
		int min = 10;
		//int chci_pokladu = 24;
		int startovnich = 3;
		int opakovani = 0;
		boolean start = false;
		boolean generovano = false;
		boolean not_on_board = true;
		boolean karticka_tecko = false;
		float ftecek = (bsize*bsize*9)/24;
		MazeCard new_card;
		players_arr = new BoardPlayersTreasures[4];
		

		int tecek = (int)ftecek;
		tecek-=4;
		//System.out.print(tecek);

		Random random = new Random();
		
		for (int p = 0; p < 24; p++){
			poklady_na_plose[p] = -1;
		}
		//System.out.println("c.pokladu: "+chci_pokladu);
		for (int i = 0; i < bsize;i++){
			for (int j = 0; j < bsize;j++){
				generovano = false;
				
					
				//System.out.print("\nVENKU Z CYKLU!!!!!!!!!!!!!!!!!!!!!!!!!!\n ");
				
				/*
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				opakovani = 0;
				not_on_board = true;
				while (not_on_board == true){//opakuj dokud nevygenerujes novy poklad nebo prazdnou karticku
					//System.out.print("cyklim\n");
					start = false;
					karticka_tecko = false;
					not_on_board = false;
					opakovani++;
					/*
					System.out.print("\nZBYVA FREE: ");
					System.out.print(bsize*bsize-((i)*bsize + (j)));
					System.out.print("\nZ TOHO STARTOVNICH ");
					System.out.print(startovnich);
					System.out.print("\nA TECEK ");
					System.out.print(tecek);
					System.out.print("\nZBYVA POKLADU: ");
					System.out.print(chci_pokladu - q);
					System.out.print(i);
					System.out.print(j);
					System.out.print(opakovani);
					*/

					
					if ((((i == 0) && (j == 0)) || ((i == 0) && (j == bsize-1)) || ((i == bsize-1) && (j == 0)) || ((i == bsize-1) && (j == bsize-1))) && (opakovani>3))
					{
						//System.out.print("\nJSEM SPRAVNE: ");
	
						if ((j == 0) && (i == 0)) { board[i][j].putCard(MazeCard.create(leve_horni[0], ((i)*bsize + (j)))); start = true;}
						else if ((j == bsize-1) && (i == 0)) { board[i][j].putCard(MazeCard.create(prave_horni[0], ((i)*bsize + (j)))); start = true;}
						else if ((j == 0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(leve_dolni[0], ((i)*bsize + (j)))); start = true;}
						else if ((j == bsize-1) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(prave_dolni[0], ((i)*bsize + (j)))); start = true;}
					}
					else if((((j == 0) && (i%2==0)) || ((i == 0) && (j%2==0)) || ((j == bsize-1) && (i%2==0)) || ((j%2==0) && (i == bsize-1)) || ((j%2==0) && (i%2==0))) && (opakovani>3)){
						if ((j == 0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(leva_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
						else if ((i == 0) && (j%2==0)) { board[i][j].putCard(MazeCard.create(horni_tecka[0],((i)*bsize + (j)))); karticka_tecko = true;}
						else if ((j == bsize-1) && (i%2==0)) { board[i][j].putCard(MazeCard.create(prava_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
						else if ((j%2==0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(dolni_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
						else if ((j%2==0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(tecka[random.nextInt(4)], ((i)*bsize + (j)))); karticka_tecko = true;}
					}
					else{
							if ((bsize*bsize-((i)*bsize + (j)) - startovnich - tecek > chci_pokladu - q) && (chci_pokladu - q != 0)){	//mam dostatek mista na poklady	
		
								if ((random.nextInt(100) % 3 == 0) && (q < chci_pokladu)){	//random generuj bud z pokladu nebo z policek
									//System.out.print("\nPRVNI IF ");
	
									if ((j == 0) && (i == 0)) { board[i][j].putCard(MazeCard.create(leve_horni[random.nextInt(3)+1], ((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == 0)) { board[i][j].putCard(MazeCard.create(prave_horni[random.nextInt(3)+1], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(leve_dolni[random.nextInt(3)+1],((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(prave_dolni[random.nextInt(3)+1], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(leva_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((i == 0) && (j%2==0)) { board[i][j].putCard(MazeCard.create(horni_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j == bsize-1) && (i%2==0)) { board[i][j].putCard(MazeCard.create(prava_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(dolni_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(tecka[random.nextInt((15 - 4) + 1) + 4], ((i)*bsize + (j)))); karticka_tecko = true;}
									else board[i][j].putCard(MazeCard.create(sutrtype[random.nextInt((max - min) + 1) + min], ((i)*bsize + (j))));
								}
								else{
									//System.out.print("\nDRUHY IF ");
	
									if ((j == 0) && (i == 0)) { board[i][j].putCard(MazeCard.create(leve_horni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == 0)) { board[i][j].putCard(MazeCard.create(prave_horni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(leve_dolni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(prave_dolni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(leva_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((i == 0) && (j%2==0)) { board[i][j].putCard(MazeCard.create(horni_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j == bsize-1) && (i%2==0)) { board[i][j].putCard(MazeCard.create(prava_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(dolni_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(tecka[random.nextInt(4)], ((i)*bsize + (j)))); karticka_tecko = true;}
									else board[i][j].putCard(MazeCard.create(sutrtype[random.nextInt(10)], ((i)*bsize + (j))));
								}
							}else//pokud uz mi misto doslo
							{
								if (((q < chci_pokladu) || (bsize*bsize-((i)*bsize + (j)) - startovnich - tecek <= chci_pokladu - q)) && (chci_pokladu - q != 0)) //nemam vsechny poklady nebo nemam misto
								{
									//System.out.print("\nTRETI IF ");
	
									if ((j == 0) && (i == 0)) { board[i][j].putCard(MazeCard.create(leve_horni[random.nextInt(3)+1], ((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == 0)) { board[i][j].putCard(MazeCard.create(prave_horni[random.nextInt(3)+1], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(leve_dolni[random.nextInt(3)+1], ((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(prave_dolni[random.nextInt(3)+1], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(leva_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((i == 0) && (j%2==0)) { board[i][j].putCard(MazeCard.create(horni_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j == bsize-1) && (i%2==0)) { board[i][j].putCard(MazeCard.create(prava_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(dolni_tecka[random.nextInt(3)+1], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(tecka[random.nextInt((15 - 4) + 1) + 4], ((i)*bsize + (j)))); karticka_tecko = true;}
									else board[i][j].putCard(MazeCard.create(sutrtype[random.nextInt((max - min) + 1) + min], ((i)*bsize + (j))));		
								}
								else//mam misto a muzu generovat jen policka
								{
									//System.out.print("\nCTVRTY IF ");
	
									if ((j == 0) && (i == 0)) { board[i][j].putCard(MazeCard.create(leve_horni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == 0)) { board[i][j].putCard(MazeCard.create(prave_horni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(leve_dolni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == bsize-1) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(prave_dolni[0], ((i)*bsize + (j)))); start = true;}
									else if ((j == 0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(leva_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((i == 0) && (j%2==0)) { board[i][j].putCard(MazeCard.create(horni_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j == bsize-1) && (i%2==0)) { board[i][j].putCard(MazeCard.create(prava_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i == bsize-1)) { board[i][j].putCard(MazeCard.create(dolni_tecka[0], ((i)*bsize + (j)))); karticka_tecko = true;}
									else if ((j%2==0) && (i%2==0)) { board[i][j].putCard(MazeCard.create(tecka[random.nextInt(4)], ((i)*bsize + (j)))); karticka_tecko = true;}
									else board[i][j].putCard(MazeCard.create(sutrtype[random.nextInt(10)], ((i)*bsize + (j))));		
								}
							}
							//System.out.print("\nvygeneroval jsem: ");
							//System.out.print(board[i][j].getCard().poklad);
	
					
						for (int z = 0; z < q; z++){//zjistim jestli uz tam neni, pokud ano, jdu generovat novy a prepisu ho 
								//System.out.print("\nHLEDAM");
								//System.out.print(poklady_na_plose[z]);
							if(poklady_na_plose[z] == board[i][j].getCard().poklad){
								not_on_board = true;	
								//System.out.print("\nZNOVUUUUUUUUUUUU");
							} 
						}
						//System.out.print("\nZA FOREM");

					}
						
					if ((start == true) && (not_on_board == false)) startovnich--;
					if ((karticka_tecko == true) && (not_on_board == false)) tecek--;
					
				}//uspesne jsem vygeneroval poklad a muzu ho ulozit a jit na dalsi
				
				if ((board[i][j].getCard().poklad != 0) && (board[i][j].getCard().poklad != -1)){//ale 0 a -1 nejsou poklady!
					poklady_na_plose[q] = board[i][j].getCard().poklad;
					q++;
				}
				
				
				//prvotni rozmisteni hracu
				new_card = board[i][j].getCard();
				if ((i == 0) && (j == 0)){
					new_card.player_on[0] = 0;
					players_arr[0] = BoardPlayersTreasures.create(0, 0, 0);
					
				}
				
				if ((i == bsize-1) && (j == bsize-1)){
					new_card.player_on[1] = 1;
					players_arr[1] = BoardPlayersTreasures.create(1,bsize-1,bsize-1);

				}
				
				if ((i == 0) && (j == bsize-1) && (MyMenu.gamernum >= 3)){
					new_card.player_on[2] = 2;
					players_arr[2] = BoardPlayersTreasures.create(2,0, bsize-1);

				}
				
				if ((i == bsize-1) && (j == 0) && (MyMenu.gamernum == 4)){
					new_card.player_on[3] = 3;
					players_arr[3] = BoardPlayersTreasures.create(3,bsize-1,0);

				}
			}
		}

		
		createflag = true;
		freesutr = MazeCard.create(sutrtype[random.nextInt(10)], -1);
		
		
		Random rnd = new Random();
	    for (int i = chci_pokladu - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      int a = poklady_na_plose[index];
	      poklady_na_plose[index] = poklady_na_plose[i];
	      poklady_na_plose[i] = a;
	    }
	    
	    if (players_arr[0].radek != -1) 
	    	{
    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
	    		players_arr[0].looking_for = poklady_na_plose[cislo_ukolu++];
	    	}
	    if (players_arr[1].radek != -1) 
	    {
    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
	    	players_arr[1].looking_for = poklady_na_plose[cislo_ukolu++];
	    }
	    if (MyMenu.gamernum >= 3){
	    	if (players_arr[2].radek != -1) {
	    		
	    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
	    		players_arr[2].looking_for = poklady_na_plose[cislo_ukolu++];
	    	}
	    }
	    if (MyMenu.gamernum == 4) {
	    	if (players_arr[3].radek != -1) {
	    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
	    		players_arr[3].looking_for = poklady_na_plose[cislo_ukolu++];
	    	}
	    }
	}
	
	
	public static int getRotation(int poloha, boolean free) {
		int rotation = 0;
		
		if (free == true) rotation = freesutr.rotation;
		else{
			for (int i = 1; i <= bsize; i++){
				for (int j = 1; j <= bsize; j++){
					MazeField card = get(i,j);
					MazeCard card2 = card.getCard();
					if (poloha == card2.poloha){
						rotation = card2.rotation;
						j = bsize;
						i = bsize;
					}
				}
			}
		}
		return rotation;
	}

	public static void setRotation(int rotation, int poloha, boolean free) {
		/*
		System.out.print("\ndostal jsem do fce:");
		System.out.print(rotation);

		System.out.print("\nPOLOHA:");
		System.out.print(poloha);
		*/
		if (free == true) freesutr.rotation = rotation;
		else{
			for (int i = 1; i <= bsize; i++){
				for (int j = 1; j <= bsize; j++){
					MazeField card = get(i,j);
					MazeCard card2 = card.getCard();
					//System.out.print("\nPOLOHA KARTICKY ");
					//System.out.print(card2.poloha);
	
					if (poloha == card2.poloha){
						//System.out.print("\nNASEL JSEM POLOHU");
						card2.rotation = rotation;
						j = bsize;
						i = bsize;
					}
				}
			}	
		}
	}
	
	public void print(){
		for (int i = 1;i<=this.bsize;i++){
            for (int j = 1;j<=this.bsize;j++){
                this.get(i, j).print();
            }
            //System.out.println();
        }
		//System.out.print("prazdna karta: ");
		
		if (this.getFreeCard() == null){
			//System.out.print('_');
		}
		else{
			this.getFreeCard().print();
		}
		//System.out.println();		

	}
	
	public static MazeField get(int r, int c){
		//System.out.println(bsize);
		
		if (r <= bsize && c <= bsize && c > 0 && r > 0){
			return board[r-1][c-1];
		}
		else{
			return null;
		}
	}
	
	public MazeCard getFreeCard(){
		if (createflag == true){
			return freesutr;
		}
		else{
			return null;
		}
	}
	
	public void shift(MazeField mf){
		int column = mf.col()-1;
		int radek = mf.row()-1;

		if (column >= 0 && column < bsize && radek >= 0 && radek < bsize)
		{
			if (mf.row() == 1){ // vlozeni kamene z vrchu na sudy sloupec
				if (((column+1) % 2) == 0){
					MazeCard newfree = board[bsize-1][column].getCard(); //ulozeni sutr na poslednim radku
					if (newfree.player_on[0] != -1) players_arr[0].radek = 0;
					if (newfree.player_on[1] != -1) players_arr[1].radek = 0;
					if (newfree.player_on[2] != -1) players_arr[2].radek = 0;
					if (newfree.player_on[3] != -1) players_arr[3].radek = 0;
					board[bsize-1][column].putCard(newfree);
					
					for (int index = bsize-2; index >=0; index--){ //jdeme od konce zpatky
						board[index+1][column].putCard(board[index][column].getCard());
						MazeCard karta = board[index][column].getCard();
						karta.poloha += bsize;
						if (karta.player_on[0] != -1) players_arr[0].radek += 1;
						if (karta.player_on[1] != -1) players_arr[1].radek += 1;
						if (karta.player_on[2] != -1) players_arr[2].radek += 1;
						if (karta.player_on[3] != -1) players_arr[3].radek += 1;
					}
					freesutr.player_on[0] = newfree.player_on[0];
					freesutr.player_on[1] = newfree.player_on[1];
					freesutr.player_on[2] = newfree.player_on[2];
					freesutr.player_on[3] = newfree.player_on[3];

					
					board[0][column].putCard(freesutr); //na prvni policko pujde volny sutr
					MazeCard karta = board[0][column].getCard();
					karta.poloha = column;
				    //System.out.println("MUUUUUJ COLOUMN");
				    //System.out.println(column);
				    
					freesutr = newfree; // posledni na radku se stava volnym sutrem
					freesutr.poloha = -1;
					//freesutr.rotation = 0;				
					}
			}
			else if (mf.row() == (bsize)){ // vlozeni sutru od spodu nahoru na sudy sloupec
				if (((column+1) % 2) == 0){
					MazeCard newfree = board[0][column].getCard();
					if (newfree.player_on[0] != -1) players_arr[0].radek = bsize-1;
					if (newfree.player_on[1] != -1) players_arr[1].radek = bsize-1;
					if (newfree.player_on[2] != -1) players_arr[2].radek = bsize-1;
					if (newfree.player_on[3] != -1) players_arr[3].radek = bsize-1;
					board[0][column].putCard(newfree);

					for (int index = 0; index < bsize-1; index++){
						board[index][column].putCard(board[index+1][column].getCard());
						MazeCard karta = board[index][column].getCard();
						karta.poloha -= bsize;
						if (karta.player_on[0] != -1) players_arr[0].radek -= 1;
						if (karta.player_on[1] != -1) players_arr[1].radek -= 1;
						if (karta.player_on[2] != -1) players_arr[2].radek -= 1;
						if (karta.player_on[3] != -1) players_arr[3].radek -= 1;
					}
					freesutr.player_on[0] = newfree.player_on[0];
					freesutr.player_on[1] = newfree.player_on[1];
					freesutr.player_on[2] = newfree.player_on[2];
					freesutr.player_on[3] = newfree.player_on[3];
					board[bsize-1][column].putCard(freesutr);
					MazeCard karta = board[bsize-1][column].getCard();
					karta.poloha = radek*bsize+column;
					freesutr = newfree; // posledni na radku se stava volnym sutrem
					freesutr.poloha = -1;
					//freesutr.rotation = 0;
				}
			}
			
			else if (mf.col() == 1){ //vlozeni sutr zleva doprava
			   // System.out.println("yep");
				if (((radek+1) % 2) == 0){
					MazeCard newfree = board[radek][bsize-1].getCard();
					if (newfree.player_on[0] != -1) players_arr[0].sloupec = 0;
					if (newfree.player_on[1] != -1) players_arr[1].sloupec = 0;
					if (newfree.player_on[2] != -1) players_arr[2].sloupec = 0;
					if (newfree.player_on[3] != -1) players_arr[3].sloupec = 0;
					board[radek][bsize-1].putCard(newfree);

					for (int index = bsize-2;index >=0; index--){ //jdeme od konce zpatky
					    //System.out.println(index);
						board[radek][index+1].putCard(board[radek][index].getCard());
						MazeCard karta = board[radek][index+1].getCard();
						karta.poloha += 1;
						if (karta.player_on[0] != -1) players_arr[0].sloupec += 1;
						if (karta.player_on[1] != -1) players_arr[1].sloupec += 1;
						if (karta.player_on[2] != -1) players_arr[2].sloupec += 1;
						if (karta.player_on[3] != -1) players_arr[3].sloupec += 1;
					}
					freesutr.player_on[0] = newfree.player_on[0];
					freesutr.player_on[1] = newfree.player_on[1];
					freesutr.player_on[2] = newfree.player_on[2];
					freesutr.player_on[3] = newfree.player_on[3];
					board[radek][0].putCard(freesutr); //na prvni policko pujde volny sutr
					MazeCard karta = board[radek][0].getCard();
					karta.poloha = radek*bsize;
					freesutr = newfree; // posledni na radku se stava volnym sutrem
					freesutr.poloha = -1;
					//freesutr.rotation = 0;
				}
			}
			
			else if (mf.col() == (bsize)){ //vlozeni zprava doleva
				if (((radek+1) % 2) == 0){
					MazeCard newfree = board[radek][0].getCard();
					if (newfree.player_on[0] != -1) players_arr[0].sloupec = bsize-1;
					if (newfree.player_on[1] != -1) players_arr[1].sloupec = bsize-1;
					if (newfree.player_on[2] != -1) players_arr[2].sloupec = bsize-1;
					if (newfree.player_on[3] != -1) players_arr[3].sloupec = bsize-1;
					board[radek][0].putCard(newfree);

					for (int index = 0; index < bsize-1; index++){
						board[radek][index].putCard(board[radek][index+1].getCard());
						MazeCard karta = board[radek][index+1].getCard();
						karta.poloha -= 1;
						if (karta.player_on[0] != -1) players_arr[0].sloupec -= 1;
						if (karta.player_on[1] != -1) players_arr[1].sloupec -= 1;
						if (karta.player_on[2] != -1) players_arr[2].sloupec -= 1;
						if (karta.player_on[3] != -1) players_arr[3].sloupec -= 1;
					}
					freesutr.player_on[0] = newfree.player_on[0];
					freesutr.player_on[1] = newfree.player_on[1];
					freesutr.player_on[2] = newfree.player_on[2];
					freesutr.player_on[3] = newfree.player_on[3];
					board[radek][bsize-1].putCard(freesutr);
					MazeCard karta = board[radek][bsize-1].getCard();
					karta.poloha = (radek)*bsize+bsize-1;
					freesutr = newfree;
					freesutr.poloha = -1;
					//freesutr.rotation = 0;
				}
			}
			else{

			}
		}
			
	}
	
	public static boolean move_players(int player, int direction){
		int poloha = -1;
		int radek = -1;
		int sloupec = -1;
		int hracVSpoklad;
		boolean konec = false;
		
		
		
		
		
		
		
		//ziskani polohy aktualniho hrace		
		radek = players_arr[player].radek;
		
		sloupec = players_arr[player].sloupec;
	    /*System.out.println("\nHrac je na radku");
	    System.out.println(radek);

	    System.out.println("\nHrac je na sloupci:");
	    System.out.println(sloupec);
	    
	    System.out.println("\nchce jit:");
	    System.out.println(direction);
	    
	    System.out.println("\nSMERY PUVODNI:");
	    System.out.println(board[radek][sloupec].getCard().outway[0]+"\n"+board[radek][sloupec].getCard().outway[1]+"\n"+board[radek][sloupec].getCard().outway[2]);
*/



		MazeCard puvodni;
		MazeCard nova;
		
		switch(direction){
		//nahoru
		case 0:
			if (radek != 0){
				puvodni = MazeBoard.board[radek][sloupec].getCard();
				nova = MazeBoard.board[radek-1][sloupec].getCard();
			  //  System.out.println("\nPRVNI IF");

			    //System.out.println("\nSMERY PUVODNI:");
			    //System.out.println(board[radek][sloupec].getCard().outway[0]+"\n"+board[radek][sloupec].getCard().outway[1]+"\n"+board[radek][sloupec].getCard().outway[2]);
	
			    
			    //System.out.println("\nSMERY NOVA:");
			    //System.out.println(nova.outway[0]+"\n"+nova.outway[1]+"\n"+nova.outway[2]);
				
			    
			    if (((puvodni.outway[0] == CANGO.UP) || (puvodni.outway[1] == CANGO.UP) || (puvodni.outway[2] == CANGO.UP)) && 
						((nova.outway[0] == CANGO.DOWN) || (nova.outway[1] == CANGO.DOWN) || (nova.outway[2] == CANGO.DOWN))){
				    //System.out.println("\nPRVNI IF");

					
					players_arr[player].radek = radek -1;
					puvodni.player_on[player] = -1;	//upravim hrace na desce
					nova.player_on[player] = player;
				
					board[radek][sloupec].putCard(puvodni);//nahraju zpet upravene karticky
					board[radek-1][sloupec].putCard(nova);
				    /*System.out.println("\nHrac cislo:");
				    System.out.println(player);
				    System.out.println("\nhleda");
				    System.out.println( players_arr[player].looking_for);
				    System.out.println("\nUz ziskal:");
				    System.out.println( players_arr[player].already_get[0]);*/


					
					if (players_arr[player].looking_for == board[radek-1][sloupec].getCard().poklad){
						//players_arr[player].already_get[players_arr[player].already_get.length] = players_arr[player].looking_for;
			    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
						players_arr[player].looking_for = poklady_na_plose[cislo_ukolu++];
						players_arr[player].get_treasures += 1;
						if (players_arr[player].get_treasures >= MyMenu.cardnum/MyMenu.gamernum) {
							System.out.println( "\n\nMAM PRVNI A KONCIM :) \n\n");
							konec = true;
						}
						
					}
				}
			}
			
			break;
			//doprava
		case 1:
			if (sloupec+1 != bsize){
				puvodni = MazeBoard.board[radek][sloupec].getCard();
				nova = MazeBoard.board[radek][sloupec+1].getCard();
			   /* System.out.println("\nPRVNI IF");
			    
			    
			    System.out.println("\nSMERY PUVODNI:");
			    System.out.println(board[radek][sloupec].getCard().outway[0]+"\n"+board[radek][sloupec].getCard().outway[1]+"\n"+board[radek][sloupec].getCard().outway[2]);
	
			    
			    System.out.println("\nSMERY NOVA:");
			    System.out.println(nova.outway[0]+"\n"+nova.outway[1]+"\n"+nova.outway[2]);
*/
				if (((puvodni.outway[0] == CANGO.RIGHT) || (puvodni.outway[1] == CANGO.RIGHT) || (puvodni.outway[2] == CANGO.RIGHT)) && 
						((nova.outway[0] == CANGO.LEFT) || (nova.outway[1] == CANGO.LEFT) || (nova.outway[2] == CANGO.LEFT))){
				    //System.out.println("\nPRVNI IF");

					players_arr[player].sloupec = sloupec + 1;
					puvodni.player_on[player] = -1;	//upravim hrace na desce
					nova.player_on[player] = player;
				
					board[radek][sloupec].putCard(puvodni);//nahraju zpet upravene karticky
					board[radek][sloupec+1].putCard(nova);
					
					/*System.out.println("\nHrac cislo:");
				    System.out.println(player);
				    System.out.println("\nhleda");
				    System.out.println( players_arr[player].looking_for);
				    System.out.println("\nUz ziskal:");
				    System.out.println( players_arr[player].already_get[0]);*/
				    
					if (players_arr[player].looking_for == board[radek][sloupec+1].getCard().poklad){
						//players_arr[player].already_get[players_arr[player].already_get.length-1] = players_arr[player].looking_for;
			    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
						players_arr[player].looking_for = poklady_na_plose[cislo_ukolu++];
						players_arr[player].get_treasures += 1;
						if (players_arr[player].get_treasures >= MyMenu.cardnum/MyMenu.gamernum) {
							//System.out.println( "\n\nMAM PRVNI A KONCIM :) \n\n");
							konec = true;
						}						
					}
				}
			}
			break;
		case 2:
			if (radek+1 != bsize){
			   // System.out.println("\nPRVNI IF");

				puvodni = MazeBoard.board[radek][sloupec].getCard();
				nova = MazeBoard.board[radek+1][sloupec].getCard();
				
			    /*System.out.println("\nSMERY PUVODNI:");
			    System.out.println(board[radek][sloupec].getCard().outway[0]+"\n"+board[radek][sloupec].getCard().outway[1]+"\n"+board[radek][sloupec].getCard().outway[2]);
	
			    
			    System.out.println("\nSMERY NOVA:");
			    System.out.println(nova.outway[0]+"\n"+nova.outway[1]+"\n"+nova.outway[2]);
				*/
				
				if (((puvodni.outway[0] == CANGO.DOWN) || (puvodni.outway[1] == CANGO.DOWN) || (puvodni.outway[2] == CANGO.DOWN)) && 
						((nova.outway[0] == CANGO.UP) || (nova.outway[1] == CANGO.UP) || (nova.outway[2] == CANGO.UP))){
				    //System.out.println("\nPRVNI IF");

					players_arr[player].radek = radek +1;
					puvodni.player_on[player] = -1;	//upravim hrace na desce
					nova.player_on[player] = player;
				
					board[radek][sloupec].putCard(puvodni);//nahraju zpet upravene karticky
					board[radek+1][sloupec].putCard(nova);
					
					/*System.out.println("\nHrac cislo:");
				    System.out.println(player);
				    System.out.println("\nhleda");
				    System.out.println( players_arr[player].looking_for);
				    System.out.println("\nUz ziskal:");
				    System.out.println( players_arr[player].already_get[0]);*/
					if (players_arr[player].looking_for == board[radek+1][sloupec].getCard().poklad){
						//players_arr[player].already_get[players_arr[player].already_get.length-1] = players_arr[player].looking_for;
			    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
						players_arr[player].looking_for = poklady_na_plose[cislo_ukolu++];
						players_arr[player].get_treasures += 1;
						if (players_arr[player].get_treasures >= MyMenu.cardnum/MyMenu.gamernum) {
							//System.out.println( "\n\nMAM PRVNI A KONCIM :) \n\n");
							konec = true;
						}					}
				}
			}
			break;
		case 3:
			if (sloupec != 0){
				puvodni = MazeBoard.board[radek][sloupec].getCard();
				nova = MazeBoard.board[radek][sloupec-1].getCard();
			   /* System.out.println("\nPRVNI IF");
			    
			    
			    
			    System.out.println("\nSMERY PUVODNI:");
			    System.out.println(board[radek][sloupec].getCard().outway[0]+"\n"+board[radek][sloupec].getCard().outway[1]+"\n"+board[radek][sloupec].getCard().outway[2]);
	
			    
			    System.out.println("\nSMERY NOVA:");
			    System.out.println(nova.outway[0]+"\n"+nova.outway[1]+"\n"+nova.outway[2]);
*/
				if (((puvodni.outway[0] == CANGO.LEFT) || (puvodni.outway[1] == CANGO.LEFT) || (puvodni.outway[2] == CANGO.LEFT)) && 
						((nova.outway[0] == CANGO.RIGHT) || (nova.outway[1] == CANGO.RIGHT) || (nova.outway[2] == CANGO.RIGHT))){
				    //System.out.println("\nPRVNI IF");

					players_arr[player].sloupec = sloupec -1;
					puvodni.player_on[player] = -1;	//upravim hrace na desce
					nova.player_on[player] = player;
				
					board[radek][sloupec].putCard(puvodni);//nahraju zpet upravene karticky
					board[radek][sloupec-1].putCard(nova);
					/*System.out.println("\nHrac cislo:");
				    System.out.println(player);
				    System.out.println("\nhleda");
				    System.out.println( players_arr[player].looking_for);
				    System.out.println("\nUz ziskal:");
				    System.out.println( players_arr[player].already_get[0]);*/
					
					if (players_arr[player].looking_for == board[radek][sloupec-1].getCard().poklad){
						//players_arr[player].already_get[players_arr[player].already_get.length-1] = players_arr[player].looking_for;
			    		while (poklady_na_plose[cislo_ukolu] == -1){cislo_ukolu++;}
						players_arr[player].looking_for = poklady_na_plose[cislo_ukolu++];
						
						players_arr[player].get_treasures += 1;
						if (players_arr[player].get_treasures >= MyMenu.cardnum/MyMenu.gamernum) {
							//System.out.println( "\n\nMAM PRVNI A KONCIM :) \n\n");
							konec = true;
						}
						
					}
				}
			}
			break;
		}
		
		return konec;
	}
	public MazeField[][] getboard(){
		return board;
	}
	public void setboard(MazeField[][] brd){
		board = brd;
	}
	
    public void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(board);
        stream.writeInt(id);
        stream.writeObject(freesutr);
        stream.writeObject(players_arr);
        stream.writeObject(poklady_na_plose);
    }

    public void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        board = (MazeField[][]) stream.readObject();
        id = stream.readInt();
        freesutr = (MazeCard) stream.readObject();
        players_arr = (BoardPlayersTreasures[]) stream.readObject();
        poklady_na_plose = (int[]) stream.readObject();
    }
    
    
    public static void turnRight(){
		
		if (freesutr.outway[0] == CANGO.RIGHT){
			freesutr.outway[0] = CANGO.DOWN;
		}
		else if (freesutr.outway[0] == CANGO.DOWN){
			freesutr.outway[0] = CANGO.LEFT;
		}
		else if (freesutr.outway[0] == CANGO.LEFT){
			freesutr.outway[0] = CANGO.UP;
		}
		else if (freesutr.outway[0] == CANGO.UP){
			freesutr.outway[0] = CANGO.RIGHT;
		}
		if (freesutr.outway[1] == CANGO.RIGHT){
			freesutr.outway[1] = CANGO.DOWN;
		}
		else if (freesutr.outway[1] == CANGO.DOWN){
			freesutr.outway[1] = CANGO.LEFT;
		}
		else if (freesutr.outway[1] == CANGO.LEFT){
			freesutr.outway[1] = CANGO.UP;
		}
		else if (freesutr.outway[1] == CANGO.UP){
			freesutr.outway[1] = CANGO.RIGHT;
		}
		if (freesutr.outway[2] != null){ // cesta ve tvaru T, 2 vychody
			if (freesutr.outway[2] == CANGO.UP){
				freesutr.outway[2] = CANGO.RIGHT;
			}
			else if (freesutr.outway[2] == CANGO.RIGHT){
				freesutr.outway[2] = CANGO.DOWN;
			}
			else if	(freesutr.outway[2] == CANGO.DOWN){
				freesutr.outway[2] = CANGO.LEFT;
			}
			else if (freesutr.outway[2] == CANGO.LEFT){
				freesutr.outway[2] = CANGO.UP;
			} 
		}
}
}
