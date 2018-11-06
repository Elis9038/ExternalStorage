package c.ex.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private static String userName = "";
    TextView textView;
    Button nameButton;
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        nameButton = findViewById(R.id.nameButton);
        nameInput = findViewById(R.id.nameInput);

        textView.animate().
                scaleY(1.5f).
                scaleX(1.5f).
                setDuration(2000).start();
    }

    // The name has to contain only latin letters. After succsessful check we save the name and start next activity
    public void submitName(View v) {

        if (!nameInput.getText().toString().isEmpty() && nameInput.getText().toString().matches("^[a-zA-Z0-9.]+$")) {

            userName = nameInput.getText().toString();
            startActivity(new Intent(MainActivity.this, GameActivity.class));

        } else if (!nameInput.getText().toString().matches("^[a-zA-Z0-9.]+$")) {
            Toast.makeText(this, "Only latin letters!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Enter your name first!", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getUserName(){
        return userName;
    }

}
