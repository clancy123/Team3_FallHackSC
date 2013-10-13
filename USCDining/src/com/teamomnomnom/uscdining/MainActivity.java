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
    private int nav_selected = 0;

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


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        PagerTabStrip strip = (PagerTabStrip) findViewById (R.id.pager_tab_strip);
        strip.setDrawFullUnderline(true);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        if (savedInstanceState == null) {
            selectItem(0);
        }
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
            String[] titles = new String[] { "Orange Chicken","Cheese Pizza", "Grilled Cheese", "Salad Bar", "Salad Bar", "Salad Bar", "Salad Bar", "Salad Bar" };
            args.putStringArray("frag_titles", titles);
            fragment.setArguments(args);
            Toast.makeText(MainActivity.this, "Frag Created " + position, Toast.LENGTH_SHORT).show();
            return fragment;
        }

        @Override
        public int getCount() {
            return 7; // One page for each weekday.
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

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
        mTitle = mDiningCategories[position];
        //mDrawerTitle = mPlanetTitles[position];
        mDrawerLayout.closeDrawer(mDrawerList);
        mViewPager.setCurrentItem(0);

        if (position != nav_selected) {
            nav_selected = position;
            //Toast.makeText(MainActivity.this, "Drawer " + position, Toast.LENGTH_SHORT).show();

            //TODO HALP mViewPager.invalidate();
            //mSectionsPagerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class FoodListFragment extends ListFragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        //public static String[] titles;

        public static final String[] descriptions = new String[] {
                "","Vegetarian", "Vegetarian", "Vegetarian" , "Vegan", "Vegetarian", "Vegetarian", "Vegetarian"};

        public static final Integer[] images = {
                R.drawable.orange_chicken, R.drawable.pizza, R.drawable.grilled_cheese, R.drawable.salad, R.drawable.salad, R.drawable.salad, R.drawable.salad, R.drawable.salad };

        ListView listView;
        List<RowItem> rowItems;
        String dining_hall = " is available";
        //static int frag_containerID;

        //*(&#$&()@#&$(SDJFLSDKJFLSDJKFLSDJFKLS:DF
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
                    view.setBackgroundColor(parent.getResources().getColor(COLORS[position % COLORS.length]));
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

        public FoodListFragment() {
        }

        private static ArrayList<Item> prepareItems() {
            ArrayList<Item> result = new ArrayList<Item>();
		/*for (int i = 0; i < 30; i++) {
			result.add(new Item(Item.SECTION, "Section " + i));
			for (int j=0; j<4; j++) {
				result.add(new Item(Item.ITEM, "Item " + j));
			}
		} */
            result.add(new Item(Item.SECTION, "Made to Order"));
            result.add(new Item(Item.ITEM, "Grilled Cheese"));
            result.add(new Item(Item.SECTION, "Entree"));
            result.add(new Item(Item.ITEM, "Chicken"));
            result.add(new Item(Item.ITEM, "Rice"));
            result.add(new Item(Item.ITEM, "Veggies"));
            result.add(new Item(Item.SECTION, "Salad Bar"));
            result.add(new Item(Item.ITEM, "Salad as usual..."));
            result.add(new Item(Item.SECTION, "Other"));
            result.add(new Item(Item.ITEM, "Pasta Bar"));
            result.add(new Item(Item.ITEM, "Smoothie of the day"));
            result.add(new Item(Item.ITEM, "More food"));
            result.add(new Item(Item.ITEM, "More food"));
            result.add(new Item(Item.ITEM, "More food"));
            result.add(new Item(Item.ITEM, "More food"));
            result.add(new Item(Item.ITEM, "More food"));

            return result;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            MyPinnedSectionListAdapter adapter = new MyPinnedSectionListAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, prepareItems());
            setListAdapter(adapter);

            //Bundle bundle = this.getArguments();

            //final String[] titles;
            //if (bundle != null) {
                /*dining_hall = bundle.getString(ARG_SECTION_NUMBER, "");
                final String[] titles = bundle.getStringArray("frag_titles");
           // }

            //frag_containerID = ((ViewGroup)getView().getParent()).getId();

            View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
            rowItems = new ArrayList<RowItem>();
            for (int i = 0; i < titles.length; i++) {
                RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
                rowItems.add(item);
            }

            listView = (ListView) rootView.findViewById(R.id.list);
            CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(),
                    R.layout.list_item, rowItems);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener( new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                        long arg3) {
                    //TODO take action. //selectItem(position);
                    //TODO convert to service...
                    // this
                    String ns = Context.NOTIFICATION_SERVICE;
                    NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(ns);

                    int icon = R.drawable.ic_stat_nom;
                    CharSequence tickerText = ""; // ticker-text
                    long when = System.currentTimeMillis();
                    Context context = getActivity().getApplicationContext();
                    CharSequence contentTitle = titles[position] + " is at " + dining_hall;
                    CharSequence contentText = "A food you're watching (" + titles[position] + ") is being served at " + dining_hall;
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
                    *///TODO mNotificationManager.notify(mId, mBuilder.build());
        //        }
         //   });

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
