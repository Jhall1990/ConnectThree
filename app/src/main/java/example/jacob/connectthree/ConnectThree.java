package example.jacob.connectthree;

class ConnectThree {
    int turnCount;
    int currentPlayer;
    private int[][] board;

    ConnectThree() {
        /*
        Creates a new instance of the ConnectThree game.
         */
        this.turnCount = 0;
        this.currentPlayer = 1;
        this.board = new int[3][3];
    }

    int placePiece(int r, int c) {
        /*
        This function attempts to place a piece onto the game board.

        r - The row the piece should be placed in.
        c - The column the piece should be placed in.

        Returns: -1 if the move is invalid.
                  0 if there is no winner.
                  1 if player 1 wins.
                  2 if player 2 wins.
         */
        // Make sure that no other piece in the space.
        if (this.board[r][c] != 0) {
            return -1;
        }

        // The move is valid, increase the turn count and
        // add the piece to the board.
        this.turnCount++;
        this.board[r][c] = this.currentPlayer;

        // Check for a winner.
        return this.checkWinner();
    }

    private int checkWinner() {
        /*
        This function checks if either player has won the game. It checks vertically,
        horizontally, and diagonally.

        Returns: 0 if there is no winner.
                 1 if player 1 wins.
                 2 if player 2 wins.
         */
        if (this.turnCount >= 2) {
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
        /*
        This functions checks each column for a winner.

        Returns: 0 if there is no winner.
                 1 if player 1 wins.
                 2 if player 2 wins.
         */
        for (int i = 0; i < 3; i++) {
            if ((this.board[i][0] == this.board[i][1]) && (this.board[i][0] == this.board[i][2])) {
                return this.board[i][0];
            }
        }
        return 0;
    }

    private int checkRow() {
        /*
        This function checks each row for a winner.

        Returns: 0 if there is no winner.
                 1 if player 1 wins.
                 2 if player 2 wins.
         */
        for (int i = 0; i < 3; i++) {
            if ((this.board[0][i] == this.board[1][i]) && (this.board[0][i] == this.board[2][i])) {
                return this.board[0][i];
            }
        }
        return 0;
    }

    private int checkDiagonal() {
        /*
        This function checks each diagonal for a winner.

        Returns: 0 if there is no winner.
                 1 if player 1 wins.
                 2 if player 2 wins.
         */
        if ((this.board[0][0] == this.board[1][1]) && this.board[0][0] == this.board[2][2]) {
            return this.board[0][0];
        } else if ((this.board[0][2] == this.board[1][1]) && this.board[0][2] == this.board[2][0]) {
            return this.board[0][2];
        } else {
            return 0;
        }
    }
}
