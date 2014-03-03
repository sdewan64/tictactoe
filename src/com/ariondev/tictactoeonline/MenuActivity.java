package com.ariondev.tictactoeonline;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


public class MenuActivity extends Activity {
	
	public void SinglePlayerButtonClicked(View view){
		Intent in = new Intent(this,SinglePlayer.class);
		startActivity(in);
		this.finish();
	}
	
	public void MultiPlayerButtonClicked(View view){
		Intent in = new Intent(this,MultiPlayerGameMenu.class);
		startActivity(in);
		this.finish();
	}
	
	public void QuitButtonClicked(View view){
		this.finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_online, menu);
		return true;
	}

}
