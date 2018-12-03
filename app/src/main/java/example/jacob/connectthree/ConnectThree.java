package example.jacob.connectthree;

public class ConnectThree {
    private int turnCount;
    public int currentPlayer;
    private int[][] board;

    public ConnectThree() {
        this.turnCount = 0;
        this.currentPlayer = 1;
        this.board = new int[3][3];
    }

    public int placePiece(int r, int c) {
        // Place a piece into the board, then check for a winner
        this.turnCount++;
        if (this.currentPlayer == 1) {
            this.board[r][c] = 1;
            this.currentPlayer = 2;
        } else {
            this.board[r][c] = 2;
            this.currentPlayer = 1;
        }
        return this.checkWinner();
    }

    private int checkWinner() {
        if (turnCount >= 2) {
            int colWin = this.checkColumn();
            if (colWin > 0) {
                return colWin;
            }

            int rowWin = this.checkRow();
            if (rowWin > 0) {
                return rowWin;
            }

            int diagWin = checkDiagonal();
            if (diagWin > 0) {
                return diagWin;
            }
        }
        return 0;
    }

    private int checkColumn() {
        for (int i = 0; i < 3; i++) {
            if ((this.board[i][0] == this.board[i][1]) && (this.board[i][0] == this.board[i][2])) {
                return this.board[i][0];
            }
        }
        return 0;
    }

    private int checkRow() {
        for (int i = 0; i < 3; i++) {
            if ((this.board[0][i] == this.board[1][i]) && (this.board[0][i] == this.board[2][i])) {
                return this.board[0][i];
            }
        }
        return 0;
    }

    private int checkDiagonal() {
        if ((this.board[0][0] == this.board[1][1]) && this.board[0][0] == this.board[2][2]) {
            return this.board[0][0];
        } else if ((this.board[0][2] == this.board[1][1]) && this.board[0][2] == this.board[2][0]) {
            return this.board[0][2];
        } else {
            return 0;
        }
    }
}
