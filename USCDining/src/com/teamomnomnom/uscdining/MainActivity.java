package com.teamomnomnom.uscdining;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.app.SearchManager;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.widget.SearchView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.app.Notification;
import android.support.v4.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.app.PendingIntent;
import android.app.NotificationManager;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hb.views.PinnedSectionListView.PinnedSectionListAdapter;
import java.util.Calendar;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//import com.hb.views.PinnedSectionListView.PinnedSectionListAdapter;

public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDiningCategories;
    private int nav_selected = 3;
    
    private DiningData[] parkside;
    private DiningData[] evk;
    private DiningData[] cafe84;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ab_texture_tile);
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bMap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        getActionBar().setBackgroundDrawable(bitmapDrawable);

        mTitle = mDrawerTitle = getTitle();
        mDiningCategories = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mDiningCategories));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        parkside = new DiningData[7];
        evk = new DiningData[7];
        cafe84 = new DiningData[7];
        //TODO start actual data
        for (int i = 0; i < 7; i++) {
        	parkside[i] = new DiningData();
            parkside[i].dining_hall = "Parkside";
            parkside[i].breakfast = new String[] { "Made to Order:", "Omelette Bar", "Entree:", "Scrambled Eggs", "Hard Boiled Eggs", "French Toast", "Waffle Bar", "Yogurt Parfait Bar", "Oatmeal", "Hash Browns", "Veggie Patties", "Smoothie of the Day", "Pizza:", "Pepperoni Pizza", "Roasted Vegetable Pizza", "Grill:", "Cheeseburger", "Chicken Apple Sausage", "Steak Fries", "Other:", "Salad Bar", "Pasta Bar", "Smoothie of the Day", "Beef and Tomato Soup", "Garden Vegetable Soup", "Deli Bar", "Chicken Ratatouille", "German Potato Salad" };
            parkside[i].dinner = new String[] { "Made to Order:", "Po Boy Sandwich Bar", "Entree:", "Rosemary Pork Chop", "Blackened Catfish", "Glazed Tempeh", "Dirty Rice", "Roasted Carrots", "Garlic Okra", "Pizza:", "Pepperoni Pizza", "Roasted Vegetable Pizza", "Grill:", "Cheeseburger", "Chicken Apple Sausage", "Steak Fries", "Other:", "Salad Bar", "Pasta Bar", "Smoothie of the Day", "Beef and Tomato Soup", "Garden Vegetable Soup", "Deli Bar", "Chicken Ratatouille", "German Potato Salad" };
            evk[i] = new DiningData();
            evk[i].dining_hall = "EVK";
            evk[i].breakfast = new String[] { "Made to Order:", "Omelette Bar", "Entree:", "Scrambled Eggs", "Hard Boiled Eggs", "French Toast", "Waffle Bar", "Yogurt Parfait Bar", "Oatmeal", "Hash Browns", "Veggie Patties", "Smoothie of the Day", "Pizza:", "Pepperoni Pizza", "Roasted Vegetable Pizza", "Grill:", "Cheeseburger", "Chicken Apple Sausage", "Steak Fries", "Other:", "Salad Bar", "Pasta Bar", "Smoothie of the Day", "Beef and Tomato Soup", "Garden Vegetable Soup", "Deli Bar", "Chicken Ratatouille", "German Potato Salad" };
            evk[i].dinner = new String[] { "Made to Order:", "Po Boy Sandwich Bar", "Entree:", "Rosemary Pork Chop", "Blackened Catfish", "Glazed Tempeh", "Dirty Rice", "Roasted Carrots", "Garlic Okra", "Pizza:", "Pepperoni Pizza", "Roasted Vegetable Pizza", "Grill:", "Cheeseburger", "Chicken Apple Sausage", "Steak Fries", "Other:", "Salad Bar", "Pasta Bar", "Smoothie of the Day", "Beef and Tomato Soup", "Garden Vegetable Soup", "Deli Bar", "Chicken Ratatouille", "German Potato Salad" };
            cafe84[i] = new DiningData();
            cafe84[i].dining_hall = "Cafe 84";
            cafe84[i].breakfast = new String[] { "Made to Order:", "Omelette Bar", "Entree:", "Scrambled Eggs", "Hard Boiled Eggs", "French Toast", "Waffle Bar", "Yogurt Parfait Bar", "Oatmeal", "Hash Browns", "Veggie Patties", "Smoothie of the Day", "Pizza:", "Pepperoni Pizza", "Roasted Vegetable Pizza", "Grill:", "Cheeseburger", "Chicken Apple Sausage", "Steak Fries", "Other:", "Salad Bar", "Pasta Bar", "Smoothie of the Day", "Beef and Tomato Soup", "Garden Vegetable Soup", "Deli Bar", "Chicken Ratatouille", "German Potato Salad" };
            cafe84[i].dinner = new String[] { "Made to Order:", "Po Boy Sandwich Bar", "Entree:", "Rosemary Pork Chop", "Blackened Catfish", "Glazed Tempeh", "Dirty Rice", "Roasted Carrots", "Garlic Okra", "Pizza:", "Pepperoni Pizza", "Roasted Vegetable Pizza", "Grill:", "Cheeseburger", "Chicken Apple Sausage", "Steak Fries", "Other:", "Salad Bar", "Pasta Bar", "Smoothie of the Day", "Beef and Tomato Soup", "Garden Vegetable Soup", "Deli Bar", "Chicken Ratatouille", "German Potato Salad" };
            
        }
        
        parkside[1].breakfast = new String[] {"Made to Order:", "Omelet Bar", "Entree:", "Scrambled Eggs", "Hard Boiled Eggs", "Waffle Bar", "Yogurt Parfait Bar", "Oatmeal", "Sausage and Cheddar Frittata", "Country Diced Potatoes", "Pork Sausage Patty", "Veggie Patties", "Smoothie of the Day"};
        parkside[1].dinner = new String[] { "Made to Order:", "Fajita Bowl Bar", "Entree:", "Chile Rubbed Beef", "Tortilla Crusted Chipotle Lime Tilapia", "Rice Stuffed Peppers with Chipotle Remoulade", "Mexican Millet", "Steamed Spinach", "SautŽed Squash Blend", "Pizza:", "Meatlover's Pizza", "Peppers & Mushroom Pizza", "Customize To Make:", "Pasta/Grain/BeanBar", "Customize Salad Bar to Make:", "Salad Bar", "Customize Sandwiches to Make:", "Deli Bar", "Market Salads:", "Fruit Salad", "Black Bean & Corn Salad", "Gluten Awareness Station:", "Snapper Veracruz", "Jicama, Mango & Cucumber Salad with Chile", "Deli Bar", "Soup:", "Chicken Tortilla", "Garden Vegetable Homemade", "Smoothie:", "Smoothie of the Day", "Grill:", "Hot Dog", "Guacamole Chicken Sandwich", "Boca Burger", "Potato Chips"};
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        PagerTabStrip strip = (PagerTabStrip) findViewById (R.id.pager_tab_strip);
        strip.setDrawFullUnderline(true);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        setTitle("Parkside"); //TODO
        
        if (savedInstanceState == null) {
            selectItem(3);
        }
        
        //TODO actual notifications
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(ns);

        int icon = R.drawable.ic_stat_nom;
        CharSequence tickerText = "Food item available"; // ticker-text
        long when = System.currentTimeMillis();
        Context context = this.getApplicationContext();
        CharSequence contentTitle = "Fajitas at Parkside"; //TODO lunch!
        CharSequence contentText = "Tomorrow for Lunch and Dinner.";
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new Notification(icon, tickerText, when);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        int HELLO_ID = 1;
        mNotificationManager.notify(HELLO_ID, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    
    

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a FoodListFragment (defined as a static inner class
            // below) with the page number as its lone argument.
            Fragment fragment = new FoodListFragment();
            Bundle args = new Bundle();
            args.putString(FoodListFragment.ARG_SECTION_NUMBER, (mDiningCategories[1 + nav_selected])); //TODO overview case
            switch(nav_selected) {
            	case 0: //overview. swap below for now to check changing
            		args.putStringArray(FoodListFragment.ARG_FOOD_BREAKFAST, evk[position].dinner);
                    args.putStringArray(FoodListFragment.ARG_FOOD_DINNER, evk[position].breakfast);
            	case 1: 
            		args.putStringArray(FoodListFragment.ARG_FOOD_BREAKFAST, evk[position].breakfast);
                    args.putStringArray(FoodListFragment.ARG_FOOD_DINNER, evk[position].dinner);
                    break;
            	case 2: 
            		args.putStringArray(FoodListFragment.ARG_FOOD_BREAKFAST, cafe84[position].breakfast);
                    args.putStringArray(FoodListFragment.ARG_FOOD_DINNER, cafe84[position].dinner);
                    break;
            	case 3: 
            		args.putStringArray(FoodListFragment.ARG_FOOD_BREAKFAST, parkside[position].breakfast);
                    args.putStringArray(FoodListFragment.ARG_FOOD_DINNER, parkside[position].dinner);
                    break;
            }

            fragment.setArguments(args);
            //Toast.makeText(MainActivity.this, "Frag Created " + position, Toast.LENGTH_SHORT).show();
            return fragment;
        }

        @Override
        public int getCount() {
            return 7; // One page for each weekday.
        }
        
        public int getItemPosition(Object object) {
            return POSITION_NONE;
       }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                default:
                    return getDayTitle(position);
            }
        }
    }

    String getDayTitle(int pos) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK); // Sunday == 1
        // Adjust titles based on day
        day = (day + pos)%7;

        //current day goes to pos 1
        switch(day) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 0:
                return "Saturday";
        }
        return "error...";
    }
    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        /*/ Create a new fragment and specify the planet to show based on position
        Fragment fragment = new FoodListFragment();
        Bundle args = new Bundle();
        args.putInt(FoodListFragment.ARG_SECTION_NUMBER, nav_selected);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragframe, fragment)
                .commit();

        fragmentManager.executePendingTransactions();
        */
    	
    	if (position == 4) {
    		Intent intent = new Intent(MainActivity.this, Favorites.class);
    		intent.putExtra(Favorites.EXTRA_SHOW_FRAGMENT, Favorites.CombinedPreferenceFragment.class.getName() );
            intent.putExtra(Favorites.EXTRA_NO_HEADERS, true );
            //TODO tablets / headers...
    		startActivity(intent);
    		return;
    	}
    	
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
        mTitle = mDiningCategories[position];
        //mDrawerTitle = mPlanetTitles[position];
        mDrawerLayout.closeDrawer(mDrawerList);
        mViewPager.setCurrentItem(0);

        //if (position != nav_selected) {
            nav_selected = position;
            //Toast.makeText(MainActivity.this, "Drawer " + position, Toast.LENGTH_SHORT).show();

            mViewPager.invalidate();
            mSectionsPagerAdapter.notifyDataSetChanged();
        //}
    }

    /**
     * A fragment representing a food list
     */
    public static class FoodListFragment extends ListFragment {
        
        private static class MyPinnedSectionListAdapter extends ArrayAdapter<Item> implements PinnedSectionListAdapter {

            private static final int[] COLORS = new int[] {
                    android.R.color.holo_green_light, android.R.color.holo_orange_light,
                    android.R.color.holo_blue_light, android.R.color.holo_red_light };

            public MyPinnedSectionListAdapter(Context context, int resource, int textViewResourceId, List<Item> objects) {
                super(context, resource, textViewResourceId, objects);
            }

            @Override public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextColor(Color.DKGRAY);
                if (getItem(position).type == Item.SECTION) {
                	if (getItem(position).text.equals("Breakfast") || getItem(position).text.equals("Lunch & Dinner")) {
                		view.setBackgroundColor(parent.getResources().getColor(COLORS[3]));
                	} 
                	else view.setBackgroundColor(parent.getResources().getColor(COLORS[position % (COLORS.length - 1)]));
                }
                return view;
            }

            @Override public int getViewTypeCount() {
                return 2;
            }

            @Override public int getItemViewType(int position) {
                return getItem(position).type;
            }

            @Override public boolean isItemViewTypePinned(int viewType) {
                return viewType == Item.SECTION;
            }
        }

        private static class Item {
            public static final int ITEM = 0;
            public static final int SECTION = 1;

            public final int type;
            public final String text;

            public Item(int type, String text) {
                this.type = type;
                this.text = text;
            }

            @Override public String toString() {
                return text;
            }
        }

        public static final String ARG_FOOD_BREAKFAST = "breakfast_menu";
        public static final String ARG_FOOD_DINNER = "dinner_menu";
        public static final String ARG_SECTION_NUMBER = "dining_hall";
        
        ListView listView;
        List<RowItem> rowItems;
        String dining_hall = "";
        
        public FoodListFragment() {
        }

        private static ArrayList<Item> prepareItems(String[] breakfast, String[] dinner) {
            ArrayList<Item> result = new ArrayList<Item>();
            result.add(new Item(Item.SECTION, "Breakfast"));
            for (int i = 0; i < breakfast.length; i++) {
            	if (breakfast[i].equals("Made to Order:") || breakfast[i].equals("Entree:") || breakfast[i].equals("Pizza:") || breakfast[i].equals("Grill:") || breakfast[i].equals("Other:") ) {
            		result.add(new Item(Item.SECTION, breakfast[i]));
            		continue;
            	}
            	else {
            		result.add(new Item(Item.ITEM, breakfast[i]));
            	}
			}
            result.add(new Item(Item.SECTION, "Lunch & Dinner"));
            for (int i = 0; i < dinner.length; i++) {
            	if (dinner[i].equals("Made to Order:") || dinner[i].equals("Entree:") || dinner[i].equals("Pizza:") || dinner[i].equals("Grill:") || dinner[i].equals("Other:") ) {
            		result.add(new Item(Item.SECTION, dinner[i]));
            		continue;
            	}
            	else {
            		result.add(new Item(Item.ITEM, dinner[i]));
            	}
			}

            return result;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

        	Bundle bundle = this.getArguments();
        	if (bundle == null) {
                Toast.makeText(getActivity(), "HELP NULL", Toast.LENGTH_LONG).show();
        	}
            dining_hall = bundle.getString(ARG_SECTION_NUMBER, "");
            final String[] breakfast_food = bundle.getStringArray(ARG_FOOD_BREAKFAST);
            final String[] dinner_food = bundle.getStringArray(ARG_FOOD_DINNER);
        	
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            MyPinnedSectionListAdapter adapter = new MyPinnedSectionListAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, prepareItems(breakfast_food, dinner_food));
            setListAdapter(adapter);

            //frag_containerID = ((ViewGroup)getView().getParent()).getId();

            //View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
            
            // listView = (ListView) rootView.findViewById(android.R.id.list);
            /*listView.setOnItemClickListener( new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                        long arg3) {
                    //TODO take action. //selectItem(position);
                    //TODO convert to service...
                    // this
                    String ns = Context.NOTIFICATION_SERVICE;
                    NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(ns);

                    int icon = R.drawable.ic_stat_nom;
                    CharSequence tickerText = "Food item available"; // ticker-text
                    long when = System.currentTimeMillis();
                    Context context = getActivity().getApplicationContext();
                    CharSequence contentTitle = breakfast_food[position%breakfast_food.length] + " is at " + dining_hall; //TODO lunch!
                    CharSequence contentText = "A food you're watching (" + breakfast_food[position%breakfast_food.length] + ") is being served at " + dining_hall;
                    Intent notificationIntent = new Intent(getActivity(), MainActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent, 0);
                    Notification notification = new Notification(icon, tickerText, when);
                    notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
                    int HELLO_ID = 1;
                    mNotificationManager.notify(HELLO_ID, notification);
                    /*NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getActivity())
                                    //.setSmallIcon(R.drawable.notification_icon)
                                    .setContentTitle("My notification")
                                    .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
                    Intent resultIntent = new Intent(getActivity(), MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
// Adds the back stack for the Intent (but not the Intent itself)
                    stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
                    ///TODO mNotificationManager.notify(mId, mBuilder.build());
                }
            }); */

            return rootView;
        }

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position); //TODO take action
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
            /*case R.id.action_websearch:
                // create intent to perform web search for this planet
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true; */
        	case R.id.action_about:
        		Intent intent = new Intent(MainActivity.this, About.class);
        		startActivity(intent);
        		return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
