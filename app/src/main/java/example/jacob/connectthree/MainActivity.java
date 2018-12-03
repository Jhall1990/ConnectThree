package example.jacob.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    ConnectThree game;
    int winner;

    public void addPiece(View view) {
        Log.i("ConnectThree", "Clicked: " + view.getTag().toString());

        if (winner == 0) {
            ImageView img = findViewById(view.getId());
            String[] coords = view.getTag().toString().split(",");
            int row = Integer.parseInt(coords[0]);
            int col = Integer.parseInt(coords[1]);
            int imgSrc;

            if (game.currentPlayer == 1) {
                imgSrc = R.drawable.red;

            } else {
                imgSrc = R.drawable.yellow;
            }

            img.setTranslationY(-3000);
            img.setImageResource(imgSrc);
            img.animate().translationYBy(3000).setDuration(1000);

            winner = game.placePiece(row, col);

            if (winner > 0) {
                Log.i("ConnectThree", "Player " + Integer.toString(winner) + " won!");
            } else {
                Log.i("ConnectThree", "No winner yet.");
            }
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
