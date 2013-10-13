package com.teamomnomnom.uscdining;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.MenuItem;

public class Favorites extends PreferenceActivity
{
    /**
     * When starting this activity, the invoking Intent can contain this extra
     * string to specify which fragment should be initially displayed.
     */
    public static final String EXTRA_SHOW_FRAGMENT = ":android:show_fragment";

    /**
     * When starting this activity, the invoking Intent can contain this extra
     * boolean that the header list should not be displayed.  This is most often
     * used in conjunction with {@link #EXTRA_SHOW_FRAGMENT} to launch
     * the activity to display a specific fragment that the user has navigated
     * to.
     */
    public static final String EXTRA_NO_HEADERS = ":android:no_headers";
    
	private static final String OPT_PO_BOY_SANDWITCH_BAR = "po_boy_sandwitch_bar";
	private static final String OPT_MEDITERRANEAN_BAR = "mediterranean_bar";
	private static final String OPT_RAVIOLI_BAR = "ravioli_bar";
	private static final String OPT_ROASTED_LEG_OF_LAMB = "roasted_leg_of_lamb";
	private static final String OPT_MOROCCAN_SPICED_CHICKEN = "moroccan_spiced_chicken";
	private static final String OPT_GREEK_PIZZA = "greek_pizza";

    public static class CombinedPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.favorites);

            final ActionBar actionBar = getActivity().getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ab_texture_tile);
            final BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bMap);
            bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            actionBar.setBackgroundDrawable(bitmapDrawable);
        }
    }
	
	public static boolean getPoBoySandwitchBar(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_PO_BOY_SANDWITCH_BAR, false);
	}
	
	public static boolean getMediterraneanBar(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_MEDITERRANEAN_BAR, false);
	}
	
	public static boolean getRavioliBar(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_RAVIOLI_BAR, false);
	}
	
	public static boolean getRoastedLegOfLamb(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_ROASTED_LEG_OF_LAMB, false);
	}
	
	public static boolean getMoroccanSpicedChicken(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_MOROCCAN_SPICED_CHICKEN, false);
	} 
	
	public static boolean getGreekPizza(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_GREEK_PIZZA, false);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {    
	        case android.R.id.home:
	            // Icon in action bar clicked; go home
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
