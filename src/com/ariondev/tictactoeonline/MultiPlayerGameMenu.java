package com.ariondev.tictactoeonline;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MultiPlayerGameMenu extends Activity {
	
	String url = "http://www.ariondev.com/ttt/";
	
	public void JoinGameButtonClicked(View view){
		AlertDialog.Builder userName = new AlertDialog.Builder(this);
		userName.setTitle("Key Phrase");
		userName.setMessage("Please Enter Key Phrase where you want to connect");
		
		final EditText input = new EditText(this);
		userName.setView(input);
		
		userName.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				String value = input.getText().toString();
				
				String msg = "Connecting to " + value + " .Please Wait.";
				Toast.makeText(getApplicationContext(), msg, (Toast.LENGTH_LONG*4)).show();
				
				Intent in = new Intent(getApplicationContext(),MultiplayerGameClient.class);
				in.putExtra("who", "client");
				in.putExtra("id", value);
				startActivity(in);
			}
		});
	   userName.show();
	}
	
	public void HostNewGameButtonClicked(View view){
		
		AlertDialog.Builder userName = new AlertDialog.Builder(this);
		userName.setTitle("Key Phrase");
		userName.setMessage("Please Enter any Key Phrase");
		
		final EditText input = new EditText(this);
		userName.setView(input);
		
		userName.setPositiveButton("Host New Game", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				String value = input.getText().toString();
				
				String makelink = url + "make.php?id=" + value + "&a=0&b=0&c=0&d=0&e=0&f=0&g=0&h=0&i=0&turn=1&last=0";
				
				
				WebView webView = new WebView(getApplicationContext());
				
				webView.loadUrl(makelink);
				
				String msg = "Ask you friend to enter " + value + " as key phrase to join the game";
				Toast.makeText(getApplicationContext(), msg, (Toast.LENGTH_LONG*4)).show();
				
				Intent in = new Intent(getApplicationContext(),MultiplayerGameHost.class);
				in.putExtra("who", "host");
				in.putExtra("id", value);
				startActivity(in);
			}
		});
	   userName.show();
	}
	
	public void QuitButtonClicked(View view){
		this.finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_player_game_menu);
		
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multi_player_game_menu, menu);
		return true;
	}
	
	

}
