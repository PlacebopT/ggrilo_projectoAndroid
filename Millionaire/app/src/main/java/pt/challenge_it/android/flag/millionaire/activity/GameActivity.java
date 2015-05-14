package pt.challenge_it.android.flag.millionaire.activity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pt.challenge_it.android.flag.millionaire.R;
import pt.challenge_it.android.flag.millionaire.model.Question;
import pt.challenge_it.android.flag.millionaire.provider.OperationsManager;

public class GameActivity extends ActionBarActivity implements View.OnClickListener {

    private Question[] questions;
    private int currentQuestionIndex;
    private int[] prizeLevel = {50,100,200,300,500,700,1200,2000,3000,4000,6000,12000,20000,30000,100000};
    private int[] safePrizeLevel = {0,0,0,0,500,500,500,500,500,4000,4000,4000,4000,4000,100000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        new GetQuestionsTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view)
    {


        if((boolean)view.getTag())
        {
            this.currentQuestionIndex++;
            changeToNextQuestion();

            if (this.currentQuestionIndex < 15){
                Question question = this.questions[this.currentQuestionIndex];


                for (int i = 1; i<=question.getIdentifier()-1; i++)
                {
                    if (question.getIdentifier()>i)
                    {
                        TextView txtPrize = (TextView)findViewById(R.id.txtPrize);
                        txtPrize.setText("Acertou, está com " + String.valueOf(prizeLevel[i-1]) + " Euros!");

                        TextView txtPrizeLevel = (TextView)findViewById(R.id.txtSafePrize);
                        txtPrizeLevel.setText("Valor de segurança: " + String.valueOf(safePrizeLevel[i-1])+ " Euros!");
                    }
                }
            }
        }else
            {
                Question question = this.questions[this.currentQuestionIndex];

                if (question.getIdentifier() < 5)
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over) + " E ficaste com " + String.valueOf(safePrizeLevel[1]) + " Euros!!!", Toast.LENGTH_LONG).show();
                    finish();
                }
                else if (question.getIdentifier() > 4 && question.getIdentifier() < 10)
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over) + " Mas ganhaste " + String.valueOf(safePrizeLevel[5]) + " Euros!!!", Toast.LENGTH_LONG).show();
                    finish();
                } else if (question.getIdentifier() > 10 && question.getIdentifier() < 15)
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over) + " Mas ganhaste " + String.valueOf(safePrizeLevel[10]) + " Euros!!!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
    }

    private void changeToNextQuestion()
    {
        if (this.currentQuestionIndex == 15){
            Toast.makeText(getApplicationContext(), "GANHOU " + String.valueOf(prizeLevel[14]) + " Euros!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Question question = this.questions[this.currentQuestionIndex];

        TextView txtQuestion = (TextView)findViewById(R.id.txtQuestion);
        txtQuestion.setText(question.getIdentifier() + " - " + question.getText());

        for (Question.Answer answer : question.getAnswers())
        {
            switch (answer.getIdentifier()) {
                case 'A':
                    Button btnAnswerA = (Button)findViewById(R.id.buttonAnswerA);
                    btnAnswerA.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerA.setTag(answer.isCorrect());
                    btnAnswerA.setOnClickListener(this);
                    break;
                case 'B':
                    Button btnAnswerB = (Button)findViewById(R.id.buttonAnswerB);
                    btnAnswerB.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerB.setTag(answer.isCorrect());
                    btnAnswerB.setOnClickListener(this);
                    break;
                case 'C':
                    Button btnAnswerC = (Button)findViewById(R.id.buttonAnswerC);
                    btnAnswerC.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerC.setTag(answer.isCorrect());
                    btnAnswerC.setOnClickListener(this);
                    break;
                case 'D':
                    Button btnAnswerD = (Button)findViewById(R.id.buttonAnswerD);
                    btnAnswerD.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerD.setTag(answer.isCorrect());
                    btnAnswerD.setOnClickListener(this);
                    break;
                default:
                    break;
            }
        }
    }

    public class GetQuestionsTask extends AsyncTask<Void,Void,Question[]>
    {
        @Override
        protected Question[] doInBackground(Void... voids)
        {
            return OperationsManager.getAllTemp();
        }

        @Override
        protected void onPostExecute(Question[] questions)
        {
            GameActivity.this.questions = questions;
            GameActivity.this.currentQuestionIndex = 0;
            changeToNextQuestion();
        }
    }
}
