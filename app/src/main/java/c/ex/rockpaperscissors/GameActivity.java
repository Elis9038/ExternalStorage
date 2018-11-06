package c.ex.rockpaperscissors;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Arrays.asList;

public class GameActivity extends AppCompatActivity {

    private static int userScore = 0;
    String yourChoiceString, enemyChoiceString, resultText;
    ImageButton rockButton, paperButton, scissorsButton;
    ImageView yourChoice, enemyChoice;
    TextView resultTextView, scoreTextView;
    LinearLayout gameMenu, resultLayout, choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);
        rockButton = findViewById(R.id.rockButton);
        yourChoice = findViewById(R.id.yourChoice);
        enemyChoice = findViewById(R.id.enemyChoice);
        resultTextView = findViewById(R.id.resultTextView);
        gameMenu = findViewById(R.id.gameMenu);
        resultLayout = findViewById(R.id.resultLayout);
        scoreTextView = findViewById(R.id.scoreTextView);
        choices = findViewById(R.id.choices);

        userScore = 0;

        Toast.makeText(this, "Score will be saved if you open scores table", Toast.LENGTH_SHORT).show();
    }

    public void makeChoice(View v) {

        switch (v.getId()) {

            case R.id.rockButton:
                yourChoice.setImageDrawable(getDrawable(R.drawable.rock));
                yourChoiceString = "rock";
                break;
            case R.id.paperButton:
                yourChoice.setImageDrawable(getDrawable(R.drawable.paper));
                yourChoiceString = "paper";
                break;
            case R.id.scissorsButton:
                yourChoice.setImageDrawable(getDrawable(R.drawable.scissors));
                yourChoiceString = "scissors";
                break;
        }

        enemyChoice();

        if (yourChoiceString == "rock" && enemyChoiceString == "paper" ||
                yourChoiceString == "paper" && enemyChoiceString == "scissors" ||
                yourChoiceString == "scissors" && enemyChoiceString == "rock") {

            resultText = "You lost";
            userScore -= 25;

        } else if (yourChoiceString == "rock" && enemyChoiceString == "scissors" ||
                yourChoiceString == "scissors" && enemyChoiceString == "paper" ||
                yourChoiceString == "paper" && enemyChoiceString == "rock"){

            resultText = "You won!";
            userScore += 25;

        } else {
            resultText = "It's a draw!";
        }

        String scoreStr = "Your score is " + userScore + "!";

        // Showing user's score after every move
        resultLayout.setVisibility(View.VISIBLE);
        resultTextView.setText(resultText);
        scoreTextView.setText(scoreStr);


        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorsButton.setEnabled(false);

        waitForOneSceond();
    }

    public void enemyChoice() {

        Random rand = new Random();
        int choice = rand.nextInt(4);

        if (choice == 0) enemyChoiceString = "rock";

        else if (choice == 1) enemyChoiceString = "paper";

        else if (choice == 2) enemyChoiceString = "scissors";

        /* If I left only these 3 choices above, then user would have about 30% chance of winning.
        By adding this 4th option for enemy it's 50% to win for user */
        else if (choice == 3) {

            if (yourChoiceString == "rock") enemyChoiceString = "scissors";
            else if (yourChoiceString == "paper") enemyChoiceString = "rock";
            else if (yourChoiceString == "scissors") enemyChoiceString = "paper";
        }

        switch (enemyChoiceString){

            case "rock": enemyChoice.setImageDrawable(getDrawable(R.drawable.rock));
                break;
            case "paper": enemyChoice.setImageDrawable(getDrawable(R.drawable.paper));
                break;
            case "scissors": enemyChoice.setImageDrawable(getDrawable(R.drawable.scissors));
                break;
        }
    }

    public void showMenu(View v) {
        gameMenu.setVisibility(View.VISIBLE);
    }

    public void continueGame(View v) {
        gameMenu.setVisibility(View.INVISIBLE);
        enemyChoice.setImageDrawable(getDrawable(R.drawable.question));
        yourChoice.setImageDrawable(getDrawable(R.drawable.question));
    }

    public void changePlayer(View v) {
        startActivity(new Intent(GameActivity.this, MainActivity.class));
    }

    public void viewScores(View v) {
        startActivity(new Intent(GameActivity.this, ScoresActivity.class));
    }


    public void waitForOneSceond() {

        CountDownTimer count = new CountDownTimer (1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                resultLayout.setVisibility(View.INVISIBLE);
                enemyChoice.setImageDrawable(getDrawable(R.drawable.question));
                yourChoice.setImageDrawable(getDrawable(R.drawable.question));

                rockButton.setEnabled(true);
                paperButton.setEnabled(true);
                scissorsButton.setEnabled(true);
            }
        }.start();

    }

    public static int getUserScore() { return userScore; }

}