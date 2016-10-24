
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	@ Fasial Bashar
	@ x13358851@student.ncirl.ie
	@ National College of Ireland
*/

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	//Declare The instances for the Player Turns
	Boolean whiteMove;
	Boolean possible;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
	    possible = false;
	    whiteMove = true;

    }

    // NearKing Method(Spare Space Near the Kings)
    private Boolean nearKing(int newX, int newY) {
			if((piecePresent(newX, newY+75)&& pName(newX, newY+75).contains("King")||(piecePresent(newX, newY-75)&& pName(newX, newY-75).contains("King")))){
				return  true;
			}
			else if((piecePresent(newX+75,newY)&& pName(newX+75, newY).contains("King")||(piecePresent(newX-75, newY)&& pName(newX-75, newY).contains("King")))){
				return true;
			}
			else if((piecePresent(newX-75, newY+75)&& pName(newX-75, newY+75).contains("King")||(piecePresent(newX+75, newY-75)&& pName(newX+75, newY-75).contains("King")))){
				return  true;
			}
			else if((piecePresent(newX-75, newY-75)&& pName(newX-75, newY-75).contains("King")||(piecePresent(newX+75, newY+75)&& pName(newX+75, newY+75).contains("King")))){
				return  true;
			}
			return false;
		}//End of NearKing Method(Spare Space Near the Kings)

		private String pName(int newX, int newY){
			Component c = chessBoard.findComponentAt(newX, newY);
			if(c instanceof JLabel){
				JLabel awaitingPiece = (JLabel) c;
				String tmp1 = awaitingPiece.getIcon().toString();
				return tmp1;
			}
			else{
				return "";
			}
	}

	/*
		This method checks if there is a piece present on a particular square.
	*/

	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			opponent = true;
			if(((tmp1.contains("King")))){
				JOptionPane.showMessageDialog(null,"CheckMate..White Win");	// GameOver The WhitePiece Player Wins
				System.exit(0);
			}
		}
		else{
			opponent = false;
		}
		return opponent;
	}

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/

		private Boolean checkBlackOpponent(int newX, int newY){
			Boolean opponent;
			Component c1 = chessBoard.findComponentAt(newX, newY);
			JLabel awaitingPiece = (JLabel)c1;
			String tmp1 = awaitingPiece.getIcon().toString();
			if(((tmp1.contains("White")))){
				opponent = true;
				if(((tmp1.contains("King")))){	// Win Move
						JOptionPane.showMessageDialog(null,"CheckMate..Black Win");	// GameOver The BlackPiece Player Wins
						System.exit(0);
					}
				}
				else{
					opponent = false;
				}
				return opponent;
			}



    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.2
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
        Boolean progression = false;
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;

		int landingX = (e.getX()/75);
		int landingY  = (e.getY()/75);
		int xMovement = Math.abs((e.getX()/75)-startX);
		int yMovement = Math.abs((e.getY()/75)-startY);
		System.out.println("----------------------------------------------");
		System.out.println("The piece that is being moved is : "+pieceName);
		System.out.println("The starting coordinates are : "+"( "+startX+","+startY+")");
		System.out.println("The xMovement is : "+xMovement);
		System.out.println("The yMovement is : "+yMovement);
		System.out.println("The landing coordinates are : "+"( "+landingX+","+landingY+")");
		System.out.println("----------------------------------------------");


	// This Allows the WhitePieces to Move First then the BlackPiece Can Move
	if(whiteMove){
		if(pieceName.contains("White")){
			possible = true;
		}
	}
	else{
		if(pieceName.contains("Black")){
			possible = true;
		}
	}


	if(possible){ 	// The "Possible" Wrapper for the turn system(Each player can play only once)
	if (pieceName.contains("King")) {	// Start of King Code
		if ((xMovement == 1 && yMovement == 0) || (xMovement == 0 && yMovement == 1) || (xMovement == 1 && yMovement == 1)) {
			if(!(nearKing(e.getX(), e.getY()))){	//Near King Method is implemented at the Top

			if (piecePresent(e.getX(), e.getY())) {
				if (pieceName.contains("Black")) {
					if (checkBlackOpponent(e.getX(), e.getY())) {
						validMove = true;
					}
				}
				if (pieceName.contains("White")) {
					if (checkWhiteOpponent(e.getX(), e.getY())) {
						validMove = true;
					}
				}
			}
				else if (!piecePresent(e.getX(), e.getY())) {
					validMove = true;
					}
				}
			}
		}// End of King Code

		//Start of Queen Code
   else if (pieceName.contains("Queen")) {
		int newY = e.getY() / 75;
		int newX = e.getX() / 75;
		//Movement of Bishop Start Here (So that the Queen can move Diagonal)
		boolean inTheWay = false;
		int distance = Math.abs(startX - newX);
		if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
			validMove = false;
		} else {
			validMove = true;
			//Check if the move is a diagonal move
			if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
				// If there are any pieces along the diagonal in the way the move cannot be made.
				if ((startX - landingX < 0) && (startY - landingY < 0)) {
					for (int i = 0; i < distance; i++) {
						if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
							inTheWay = true;
						}
					}
				} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
					for (int i = 0; i < distance; i++) {
						if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
							inTheWay = true;
						}
					}
				} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
					for (int i = 0; i < distance; i++) {
						if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
							inTheWay = true;
						}
					}
				} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
					for (int i = 0; i < distance; i++) {
						if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
							inTheWay = true;
						}
					}
				}
				if (inTheWay) {
					validMove = false;
				} else {	// Check to see if its Own Piece or Opponents Piece..
					if (piecePresent(e.getX(), (e.getY()))) {
						if (pieceName.contains("White")) {
							if (checkWhiteOpponent(e.getX(), e.getY())) {
								validMove = true;
							} else {
								validMove = false;
							}
						} else {
							if (checkBlackOpponent(e.getX(), e.getY())) {
								validMove = true;
							} else {
								validMove = false;
							}
						}
					} else {
						validMove = true;
					}
				}		//Movement of Rook starts here (so that the Queen Can move Horizontal or Vertical)
			} else if (((Math.abs(startX - landingX) !=0)&&(Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) ==0) && (Math.abs(landingY - startY) !=0))) {
				if (Math.abs(startX - landingX) != 0) {
					//we have movement along the x axis
					if (startX - landingX > 0) {
						//movement in the left direction....
						for (int i = 0; i < xMovement; i++) {
							if (piecePresent(initialX - (i * 75), e.getY())) {
								inTheWay = true;
								break;
							} else {
								inTheWay = false;
							}
						}
					} else {
						for (int i = 0; i < xMovement; i++) {
							if (piecePresent(initialX + (i * 75), e.getY())) {
								inTheWay = true;
								break;
							} else {
								inTheWay = false;
							}
						}
					}
				} else {
					//we have movement along the y axis
					if (startY - newY > 0) {
						//movement in the left direction....
						for (int i = 0; i < yMovement; i++) {
							if (piecePresent(e.getX(), initialY - (i * 75))) {
								inTheWay = true;
								break;
							} else {
								inTheWay = false;
							}
						}
					} else {
						for (int i = 0; i < yMovement; i++) {
							if (piecePresent(e.getX(), initialY + (i * 75))) {
								inTheWay = true;
								break;
							} else {
								inTheWay = false;
							}
						}
					}
				}
				if (inTheWay) {
					validMove = false;
				} else {
					if (piecePresent(e.getX(), (e.getY()))) {
						if (pieceName.contains("White")) {
							if (checkWhiteOpponent(e.getX(), e.getY())) {
								validMove = true;
							} else {
								validMove = false;
							}
						} else {
							if (checkBlackOpponent(e.getX(), e.getY())) {
								validMove = true;
							} else {
								validMove = false;
							}
						}
					} else {
						validMove = true;
					}
				}
			} else { // the move that is being tried is not a diagonal move...
				validMove = false;
			}
		}
	} //End of Queen Code

			//Start of Rook Code
	else if(pieceName.contains("Rook")){
		Boolean intheway = false;
		if(((landingX < 0)||(landingX > 7))||((landingY < 0)||(landingY > 7))){
			validMove = false;
		}
		else{
			if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) == 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0)))
			{
				if(Math.abs(startX-landingX)!=0){
					int xmovement = Math.abs(startX-landingX);
					if(startX-landingX > 0){
						for(int i=0;i < xMovement;i++){
							if(piecePresent(initialX-(i*75), e.getY())){
								intheway = true;
								break;
							}
							else{
								intheway = false;
							}
						}
					}
					else{
						for(int i=0;i < xMovement;i++){
							if(piecePresent(initialX+(i*75), e.getY())){
								intheway = true;
								break;
							}
							else{
								intheway = false;
							}
						}
					}
				}
				else{
					int ymovement = Math.abs(startY-landingY);
					if(startY-landingY > 0){
						for(int i=0;i< yMovement;i++){
							if(piecePresent(e.getX(),initialY-(i*75))){
								intheway = true;
								break;
							}
							else{
								intheway = false;
							}
						}
					}
					else{
						for(int i=0;i < yMovement;i++){
							if(piecePresent(e.getX(),initialY+(i*75))){
								intheway = true;
								break;
							}
							else{
								intheway = false;
							}
						}
					}
				}
				if(intheway){
					validMove = false;
				}
				else{		// Check to see if its Own Piece or Opponents Piece..
					if(piecePresent(e.getX(),(e.getY()))){
						if(pieceName.contains("White")){
							if(checkWhiteOpponent(e.getX(), e.getY())){
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
						else{
							if(checkBlackOpponent(e.getX(), e.getY())){
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
					}
					else{
						validMove = true;
					}
				}
			}
			else{
				validMove = false;
			}
		}
	}// End of Rook Code

		// Start of Bishop Code
	else if(pieceName.contains("Bishup")){
				Boolean inTheWay = false;
				int distance = Math.abs(startX-landingX);
				if(((landingX < 0) ||(landingX > 7))||((landingY < 0)||(landingY > 7))){
					validMove = false;
				}
				else{
					validMove = true;
					if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
						if((startX-landingX < 0)&&(startY-landingY < 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
									inTheWay = true;
								}
							}
						}
						else if((startX-landingX < 0)&&(startY-landingY > 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
									inTheWay = true;
								}
							}
						}
						else if((startX-landingX > 0)&&(startY-landingY > 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
									inTheWay = true;
								}
							}
						}
						else if((startX-landingX > 0)&&(startY-landingY < 0)){
							for(int i=0; i < distance;i++){
								if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
									inTheWay = true;
								}
							}
						}
						if(inTheWay){
							validMove = false;
						}
						else{	// Check to see if its Own Piece or Opponents Piece..
							if(piecePresent(e.getX(),(e.getY()))){
								if(pieceName.contains("White")){
									if(checkWhiteOpponent(e.getX(),e.getY())){
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
								else{
									if(checkBlackOpponent(e.getX(), e.getY())){
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
							}
							else{
								validMove = true;
							}
						}
					}
					else{
						validMove = false;
					}
				}
			}	// End of Bishop Code

			//Start of Knight Code
	else if(pieceName.contains("Knight")){
		if(((landingX < 0)||(landingX > 7))||((landingY < 0)||landingY > 7)){	//too Keep the KnightPiece On the Board
			validMove = false;
		}
		else{
			if(((landingX == startX+1)&&(landingY == startY+2))||((landingX == startX-1)&&(landingY ==
			startY+2))||((landingX == startX+2) && (landingY == startY+1))||((landingX == startX-2) &&(landingY ==
			startY+1))||((landingX == startX+1) && (landingY == startY-2))||((landingX == startX-1) &&(landingY ==
			startY-2))||((landingX == startX+2) && (landingY == startY-1))||((landingX == startX-2) &&(landingY ==
			startY-1))){
				if(piecePresent(e.getX(),(e.getY()))){
					if(pieceName.contains("White")){	// Check to see if its Own Piece or Opponents Piece..
						if(checkWhiteOpponent(e.getX(), e.getY())){
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
					else{
						if(checkBlackOpponent(e.getX(), e.getY())){
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = true;
				}
			}
			else{
				validMove = false;
			}
		}
	}	// End of Kinght Code

      //Start of BlackPawn Code
 	else if(pieceName.equals("BlackPawn")){
				/* The Pawn can move either two or one squares*/

			if((startY == 6)&&(startX == landingX)&&(((startY-landingY)== 1)||(startY-landingY)== 2)){
				/* If there is a piece in the way*/
				if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), e.getY() + 75))){
					validMove = true;
				}

				else{
					validMove = false;
				}
			}

			else if((Math.abs(startX-landingX)==1)&&(((startY-landingY)== 1))){
				if(piecePresent(e.getX(),e.getY())){
					if(checkBlackOpponent(e.getX(),e.getY())){
						validMove = true;
						if(landingY == 0){
							progression = true;
						}
					}
					else{
						validMove = false;
					}
				}
				else{
					validMove = false;
				}
			}
			else if((startY != 6)&&((startX == landingX)&&(((startY-landingY)== 1)))){
				/* if there is a piece in the way*/
				if(!piecePresent(e.getX(), e.getY())){
					validMove = true;
					if(landingY == 0){
						progression = true;
					}
				}
				else{
					validMove = false;
				}
			}
			else{
				validMove = false;
			}
		} // End of BlackPawn Code

		// Start WhitePawn Code
	else if (pieceName.equals("WhitePawn")) {
					if ((startY == 1) && (startX == landingX) && (((landingY - startY) == 1) || (landingY - startY) == 2)) {	// you Can move once or twice one first Move
						if ((!piecePresent(e.getX(), e.getY()) &&(!piecePresent(e.getX(), e.getY()-75)))) {
							validMove = true;
						}
						} else if ((Math.abs(landingX - startX) == 1) && (((landingY - startY) == 1))) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkWhiteOpponent(e.getX(), e.getY())) {	//Check if Opponents Piece is there, if its not than make the MOve
								validMove = true;
								if (landingY == 7) {
									success = true;
								}
								} else {
								validMove = false;
							}
							} else {
							validMove = false;
						}
						} else if ((startY != 1) && ((startX == landingX) && (((landingY - startY) == 1)))) {	// this time you can only move one square..not twice
						// If there is a piece in the way
						if (!piecePresent(e.getX(), e.getY())) {
							validMove = true;
							if (landingY == 7) {
								success = true;
							}
							} else {
							validMove = false;
						}
						} else {
						validMove = false;
						}
					} //End of WhitePawn Code
				}// End of if(possible)wrapper

		if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}
		else{
			whiteMove = !whiteMove; //Can't Move WhitePiece Twice
			possible = false;

			if(progression){
				int location = 0 + (e.getX()/75);
				if(c instanceof JLabel){
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel)chessBoard.getComponent(location);
					parent.add(pieces);
				}
			}
			else if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);
			}
		}
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
