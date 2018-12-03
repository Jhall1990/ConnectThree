package example.jacob.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ConnectThree game;
    int winner;

    public void addPiece(View view) {
        /*
        First checks if a winner has been found, if there is a winner do nothing.
        Otherwise attempt to add a piece to the board. If the move is valid add
        update the image and display the animation. Then check if the newly placed
        piece wins the game.
         */
        Log.i("ConnectThree", "Clicked: " + view.getTag().toString());

        if (this.winner <= 0) {
            String[] coords = view.getTag().toString().split(",");
            int row = Integer.parseInt(coords[0]);
            int col = Integer.parseInt(coords[1]);

            this.winner = this.game.placePiece(row, col);

            // The move was valid, update the image and display animation.
            if (this.winner != -1) {
                this.updateImage(view.getId());
                this.checkWinner();
            }
        }
    }

    public void resetGame(View view) {
        /*
        Reset the game so that it can be played again. Creates a new game board, sets
        the winner int to 0. Then removes the replay button and text. Finally
        reset all the piece images.
         */
        this.game = new ConnectThree();
        this.winner = 0;
        TextView winnerText = findViewById(R.id.winnerMessage);
        Button winnerButton = findViewById(R.id.resetGameButton);

        winnerText.setText("");
        winnerButton.setVisibility(View.INVISIBLE);
        resetBoardImages();
    }

    private void resetBoardImages() {
        /*
        Sets each space's image to null (no image).
         */
        List<ImageView> boardSpaces = new ArrayList<>();
        boardSpaces.add((ImageView)findViewById(R.id.space_0_0));
        boardSpaces.add((ImageView)findViewById(R.id.space_0_1));
        boardSpaces.add((ImageView)findViewById(R.id.space_0_2));
        boardSpaces.add((ImageView)findViewById(R.id.space_1_0));
        boardSpaces.add((ImageView)findViewById(R.id.space_1_1));
        boardSpaces.add((ImageView)findViewById(R.id.space_1_2));
        boardSpaces.add((ImageView)findViewById(R.id.space_2_0));
        boardSpaces.add((ImageView)findViewById(R.id.space_2_1));
        boardSpaces.add((ImageView)findViewById(R.id.space_2_2));

        for (ImageView img : boardSpaces) {
            img.setImageDrawable(null);
        }
    }
    private void updateImage(int id) {
        /*
        Gets the current player and sets the piece image accordingly.
         */
        ImageView img = findViewById(id);
        int imgSrc;

        if (this.game.currentPlayer == 1) {
            imgSrc = R.drawable.red;
        } else {
            imgSrc = R.drawable.yellow;
        }

        img.setTranslationY(-3000);
        img.setImageResource(imgSrc);
        img.animate().translationYBy(3000).setDuration(1000);

        if (this.game.currentPlayer == 1) {
            this.game.currentPlayer = 2;
        } else {
            this.game.currentPlayer = 1;
        }
    }

    private void checkWinner() {
        /*
        This function checks to see if a winner has been determined or if there
        are no more valid moves left. If a winner has been found or no more moves exists
        it updates the text view letting the user know the game is over and displays
        a button so the user can start a new game.
         */
        if (this.winner > 0 || this.game.turnCount == 9) {
            TextView winnerText = findViewById(R.id.winnerMessage);
            Button winnerButton = findViewById(R.id.resetGameButton);

            if (this.game.turnCount == 9) {
                winnerText.setText("Tie game!");
            } else if (this.winner == 1) {
                winnerText.setText("Red has won!");
            } else {
                winnerText.setText("Yellow has won!");
            }

            winnerButton.setVisibility(View.VISIBLE);
        }
        else {
            Log.i("ConnectThree", "No winner yet.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new ConnectThree();
        winner = 0;
    }
}
