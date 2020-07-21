package com.example.guessmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.Random;

public class GuessMaster extends AppCompatActivity {
    //step iii
    private TextView entityName;
    private TextView ticketDisplay;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private String user_input;
    private ImageView entityImage;
    String answer;

    //from assignment 2
    private int numOfEntities;
    private Entity[] entities;
    private int ticketTotal;
    private int awardedTicketNumber;
    Entity current; //current entity used in guessmaster game

    public GuessMaster() { //constructor
        numOfEntities = 0;
        entities = new Entity[10];
    }

    public void addEntity(Entity entity) {
        entities[numOfEntities++] = entity.clone();
    }

    public void playGame(int entityId) {
        Entity entity = entities[entityId];
        playGame(entity);
    }

    public void playGame(Entity entity) {
        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "");
        Date date = new Date(answer);
        awardedTicketNumber = entity.getAwardedTicketNumber();

        if (date.precedes(entity.getBorn())) {
            AlertDialog.Builder incorrectalert = new AlertDialog.Builder(GuessMaster.this);
            incorrectalert.setTitle("Incorrect");
            incorrectalert.setMessage("Try a later date!");
            incorrectalert.setCancelable(true);

            AlertDialog dialog = incorrectalert.create();
            dialog.show();

        } else if (entity.getBorn().precedes(date)) {
            AlertDialog.Builder incorrectalert = new AlertDialog.Builder(GuessMaster.this);
            incorrectalert.setTitle("Incorrect");
            incorrectalert.setMessage("Try an earlier date!");
            incorrectalert.setCancelable(true);

            AlertDialog dialog = incorrectalert.create();
            dialog.show();
        } else if(entity.getBorn().equals(date)){
            AlertDialog.Builder winningalert = new AlertDialog.Builder(GuessMaster.this);
            winningalert.setTitle("Correct!");
            winningalert.setMessage("BINGO! " + entity.closingMessage());
            winningalert.setCancelable(false);
            setTicketTotal(entity.getAwardedTicketNumber());
            winningalert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getBaseContext(), current.welcomeMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = winningalert.create();
            dialog.show();

            changeEntity();


        }
    }

    public void playGame() {
            int entityId = genRandomEntityId();
            playGame(entityId);

    }

    public int genRandomEntityId() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }

    public void setTicketTotal(int awardedTickets) { //method to update total ticket balance
        ticketTotal = ticketTotal + awardedTickets;
        ticketDisplay.setText(String.valueOf(ticketTotal));
    }

    public void changeEntity() {
        userIn.getText().clear();
        current = entities[genRandomEntityId()];
        entityName.setText(current.getName());
        imageSetter();
        Toast.makeText(getBaseContext(), "You won " + awardedTicketNumber +" tickets!",
                Toast.LENGTH_SHORT).show();
        awardedTicketNumber = 0;
    }

    public void welcomeToGame() {
        AlertDialog.Builder welcomealert = new AlertDialog.Builder(GuessMaster.this);
        welcomealert.setTitle("GuessMaster Game v3");
        welcomealert.setMessage("Game is starting... Enjoy!");
        welcomealert.setCancelable(true);

        welcomealert.setNegativeButton("START_GAME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //show dialog
        AlertDialog dialog = welcomealert.create();
        dialog.show();
    }

    public void imageSetter() {
        switch (current.getName()){
            case "United States":
                entityImage.setImageResource(R.drawable.usaflag); //why is entityImage null?
                break;
            case "Justin Trudeau":
                entityImage.setImageResource(R.drawable.justint);
                break;
            case "Celine Dion":
                entityImage.setImageResource(R.drawable.celidion);
                break;
            case "Sean Pollen":
                entityImage.setImageResource(R.drawable.mycreator);
                break;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_activity);

        guessButton = (Button) findViewById(R.id.btnGuess);
        btnclearContent = (Button)findViewById(R.id.btnClear);
        userIn = (EditText) findViewById(R.id.guessinput);
        ticketDisplay = (TextView)findViewById(R.id.ticket);
        entityName = (TextView)findViewById(R.id.entitylabel);
        entityImage = (ImageView)findViewById(R.id.entityImage);

        Country usa = new Country("United States", new Date("July", 4, 1776), "Washington DC", 0.1);
        Person creator = new Person("Sean Pollen", new Date("December", 4, 2000), "Male", 1); //its me!
        Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);
        Singer dion = new Singer("Celine Dion", new Date("March", 30, 1968), "Female","La voix du bon Dieu", new Date("November", 6, 1981), 0.5);

        final GuessMaster gm = new GuessMaster();

        addEntity(usa);
        addEntity(creator);
        addEntity(trudeau);
        addEntity(dion);

        changeEntity(); //set current
        welcomeToGame();
        setTicketTotal(0);
        btnclearContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeEntity();
            }
        });

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(current);
            }
        });
    }

}
