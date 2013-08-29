package com.example.lunchcount;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FoodCount extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_count);
        
        TextView dateText = (TextView) findViewById(R.id.textView6);
        String todayDate = DateFormat.getDateInstance().format(new Date());
        String dayOfWeek = new SimpleDateFormat("EEEE").format(new Date());
        dateText.setText(dayOfWeek + ", " + todayDate);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_count, menu);
        return true;
    }
    
    public void sendMail(View clickedButton) {
    	String email = "michael_tso@yahoo.com";
    	String subject = "QHC Lunch Summary - " +  DateFormat.getDateInstance().format(new Date()) ;
    	Intent i = new Intent(Intent.ACTION_SEND);
    	i.setType("message/rfc822");
    	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
    	i.putExtra(Intent.EXTRA_SUBJECT, subject);

    	// textView is the TextView view that should display it    	
    	String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    	EditText adultText = (EditText)findViewById(R.id.adultText);
    	String adultTextStr = adultText.getText().toString();
    	
    	EditText elderText = (EditText)findViewById(R.id.elderText);
    	String elderTextStr = elderText.getText().toString();
    	
    	EditText childText = (EditText)findViewById(R.id.childText);
    	String childTextStr = childText.getText().toString();
    	
    	EditText newcomerChinText = (EditText)findViewById(R.id.newcomerChin);
    	String newcomerChinTextStr = newcomerChinText.getText().toString();
    	
    	EditText newcomerEngText = (EditText)findViewById(R.id.newcomerEng);
    	String newcomerEngTextStr = newcomerEngText.getText().toString();
    	
    	String bodyMsg = "Date: " + currentDateTimeString + "\nAdults: " + adultTextStr + "\nElders: " + elderTextStr + "\nChildren: " + childTextStr + "\nNewcomers (Chinese): " + newcomerChinTextStr
    			+ "\nNewcomers (English): " + newcomerEngTextStr + "\n\nExpense: \nBalance:";
    	i.putExtra(Intent.EXTRA_TEXT, bodyMsg);
    	
    	try {
    	    startActivity(Intent.createChooser(i, "Send mail..."));
    	} catch (android.content.ActivityNotFoundException ex) {
    	    Toast.makeText(FoodCount.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
    	}
    }
    
}
