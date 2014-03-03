package com.ariondev.tictactoeonline;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePlayer extends Activity {
	
	TextView tv;
	
	ImageButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	Button resetButton;
	
	int[][] pos;
	
	int i,j,turn;
	
	Boolean isClickable,alreadyWon;
	
	String yourTurn,computerTurn;
	
	public void RestartButtonClicked(View view){
		restartGame();
	}
	
	public void BackButtonClicked(View view){
		this.finish();
	}
	
	
	public void GameInit(){	
		b1 = (ImageButton) findViewById(R.id.button1);
		b2 = (ImageButton) findViewById(R.id.button2);
		b3 = (ImageButton) findViewById(R.id.button3);
		b4 = (ImageButton) findViewById(R.id.button4);
		b5 = (ImageButton) findViewById(R.id.button5);
		b6 = (ImageButton) findViewById(R.id.button6);
		b7 = (ImageButton) findViewById(R.id.button7);
		b8 = (ImageButton) findViewById(R.id.button8);
		b9 = (ImageButton) findViewById(R.id.button9);
		tv = (TextView) findViewById(R.id.statusView);
		
		resetButton = (Button) findViewById(R.id.button10);
		
		yourTurn = "    Your Turn";
		computerTurn = "Opponents Turn";
		
		tv.setText(yourTurn);
		
		pos = new int[3][3];
		
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				pos[i][j] = 0;
			}
		}
		
		turn = 0;
		isClickable = true;
		alreadyWon = false;
		
		resetButton.setVisibility(android.view.View.INVISIBLE);
	}
	
	public void GameRun(){
		if(turn>9){
			isClickable = false;
			declareWin(3);
		}
		if((turn%2)==0){
			if(!alreadyWon)
			{
				isClickable = true;
				tv.setText(yourTurn);
			}
		}else{
			if(!alreadyWon)
			{
				tv.setText(computerTurn);
				isClickable = false;
				compPlay();
			}
		}
		
	}
	
	public void ResetButtonClicked(View view){
		restartGame();
	}
	
	public void restartGame(){
		this.finish();
		Intent in = new Intent(this,SinglePlayer.class);
		startActivity(in);
	}
	public ImageButton GetButton(int a,int b){
		if(a==0 && b == 0){
			return b1;
		}else if(a==0 && b==1){
			return b2;
		}else if(a==0 && b==2){
			return b3;
		}else if(a==1 && b==0){
			return b4;
		}else if(a==1 && b==1){
			return b5;
		}else if(a==1 && b==2){
			return b6;
		}else if(a==2 && b==0){
			return b7;
		}else if(a==2 && b==1){
			return b8;
		}else if(a==2 && b==2){
			return b9;
		}else return null;
	}
	
	public int compCheck(){
		if(pos[0][0]==1 && pos[0][1]==1 && pos[0][2]==0 ) 
			return 3; 
		else if(pos[0][0]==1 && pos[0][2]==1 && pos[0][1]==0 )
			return 2; 
		else if(pos[0][0]==0 && pos[0][2]==1 && pos[0][1]==1 )
			return 1; 
		else if(pos[1][0]==1 && pos[1][1]==1 && pos[1][2]==0 ) 
			return 6; 
		else if(pos[1][0]==1 && pos[1][2]==1 && pos[1][1]==0 )
			return 5; 
		else if(pos[1][0]==0 && pos[1][2]==1 && pos[1][1]==1 )
			return 4; 
		else if(pos[2][0]==1 && pos[2][1]==1 && pos[2][2]==0 ) 
			return 9; 
		else if(pos[2][0]==1 && pos[2][2]==1 && pos[2][1]==0 )
			return 8; 
		else if(pos[2][0]==0 && pos[2][2]==1 && pos[2][1]==1 )
			return 7; 
		else if(pos[0][0]==1 && pos[1][0]==1 && pos[2][0]==0 ) 
			return 7; 
		else if(pos[0][0]==1 && pos[2][0]==1 && pos[1][0]==0 )
			return 4; 
		else if(pos[1][0]==1 && pos[2][0]==1 && pos[0][0]==0 )
			return 1; 
		else if(pos[0][1]==1 && pos[1][1]==1 && pos[2][1]==0 ) 
			return 8; 
		else if(pos[0][1]==1 && pos[2][1]==1 && pos[1][1]==0 )
			return 5; 
		else if(pos[1][1]==1 && pos[2][1]==1 && pos[0][1]==0 )
			return 2; 
		else if(pos[0][2]==1 && pos[1][2]==1 && pos[2][2]==0 ) 
			return 9; 
		else if(pos[0][2]==1 && pos[2][2]==1 && pos[1][2]==0 )
			return 6; 
		else if(pos[1][2]==1 && pos[2][2]==1 && pos[0][2]==0 )
			return 3; 
		else if(pos[0][0]==1 && pos[1][1]==1 && pos[2][2]==0 ) 
			return 9; 
		else if(pos[0][0]==1 && pos[2][2]==1 && pos[1][1]==0 )
			return 5; 
		else if(pos[1][1]==1 && pos[2][2]==1 && pos[0][0]==0 )
			return 1; 
		else if(pos[0][2]==1 && pos[1][1]==1 && pos[2][0]==0 ) 
			return 7; 
		else if(pos[0][2]==1 && pos[2][0]==1 && pos[1][1]==0 )
			return 5; 
		else if(pos[1][1]==1 && pos[2][0]==1 && pos[0][2]==0 )
			return 3;
		else return -100;
	}
	
	public void compPlay(){
		if(turn<3){
			int cx = 0;
			int cy = 0;
			boolean flag = true;
			
			while(Played(cx,cy)!=true){
				if(cx>2){
					cx = 0;
				}else if(cy>2){
					cy = 0;
				}else{
					if(flag){
						cx++;
						flag = false;
					}else{
						cy++;
						flag = true;
					}
				}
			}
			ImageButton b = GetButton(cx,cy);
			pos[cx][cy] = 2;
			b.setBackgroundResource(R.drawable.zero);
			turn++;
			if(WON(2)){
				declareWin(2);
			}
			GameRun();
		}else{
			if(turn<9){
				int cx=0;
				int cy=0;
				int cs = compCheck();
				if(cs==-100){
					cx = 0;
					cy = 0;
					boolean flag = true;
					
					while(Played(cx,cy)!=true){
						if(cx>2){
							cx = 0;
						}else if(cy>2){
							cy = 0;
						}else{
							if(flag){
								cx++;
								flag = false;
							}else{
								cy++;
								flag = true;
							}
						}
					}
				}else{
					if(cs==1){
						cx = 0;
						cy = 0;
					}else if(cs==2){
						cx = 0;
						cy = 1;
 					}else if(cs==3){
						cx = 0;
						cy = 2;
 					}else if(cs==4){
						cx = 1;
						cy = 0;
 					}else if(cs==5){
						cx = 1;
						cy = 1;
 					}else if(cs==6){
						cx = 1;
						cy = 2;
 					}else if(cs==7){
						cx = 2;
						cy = 0;
 					}else if(cs==8){
						cx = 2;
						cy = 1;
 					}else if(cs==9){
						cx = 2;
						cy = 2;
 					}
				}
				ImageButton b = GetButton(cx,cy);
				pos[cx][cy] = 2;
				b.setBackgroundResource(R.drawable.zero);
				turn++;
				if(WON(2)){
					declareWin(2);
				}
				GameRun();
			}
		}
	}
	
	boolean Played(int px,int py){
		if(pos[px][py]==0)
			return true;
		else return false;
	}
	
	boolean WON(int p){
		if(pos[0][0]==p && pos[0][1]==p && pos[0][2]==p){
			return true; 
		}else if(pos[0][0]==p && pos[1][0]==p && pos[2][0]==p){ 
			return true; 
		}else if(pos[1][0]==p && pos[1][1]==p && pos[1][2]==p){ 
			return true; 
		}else if(pos[0][1]==p && pos[1][1]==p && pos[2][1]==p){ 
			return true; 
		}else if(pos[2][0]==p && pos[2][1]==p && pos[2][2]==p){ 
			return true; 
		}else if(pos[0][2]==p && pos[1][2]==p && pos[2][2]==p){ 
			return true; 
		}else if(pos[0][0]==p && pos[1][1]==p && pos[2][2]==p){ 
			return true; 
		}else if(pos[0][2]==p && pos[1][1]==p && pos[2][0]==p){ 
			return true; 
		}else return false;
	}
	
	void declareWin(int p){
		
		alreadyWon = true;
		
		String str = "";
		if(p==1){
			str = "Player Wins!";
		}else if(p==2){
			str = "Computer Wins!";
		}else{
			str = "        Draw!";
		}
		isClickable = false;
		tv.setText(str);
		Toast result = Toast.makeText(this, str, (Toast.LENGTH_LONG+Toast.LENGTH_LONG));
		result.show();
		resetButton.setVisibility(android.view.View.VISIBLE);
	}
	
	public void button1Clicked(View view){
		if(isClickable && Played(0,0) && !alreadyWon){
			pos[0][0] = 1;
			b1.setImageResource(R.drawable.cross);
			turn++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}
	
	public void button2Clicked(View view){
		if(isClickable && Played(0,1) && !alreadyWon){
			pos[0][1] = 1;
			b2.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}
	
	public void button3Clicked(View view){
		if(isClickable && Played(0,2) && !alreadyWon){
			pos[0][2] = 1;
			b3.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}

	public void button4Clicked(View view){
		if(isClickable && Played(1,0) && !alreadyWon){
			pos[1][0] = 1;
			b4.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}

	public void button5Clicked(View view){
		if(isClickable && Played(1,1) && !alreadyWon){
			pos[1][1] = 1;
			b5.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}

	public void button6Clicked(View view){
		if(isClickable && Played(1,2) && !alreadyWon){
			pos[1][2] = 1;
			b6.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}

	public void button7Clicked(View view){
		if(isClickable && Played(2,0) && !alreadyWon){
			pos[2][0] = 1;
			b7.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}

	public void button8Clicked(View view){
		if(isClickable && Played(2,1) && !alreadyWon){
			pos[2][1] = 1;
			b8.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}

	public void button9Clicked(View view){
		if(isClickable && Played(2,2) && !alreadyWon){
			pos[2][2] = 1;
			b9.setImageResource(R.drawable.cross);
			turn++;
			if(WON(1)){
				declareWin(1);
			}
			GameRun();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_player_game);
		
		GameInit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_player_game, menu);
		return true;
	}

}

