import javafx.util.Pair;

public class AIBot {

    private short board_6_x_6[][];
    private short symbol;
    private short oppSymbol;

    AIBot(short symbol, short oppSymbol){
        board_6_x_6 = new short[3][3];
        this.symbol = symbol;
        this.oppSymbol = oppSymbol;
    }

    public void setBoard(short board[][]){
        board_6_x_6 = board;
    }

    public Pair<Short, Short> getNextMove(){
        short result[] = calculateNextMove(this.board_6_x_6, false, this.symbol);
        return new Pair<Short, Short>(result[1], result[2]);
    }

    private short getOpponentSymbol(short symbol){
        if(symbol == this.symbol)
            return oppSymbol;
        return this.symbol;
    }

    private short[] calculateNextMove(short board_6_x_6[][], boolean min, short symbol){
        short board[][] = board_6_x_6.clone();
        short result[] = new short[3];
        if(min)
            result[0] = Short.MAX_VALUE;
        else
            result[0] = Short.MIN_VALUE;

        boolean leafNode = true;
        for(short row = 0; row < 3; row++){
            for(short col = 0; col < 3; col++){
                if(board[row][col] == -1) {
                    board[row][col] = symbol;
                    short r[] = calculateNextMove(board, !min, getOpponentSymbol(symbol));
                    if(min){
                        if(r[0] < result[0]) {
                            result[0] = r[0];
                            result[1] = row;
                            result[2] = col;
                        }
                    }else{
                        if(r[0] >  result[0]) {
                            result[0] = r[0];
                            result[1] = row;
                            result[2] = col;
                        }
                    }
                    board[row][col] = -1;
                    leafNode = false;
                }
            }
        }

        if(leafNode){
            // check if ai won
            if(checkIfPlayerWon(this.symbol)) {
                result[0] = 1;
                return result;
            }
            // check if opponent won
            if(checkIfPlayerWon(oppSymbol)) {
                result[0] = -1;
                return result;
            }
            result[0] = 0;
            // draw
            return result;
        }

        return result;
    }

    public boolean checkIfPlayerWon(short playerSymbol){
        // check for row
        for(int row = 0; row < 3; row++){
            boolean sameSymbolInaRow = true;
            for(int col = 0; col < 3; col++){
                if(board_6_x_6[row][col] != playerSymbol)
                    sameSymbolInaRow = false;
            }
            if(sameSymbolInaRow)
                return true;
        }

        // check for col
        for(int col = 0; col < 3; col++){
            boolean sameSymbolInaRow = true;
            for(int row = 0; row < 3; row++){
                if(board_6_x_6[row][col] != playerSymbol)
                    sameSymbolInaRow = false;
            }
            if(sameSymbolInaRow)
                return true;
        }

        // check for diagonal
        boolean sameSymbolInDiagonal = true, sameSymbolInAntiDiagonal = true;
        for(int row = 0, col = 2; row < 3; row++, col--){
            if(board_6_x_6[row][row] != playerSymbol)
                sameSymbolInDiagonal = false;
            if(board_6_x_6[row][col] != playerSymbol)
                sameSymbolInAntiDiagonal = false;
        }

        if(sameSymbolInDiagonal || sameSymbolInAntiDiagonal)
            return true;

        return false;
    }

    // ------------
    //|   | O |   |
    //| X | X | O |
    //| O |   |   |
    // ------------

    public static void main(String args[]){
        short board[][] = new short[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                board[i][j] = -1;

        board[0][1] = 0;
        board[1][0] = 1;
        board[1][1] = 1;
        board[1][2] = 0;
        board[2][0] = 0;

        short symbol = 1, opp = 0;
        AIBot bot = new AIBot(symbol, opp);
        bot.setBoard(board);

        System.out.println(bot.getNextMove().getKey() + " " + bot.getNextMove().getValue() );
    }

}
