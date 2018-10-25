package Connect;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.color.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;


public class Connect_GUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jpMain;
	ConnectBoard jpBoard;//a JPanel containing the game board made up of JButtons
	//a JPanel displaying the score which is made up of JLabels
	
	private Player currPlayer;
	private Player player1;
	private Player player2;
	
	public Connect_GUI(){
		player1 = new Player("Sienna", "S");
		player2 = new Player("Kerillian", "K");
		currPlayer = player1;
		
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());//N,S,E,W,C
		
		
		//initialize score board
		jpBoard = new ConnectBoard();//initialize gameboard
		//add score board to jpMain BorderLayout.NORTH //jpMain.add(BorderLayout.NORTH, jpScoreBoard);
		jpMain.add(BorderLayout.CENTER, jpBoard);//add game board to jpMain BorderLayout.CENTER
		
		add(jpMain);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private class ScoreBoard extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	private class ConnectBoard extends JPanel implements GameBoardInterface, GamePlayerInterface, ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JButton[] AboveBoard;
		private JLabel [][] board;
		private int[] gravity = {5, 5, 5, 5, 5, 5, 5};
		private final int ROWS = 6;
		private final int COLS = 7;
		
		public ConnectBoard(){
			setLayout(new GridLayout(ROWS + 1,COLS));
			board = new JLabel[ROWS][COLS];
			AboveBoard = new JButton[COLS];
			displayBoard();
		}
		
		public void actionPerformed(ActionEvent e) {
			JButton btnClicked = (JButton) e.getSource();//find out which button was clicked
			int indexOfBtn = 0;
			
			for(int i = 0; i < AboveBoard.length; i++) {
				if(AboveBoard[i] == btnClicked) {
					indexOfBtn = i;
				}
			}
			
			board[gravity[indexOfBtn]][indexOfBtn].setText(currPlayer.getSymbol());
			
			gravity[indexOfBtn]--;
			
			if(isWinner()){//check if currPlayer isWinner
				//display the currPlayer as winner
				//ask to play again yes/no
				//clearBoard();//clear board
				JOptionPane.showMessageDialog(null, "WINNER= "+currPlayer.getName());
				clearBoard();
			}
			else if(isFull()){//check if full
				//game over... show draw
				//ask to play again yes/no
				//clear board
				JOptionPane.showMessageDialog(null,"IS FULL... DRAW");
				clearBoard();
			}
			takeTurn();//swap players and update the display
		}


		@Override
		public void displayBoard() {
			
			Border patrol = BorderFactory.createLineBorder(Color.YELLOW);
			
			for(int col = 0; col < AboveBoard.length; col++) {
				AboveBoard[col]= new JButton ();
				AboveBoard[col].setBackground(Color.YELLOW);
				AboveBoard[col].addActionListener(this);
				AboveBoard[col].setEnabled(true);
				add(AboveBoard[col]);
			}
			
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col] = new JLabel();
					board[row][col].setOpaque(true);
					board[row][col].setBackground(Color.MAGENTA);
					Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 40);
					board[row][col].setFont(bigFont);
					board[row][col].setBorder(patrol);
					add(board[row][col]);	
				}
			}
		}

		@Override
		public void clearBoard() {
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col].setText("");//clear button text
					board[row][col].setEnabled(true);//re-enable
				}
			}
			for(int i = 0; i < gravity.length; i++){
				gravity[i] = 5;
			}
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFull() {
			for(int row=0; row < board.length; row++){
				for(int col=0; col< board[row].length; col++){
					String cellContent = board[row][col].getText().trim();
					if(cellContent.isEmpty()){
						return false;//board has an empty slot... not full
					}
				}
			}
			
			return true;
		}

		@Override
		public boolean isWinner() {
			//check rows
			//check cols
			//check main diagonal
			//check seocndary diagonal
			if(isWinnerInRow() || isWinnerInCol()) {//== true) {// || isWinnerInMainDiag() /* || isWinnerInSecDiag() */){
				return true;
			}
			/**if(isWinnerInCol()){
				return true;
			}**/
			return false;
		}

	
		public boolean isWinnerInCol(){
			String symbol = currPlayer.getSymbol();
			for(int col=0; col < board.length; col++){
				int numMatchesInCol = 0; //reset on the next row
				for(int row=0; row < board[col].length; row++){
					if( board[col][row].getText().trim().equalsIgnoreCase(symbol)){
						numMatchesInCol++;
					}else {
						numMatchesInCol = 0;
					}if(numMatchesInCol >= 4){
						return true;
					}
					
				}
			}
			
			return false;
		}
		
		public boolean isWinnerInRow(){
			String symbol = currPlayer.getSymbol();
			for(int row=0; row < board.length; row++){
				int numMatchesInRow = 0; //reset on the next row
				for(int col=0; col< board[row].length; col++){
					if( board[row][col].getText().trim().equalsIgnoreCase(symbol)){
						numMatchesInRow++;
					}else {
						numMatchesInRow = 0;
					}if(numMatchesInRow >= 4){
						return true;
					}
					
				}
			}
			return false;
		}	
		

		
		/**public boolean isWinnerInMainDiag(){
			String symbol = currPlayer.getSymbol();
			for(int i =0; i < board.length; i++){
				int madiag = 0; //reset on the next row
				for(int row=0; row < board[i].length; row++){
					if( board[i][i].getText().trim().equalsIgnoreCase(symbol)){
						madiag++;
						}else {
							madiag = 0;
						}	
				
					if(madiag == 4){
						return true;
					}
				}
			}
			return false;
		}**/
		
		@Override
		public void takeTurn() {
			if(currPlayer.equals(player1)){
				currPlayer = player2;
			}
			else{
				currPlayer = player1;
			}
		}

		
		
		
		
	}
	
	
	
	
}//end
