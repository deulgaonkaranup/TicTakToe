public class TicTakToeBoard {

    public short board_6_x_6[][];
    private short player1_symbol;
    private short player2_symbol;

    TicTakToeBoard(){
        board_6_x_6 = new short[6][6];
        player1_symbol = -1;
        player2_symbol = -1;
    }

    public void setPlayer1_symbol(short symbol){
        player1_symbol = symbol;
    }

    public void setPlayer2_symbol(short symbol){
        player2_symbol = symbol;
    }

    public void setPlayerChoice(short playerSymbol, short row, short col){
        if(row > 1 || row < -1 || col > 1 || col < -1)
            return;
        board_6_x_6[row][col] = playerSymbol;
    }

    public boolean checkIfPlayerWon(short playerSymbol){
        // check for row
        for(int row = 0; row < 2; row++){
            boolean sameSymbolInaRow = true;
            for(int col = 0; col < 2; col++){
                if(board_6_x_6[row][col] != playerSymbol)
                    sameSymbolInaRow = false;
            }
            if(sameSymbolInaRow)
                return true;
        }

        // check for col
        for(int col = 0; col < 2; col++){
            boolean sameSymbolInaRow = true;
            for(int row = 0; row < 2; row++){
                if(board_6_x_6[row][col] != playerSymbol)
                    sameSymbolInaRow = false;
            }
            if(sameSymbolInaRow)
                return true;
        }

        // check for diagonal
        boolean sameSymbolInDiagonal = true, sameSymbolInAntiDiagonal = true;
        for(int row = 0, col = 2; row < 2; row++, col--){
            if(board_6_x_6[row][row] != playerSymbol)
                sameSymbolInDiagonal = false;
            if(board_6_x_6[row][col] != playerSymbol)
                sameSymbolInAntiDiagonal = false;
        }

        if(sameSymbolInDiagonal || sameSymbolInAntiDiagonal)
            return true;

        return false;
    }
}
