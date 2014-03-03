package com.ariondev.tictactoeonline;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MultiplayerGameHost extends Activity {
	String url = "http://www.ariondev.com/ttt/";
	String baseurl = "http://www.ariondev.com/ttt/";
	WebView webView = null;
	String value = null;
    ProgressDialog pDialog;
    int a,b,c,d,e,f,g,h,ii,turn,last;
    int current;
    TextView tv;
    
    String yourTurn,computerTurn;
    
    ImageButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
    Button resetButton;
    
    int[][] pos;
	
	Boolean isClickable,alreadyWon;
    
    public void GameInIt(){
    	b1 = (ImageButton) findViewById(R.id.button1);
		b2 = (ImageButton) findViewById(R.id.button2);
		b3 = (ImageButton) findViewById(R.id.button3);
		b4 = (ImageButton) findViewById(R.id.button4);
		b5 = (ImageButton) findViewById(R.id.button5);
		b6 = (ImageButton) findViewById(R.id.button6);
		b7 = (ImageButton) findViewById(R.id.button7);
		b8 = (ImageButton) findViewById(R.id.button8);
		b9 = (ImageButton) findViewById(R.id.button9);
		resetButton = (Button) findViewById(R.id.button10);
		
    	tv = (TextView) findViewById(R.id.statusView);
		
		pos = new int[3][3];
    	
	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			 new GetXml(url).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{null});
        else
          new GetXml(url).execute(new String[]{null});
	    
	    pos[0][0] = a;
	    pos[0][1] = b;
	    pos[0][2] = c;
	    pos[1][0] = d;
	    pos[1][1] = e;
	    pos[1][2] = f;
	    pos[2][0] = g;
	    pos[2][1] = h;
	    pos[2][2] = ii;
		
    	yourTurn = "    Your Turn";
		computerTurn = "Opponents Turn";
		
		tv.setText(yourTurn);
		
		isClickable = true;
		alreadyWon = false;
		current = 0;
		
		resetButton.setVisibility(android.view.View.INVISIBLE);
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
			str = "You Won!";
		}else if(p==2){
			str = "Opponent Won!";
		}else{
			str = "Draw!";
		}
		isClickable = false;
		tv.setText(str);
		Toast result = Toast.makeText(this, str, (Toast.LENGTH_LONG+Toast.LENGTH_LONG));
		result.show();
		resetButton.setVisibility(android.view.View.VISIBLE);
	}
	
	public void SendToServer(){
		
		if(last==1){
			a=1;
		}else if(last==2){
			b=1;
		}else if(last==3){
			c=1;
		}else if(last==4){
			d=1;
		}else if(last==5){
			e=1;
		}else if(last==6){
			f=1;
		}else if(last==7){
			g=1;
		}else if(last==8){
			h=1;
		}else if(last==9){
			ii=1;
		}
		
		String makelink = baseurl + "make.php?id=" + value + "&a="+a+"&b="+b+"&c="+c+"&d="+d+"&e="+e+"&f="+f+"&g="+g+"&h="+h+"&i="+ii+"&turn="+turn+"&last="+last;
		
		webView.loadUrl(makelink);
	}
	
	public void getUpdateButtonClicked(View view){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			 new GetXml(url).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{null});
       else
         new GetXml(url).execute(new String[]{null});
		
		if(turn==1){
//			String aaaaa = "&a="+a+"&b="+b+"&c="+c+"&d="+d+"&e="+e+"&f="+f+"&g="+g+"&h="+h+"&i="+ii+"&turn="+turn+"&last="+last;
//		    Toast.makeText(this, aaaaa, Toast.LENGTH_LONG).show();
//		    TextView txx = (TextView) findViewById(R.id.textView1);
//		    txx.setText(aaaaa);
		
		current++;
		
		
		if(WON(1)){
			declareWin(1);
		}else if(WON(2)){
			declareWin(2);
		}else if(current>19){
			declareWin(3);
		}else{
			isClickable = true;
			
			pos[0][0] = a;
		    pos[0][1] = b;
		    pos[0][2] = c;
		    pos[1][0] = d;
		    pos[1][1] = e;
		    pos[1][2] = f;
		    pos[2][0] = g;
		    pos[2][1] = h;
		    pos[2][2] = ii;    
		    
		    int zx=-1;
		    int zy=-1;
		    if(last == 1){
		    	zx = 0;
		    	zy = 0;
		    }else if(last == 2){
		    	zx = 0;
		    	zy = 1;
		    }else if(last == 3){
		    	zx = 0;
		    	zy = 2;
		    }else if(last == 4){
		    	zx = 1;
		    	zy = 0;
		    }else if(last == 5){
		    	zx = 1;
		    	zy = 1;
		    }else if(last == 6){
		    	zx = 1;
		    	zy = 2;
		    }else if(last == 7){
		    	zx = 2;
		    	zy = 0;
		    }else if(last == 8){
		    	zx = 2;
		    	zy = 1;
		    }else if(last == 9){
		    	zx = 2;
		    	zy = 2;
		    }
		    
		    pos[zx][zy] = 2;
		    ImageButton n = getButton(last);
		    n.setImageResource(R.drawable.zero);
		}
		}
		
	}
	
	public ImageButton getButton(int x){
		if(x==1) return b1;
		else if(x==2) return b2;
		else if(x==3) return b3;
		else if(x==4) return b4;
		else if(x==5) return b5;
		else if(x==6) return b6;
		else if(x==7) return b7;
		else if(x==8) return b8;
		else if(x==9) return b9;
		else return null;
	}
    
    public void button1Clicked(View view){
		if(isClickable && Played(0,0) && !alreadyWon){
			pos[0][0] = 1;
			b1.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 1;
			SendToServer();
		}
	}
    
    public void button2Clicked(View view){
		if(isClickable && Played(0,1) && !alreadyWon){
			pos[0][1] = 1;
			b2.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 2;
			SendToServer();
		}
	}
    
    public void button3Clicked(View view){
		if(isClickable && Played(0,2) && !alreadyWon){
			pos[0][2] = 1;
			b3.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 3;
			SendToServer();
		}
	}
    
    public void button4Clicked(View view){
		if(isClickable && Played(1,0) && !alreadyWon){
			pos[1][0] = 1;
			b4.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 4;
			SendToServer();
		}
	}
    
    public void button5Clicked(View view){
		if(isClickable && Played(1 ,1) && !alreadyWon){
			pos[1][1] = 1;
			b5.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 5;
			SendToServer();
		}
	}
    
    public void button6Clicked(View view){
		if(isClickable && Played(1,2) && !alreadyWon){
			pos[1][2] = 1;
			b6.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 6;
			SendToServer();
		}
	}
    
    public void button7Clicked(View view){
		if(isClickable && Played(2,0) && !alreadyWon){
			pos[2][0] = 1;
			b7.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 7;
			SendToServer();
		}
	}
    
    public void button8Clicked(View view){
		if(isClickable && Played(2,1) && !alreadyWon){
			pos[2][1] = 1;
			b8.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 8;
			SendToServer();
		}
	}
    
    public void button9Clicked(View view){
		if(isClickable && Played(2 ,2) && !alreadyWon){
			pos[2][2] = 1;
			b9.setImageResource(R.drawable.cross);
			turn = 2;
			current++;
			isClickable = false;
			if(WON(1)){
				declareWin(1);
			}else if(WON(2)){
				declareWin(2);
			}else if(current>9){
				declareWin(3);
			}
			last = 9;
			SendToServer();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplayer_game);
		
		Bundle bdl = getIntent().getExtras();
		value = bdl.getString("id");
		
		url = url + value + ".xml"; 
		
		webView = new WebView(this);
		
		GameInIt();
		
	}
	
	public void ResetButtonClicked(View view){
//		String msg = String.valueOf(a) + " " + String.valueOf(b) + " " + String.valueOf(c) + " " + String.valueOf(d) + " " + String.valueOf(e) + " " + String.valueOf(f) + " " + String.valueOf(g) + " " + String.valueOf(h) + " " + String.valueOf(ii) + " " + String.valueOf(turn) + " end";
//		
//		Toast.makeText(this, msg, (Toast.LENGTH_LONG*3)).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multiplayer_game, menu);
		return true;
	}
	public class GetXml extends AsyncTask<String, Void, String>{
		String urls;
		
		public GetXml(String urls){
			this.urls = urls;
		}
		
		@Override
		protected void onPreExecute(){
			pDialog = ProgressDialog.show(MultiplayerGameHost.this, "Retrieving Data from Server... ", "Please Wait...", false);
		}
		
		@Override
		protected String doInBackground(String... params){
			URL url;
			try{
				url = new URL(urls);
				DocumentBuilderFactory mDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder mDocumentBuilder = mDocumentBuilderFactory.newDocumentBuilder();
				Document mDocument = mDocumentBuilder.parse(new InputSource(url.openStream()));
				
				mDocument.getDocumentElement().normalize();
				
				NodeList mNodeList = mDocument.getElementsByTagName("stat");
				
				for(int i=0;i<mNodeList.getLength();i++){
					Node mNode = mNodeList.item(i);
					
					Element mElement = (Element) mNode;
					NodeList mStringList = mElement.getElementsByTagName("a");
					Element strElement = (Element) mStringList.item(0);
					mStringList = strElement.getChildNodes();
					
					a = Integer.parseInt(mStringList.item(0).getNodeValue());
					
					NodeList mStringList2 = mElement.getElementsByTagName("b");
					Element strElement2 = (Element) mStringList2.item(0);
					mStringList2 = strElement2.getChildNodes();
					
					b = Integer.parseInt(mStringList2.item(0).getNodeValue());
					
					NodeList mStringList3 = mElement.getElementsByTagName("c");
					Element strElement3 = (Element) mStringList3.item(0);
					mStringList3 = strElement3.getChildNodes();
					
					c = Integer.parseInt(mStringList3.item(0).getNodeValue());
					
					NodeList mStringList4 = mElement.getElementsByTagName("d");
					Element strElement4 = (Element) mStringList4.item(0);
					mStringList4 = strElement4.getChildNodes();
					
					d = Integer.parseInt(mStringList4.item(0).getNodeValue());
					
					NodeList mStringList5 = mElement.getElementsByTagName("e");
					Element strElement5 = (Element) mStringList5.item(0);
					mStringList5 = strElement5.getChildNodes();
					
					e = Integer.parseInt(mStringList5.item(0).getNodeValue());
					
					NodeList mStringList6 = mElement.getElementsByTagName("f");
					Element strElement6 = (Element) mStringList6.item(0);
					mStringList6 = strElement6.getChildNodes();
					
					f = Integer.parseInt(mStringList6.item(0).getNodeValue());
					
					NodeList mStringList7 = mElement.getElementsByTagName("g");
					Element strElement7 = (Element) mStringList7.item(0);
					mStringList7 = strElement7.getChildNodes();
					
					g = Integer.parseInt(mStringList7.item(0).getNodeValue());
					
					NodeList mStringList8 = mElement.getElementsByTagName("h");
					Element strElement8 = (Element) mStringList8.item(0);
					mStringList8 = strElement8.getChildNodes();
					
					h = Integer.parseInt(mStringList8.item(0).getNodeValue());
					
					NodeList mStringList9 = mElement.getElementsByTagName("i");
					Element strElement9 = (Element) mStringList9.item(0);
					mStringList9 = strElement9.getChildNodes();
					
					ii = Integer.parseInt(mStringList9.item(0).getNodeValue());
					
					NodeList mStringList10 = mElement.getElementsByTagName("turn");
					Element strElement10 = (Element) mStringList10.item(0);
					mStringList10 = strElement10.getChildNodes();
					
					turn = Integer.parseInt(mStringList10.item(0).getNodeValue());
					
					NodeList mStringList11 = mElement.getElementsByTagName("last");
					Element strElement11 = (Element) mStringList11.item(0);
					mStringList11 = strElement11.getChildNodes();
					
					last = Integer.parseInt(mStringList11.item(0).getNodeValue());
				}
			}catch(MalformedURLException e){
				e.printStackTrace();
			}catch(ParserConfigurationException e){
				e.printStackTrace();
			}catch(SAXException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
				return null;
		}
		
		@Override
		protected void onPostExecute(String result){
			if(pDialog.isShowing())
				pDialog.dismiss();
		}
		
	}
}
