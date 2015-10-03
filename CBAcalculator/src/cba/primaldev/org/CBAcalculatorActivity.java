package cba.primaldev.org;


import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CBAcalculatorActivity extends Activity {
	
	ArrayList<PlayersScore> playersScoreList;
	
	
	private TextView playerNum;
	private EditText playerPoints;
	private EditText playerHcp;
	private Button prevPlayer;
	private Button delPlayer;
	private Button nextPlayer;
	private CheckBox plusHandicap;
	String introText= "COMPUTED BUFFER ADJUSTMENT /n is calculated at the conclusion of each round of a qualifying competition, with the exception of a 9-hole competition" ;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
    
    private void setPlayerEntryScreen(){
    	setContentView(R.layout.playerscore);    	
    	playerNum=(TextView) findViewById(R.id.playerNum);
    	playerPoints = (EditText) findViewById(R.id.playerPoints);
    	playerHcp = (EditText) findViewById(R.id.playerHcp);
    	prevPlayer=(Button) findViewById(R.id.prevPlayer);
    	nextPlayer = (Button) findViewById(R.id.nextPlayer);
    	delPlayer = (Button) findViewById(R.id.delPlayer);
    	plusHandicap = (CheckBox) findViewById(R.id.plusHandicap);
    	playerNum.setText("1");
    	playerHcp.setText("0");
    	playerPoints.setText("0");
    	prevPlayer.setEnabled(false);
    	delPlayer.setEnabled(false);
    	
    	
    	  	
    	
    	playerPoints.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				if (playerPoints.length()==2 ) {
					playerHcp.requestFocus();
					
				}
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
			
           
    	}); 
    	
    	playerHcp.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				if (playerHcp.length()==2) {
					//playerHcp.setText(playerHcp.getText() + ".");
					playerHcp.append(".");
				}
				
				if (playerHcp.length()==4) {
					nextPlayer.requestFocus(); //does not work
				}
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}});
    	
    }
    
    public void startEntryPlayers(View v) {
    	playersScoreList= new ArrayList<PlayersScore>();
    	setPlayerEntryScreen();
    }
    
    
   
    
    public void nextPlayerClicked(View v) {
    	
       moveN(1);
    	    	
    }
    
    private void loadPlayerN(int PlayerNum){
    	PlayersScore playersScore = playersScoreList.get(PlayerNum -1);
    	playerHcp.setText(String.valueOf(playersScore.getHadicap()));
    	playerPoints.setText(String.valueOf(playersScore.getScore()));
    	playerNum.setText(String.valueOf(PlayerNum));
    }
    
    private void moveN(int step) {
    	
    	PlayersScore playersScore = new PlayersScore(Double.parseDouble(playerHcp.getText().toString()),Integer.valueOf(playerPoints.getText().toString()),Integer.valueOf(playerNum.getText().toString()));
    	delPlayer.setEnabled(false);
    	
    	if (plusHandicap.isChecked()) {
    		playersScore.setHadicap(-playersScore.getHadicap());
    		plusHandicap.setChecked(false);
    	}
    	    	
    	if (playersScoreList.size() < Integer.valueOf(playerNum.getText().toString())) {
    		
    		playersScoreList.add(playersScore);
    		
        	playerNum.setText(String.valueOf(Integer.valueOf(playerNum.getText().toString()) + step));        	
    	}else{
    		playersScoreList.set(Integer.valueOf(playerNum.getText().toString()) - 1, playersScore);    		    		
    		playerNum.setText(String.valueOf(Integer.valueOf(playerNum.getText().toString()) + step));
    		
    	}

    	
    	if (playersScoreList.size() >= Integer.valueOf(playerNum.getText().toString())) {
			playersScore = playersScoreList.get(Integer.valueOf(playerNum.getText().toString())-1);
	    	if (playersScore.getHadicap() < 0) {
	    		plusHandicap.setChecked(true);
	    		playersScore.setHadicap(-playersScore.getHadicap());
	    	}
        	playerHcp.setText(String.valueOf(playersScore.getHadicap()));

        	playerPoints.setText(String.valueOf(playersScore.getScore()));
        	delPlayer.setEnabled(true);
		}else{
        	playerHcp.setText("0");
        	playerPoints.setText("0");
		}
    	

    	
    	if (Integer.valueOf(playerNum.getText().toString()) > 1) {
    		prevPlayer.setEnabled(true);
    		
    	}else{
    		prevPlayer.setEnabled(false);
    		
    	}
    
    	playerPoints.requestFocus();
    	playerPoints.selectAll();
    }
    

    public void finnishClicked(View v) { 	
    	int cba;
    	moveN(0);


    	setContentView(R.layout.overview);
    	TextView textCBA = (TextView) findViewById(R.id.textCBA);
    	ArrayAdapter<PlayersScore> adapter = new ArrayAdapter<PlayersScore>(this,android.R.layout.simple_list_item_1, playersScoreList);
    	
    	ListView playerView = (ListView) findViewById(R.id.list);
    	
    	playerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long row) {
				setContentView(R.layout.playerscore);
				setPlayerEntryScreen();
				if (playersScoreList.size() > 1) {
					delPlayer.setEnabled(true);
				}
				loadPlayerN((int) row +1 );
				
			}
		});
    	

	    playerView.setAdapter(adapter);
	    
	    CalculateCba calcCBA = new CalculateCba(playersScoreList);
    	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    	imm.hideSoftInputFromWindow(v.getWindowToken(),0); 
    	
		cba=calcCBA.getCBA();
		
		AlertDialog ad = new AlertDialog.Builder(this).create();  
		ad.setCancelable(false); // This blocks the 'BACK' button  
		 
		ad.setButton("OK", new DialogInterface.OnClickListener() {  
		    @Override  
		    public void onClick(DialogInterface dialog, int which) {  
		        dialog.dismiss();                      
		    }  
		});  
		
    	if (cba==9){
    		ad.setMessage("Less than 10 players entered with handicap category 1 to 4"); 
    		textCBA.setText("None");
    		ad.show();
    	} else if (cba==10){
    		ad.setMessage("The result falls outside the CBA scope"); 
    		textCBA.setText("invalid");
    		ad.show();
    	}else{
    		textCBA.setText(String.valueOf(calcCBA.getCBA()));
    	}
		
		
    }
    
    protected void onListItemClick(ListView l , View v, int position, long id) {
    	setContentView(R.layout.playerscore);
		loadPlayerN(position);
	}
    
    
    public void prevPlayerClicked(View v) {
    	moveN(-1);
    }
    
    public void overviewOkClicked(View v) {
    	setContentView(R.layout.main);
    }
    
    private ArrayList<PlayersScore> reindexArray(ArrayList<PlayersScore> theList){
    	ArrayList<PlayersScore> cleanList = new ArrayList<PlayersScore>();    	
    	for (PlayersScore playerScore : theList){
    		cleanList.add(playerScore);
    	}
    	
    	return cleanList;
    }
    
    public void delPlayerPressed (View v) {
    	playersScoreList.remove(Integer.valueOf(playerNum.getText().toString()) - 1);
    	playersScoreList = reindexArray(playersScoreList);
    	loadPlayerN(1);
    }
    
}