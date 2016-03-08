package ija.board;

@SuppressWarnings("serial")
public class MazeField implements java.io.Serializable{
	/**
	 * 
	 */

	private int Row = 0;
	private int Col = 0;
	private  MazeCard mc = null;
	private  boolean mccreate = false;
	public MazeField(int Row,int Col){
		this.Row = Row;
		this.Col = Col;
	}
	public int row(){
		return Row;
	}
	public int col(){
		return Col;
	}
	public MazeCard getCard(){

		if (mccreate == true){
			return mc;
		}
		else{
			return null;
		}
	}
	public void putCard(MazeCard c){
		//System.out.println("mc pred putCard: 	"+mc);
		//System.out.println("c: "+c);
		mc = c; 
		//System.out.println("m: "+mc);
		mccreate = true;
	}
	
	public void print(){
		if (this.getCard() == null){
			//System.out.print('_');
		}
		else{
			//this.getCard().print();
		}
	}
	
}
