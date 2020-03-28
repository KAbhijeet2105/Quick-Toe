package india.abhijeet.k.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private Button[][]  buttons= new Button[3][3];
   private boolean Player1Turn =true;

   private int roundCount;

    private int Player1Points;

    private int Player2Points;


private TextView  txt_v_playear1;
    private TextView  txt_v_playear2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_main);


        txt_v_playear1=findViewById(R.id.text_view_p1);
        txt_v_playear2=findViewById(R.id.text_view_p2);


        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {

              String buttonId="btn_"+i+j;
              int resId= getResources().getIdentifier(buttonId,"id",getPackageName());

              buttons[i][j]=findViewById(resId);
              buttons[i][j].setOnClickListener(this);
            }
        }


        Button reset= findViewById(R.id.btn_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reserGame();

            }
        });




    }

    @Override
    public void onClick(View view) {

        if (!((Button) view).getText().toString().equals(""))
        {
            return;
        }


        if (Player1Turn)
        {
            ((Button) view).setText("X");

        }
        else {

            ((Button) view).setText("O");

        }

        roundCount++;

        if (checkForWin())
        {

            if (Player1Turn)
            {
                player1Wins();
            }
            else
            {
                player2Wins();

            }


        }else if(roundCount==9)
        {

         draw();

        }


        else {

            Player1Turn = !Player1Turn;

        }



    }

    private boolean checkForWin(){

        String[][] field  = new String[3][3];

        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {

                field[i][j]=buttons[i][j].getText().toString();
            }
        }


        for (int i = 0; i < 3; i++) {

            if (field[i][0].equals(field[i][1])
                    &&field[i][0].equals(field[i][2])
                    && !field[i][0].equals("") )
            {

                return true;
            }

        }



        for (int i = 0; i < 3; i++) {

            if (field[0][i].equals(field[1][i])
                    &&field[0][i].equals(field[2][i])
                    && !field[0][i].equals("") )
            {

                return true;
            }

        }




        if (field[0][0].equals(field[1][1])                                             //go top left to bottom right
                &&field[0][0].equals(field[2][2])
                && !field[0][0].equals("") )
        {

            return true;
        }




        if (field[0][2].equals(field[1][1])                                                   //go top right to bottom left
                &&field[0][2].equals(field[2][0])
                && !field[0][2].equals("") )
        {

            return true;
        }


        return false;

    }



    private void player1Wins()
    {
        Player1Points++;

        Toast.makeText(this, "Player 1 Win this round!!", Toast.LENGTH_LONG).show();

        updatePoints();
        resetBoard();

    }


    private void player2Wins()
    {

        Player2Points++;
        Toast.makeText(this, "Player 2 Win this round!!", Toast.LENGTH_LONG).show();

        updatePoints();
        resetBoard();


    }

    private void draw()
    {

        Toast.makeText(this, "This round is Draw!!", Toast.LENGTH_SHORT).show();
        resetBoard();

    }


    private  void updatePoints()
    {

        txt_v_playear1.setText("Player 1 : "+Player1Points);
        txt_v_playear2.setText("Player 2 : "+Player2Points);
    }

    private void resetBoard()
    {

        for (int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j].setText("");

            }
        }

         roundCount=0;
        Player1Turn=true;

    }


    private void reserGame(){

        resetBoard();
        Player1Points=0;
        Player2Points=0;
        updatePoints();


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("Player1Points",Player1Points);
        outState.putInt("Player2Points",Player2Points);

        outState.putBoolean("Player1Turn",Player1Turn);



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount= savedInstanceState.getInt("roundCount");
        Player1Points=savedInstanceState.getInt("Player1Points");
                Player2Points=savedInstanceState.getInt("Player2Points");

        Player1Turn=savedInstanceState.getBoolean("Player1Turn");

    }
}
