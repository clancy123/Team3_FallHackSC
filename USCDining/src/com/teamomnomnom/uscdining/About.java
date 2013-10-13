package com.teamomnomnom.uscdining;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class About extends Activity implements OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String version = "v";
		try {
			version += getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			version += "5.0";
			e.printStackTrace();
		}
		
			ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setSubtitle(version);
            Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ab_texture_tile);
            final BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bMap);
            bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            actionBar.setBackgroundDrawable(bitmapDrawable);
		
		setContentView(R.layout.about);
		
		// Set up click listeners for all the buttons
    	View licensesButton = findViewById(R.id.about_licenses_button);
    	licensesButton.setOnClickListener(this);
    	
    	TextView versionText = (TextView) findViewById(R.id.about_versiontextView);
    	versionText.setText(version);
	}
	
	/** Handle when a button is clicked, call appropriate action */
    public void onClick(View v) 
    {
    	switch (v.getId()) 
    	{    
    		case R.id.about_licenses_button:
    			Intent intent = new Intent(About.this, AboutHelper.class);
    			startActivity(intent);
    			break;
    	}
    }
    
	@Override
	protected void onResume() 
	{
		super.onResume();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	        	finish();//NavUtils.navigateUpFromSameTask(this);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
