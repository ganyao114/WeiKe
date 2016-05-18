package com.just.weike.ui.impl.activity;

import java.util.ArrayList;
import java.util.List;

import com.just.weike.R;
import com.just.weike.Dao.bean.NavigationItem;
import com.just.weike.test.ExampleUtil;
import com.just.weike.ui.IOnLoginedCallBack;
import com.just.weike.ui.MyItemClickListener;
import com.just.weike.ui.MyItemLongClickListener;
import com.just.weike.ui.NavigationDrawerCallbacks;
import com.just.weike.ui.impl.SetLoginDialog;
import com.just.weike.ui.impl.fragment.BookListFragment;
import com.just.weike.ui.impl.fragment.ClassifyFragment;
import com.just.weike.ui.impl.fragment.NavigationDrawerFragment;
import com.just.weike.ui.impl.fragment.NotifyFragment;
import com.just.weike.ui.impl.fragment.SettingFragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends ActionBarActivity implements
NavigationDrawerCallbacks, MyItemClickListener,
MyItemLongClickListener,OnCheckedChangeListener,IOnLoginedCallBack{
	
	private Toolbar mToolbar;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private FragmentManager fragmentmanager;
	private RadioGroup group;
	private RadioButton main_home;
	private List<NavigationItem> navigationlists;
	
	public static boolean isForeground = false;
	
	 @SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_topdrawer);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		fragmentmanager = getFragmentManager();
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_drawer);
		mNavigationDrawerFragment.setup(R.id.fragment_drawer,
				(DrawerLayout) findViewById(R.id.drawer), mToolbar);
		group = (RadioGroup) findViewById(R.id.main_bottom_tags);
		group.setOnCheckedChangeListener(this);
		main_home = (RadioButton) findViewById(R.id.homerb);
		main_home.setChecked(true);
		initNavigation();
		//registerMessageReceiver();
		//JPushInterface.init(getApplicationContext());
	}
	 
	private void initNavigation()
	{
		navigationlists = new ArrayList<NavigationItem>();
		Resources resources = getResources();
		navigationlists.add(new NavigationItem("阅读历史", resources.getDrawable(R.drawable.history)));
		navigationlists.add(new NavigationItem("我的收藏", resources.getDrawable(R.drawable.save80)));
		navigationlists.add(new NavigationItem("我的下载", resources.getDrawable(R.drawable.download80)));
		navigationlists.add(new NavigationItem("我的笔记", resources.getDrawable(R.drawable.note)));
		mNavigationDrawerFragment.RefreshList(navigationlists);
	}
	
	

	@Override
	public void onLongItemClick(View view, int postion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(View view, int postion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		
	}
	
	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		//actionBar.setTitle(mTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_login) {
			new SetLoginDialog(this, this);
			return true;
		}else if(id == R.id.menu_setting){
			return true;
		} 
		return super.onOptionsItemSelected(item); 
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.homerb:
			changeFragment(new BookListFragment(), true);
			break;
		case R.id.classifyrb:
			changeFragment(new ClassifyFragment(), true);
			break;
		case R.id.messagerb:			
			changeFragment(new NotifyFragment(), true);
			break;
		case R.id.settingrb:
			changeFragment(new SettingFragment(), true);
			break;

		default:
			break;
		}
	}

	private void changeFragment(Fragment fragment, boolean isInit) {
		// 开启事务
		FragmentTransaction transaction = fragmentmanager.beginTransaction();
		transaction.replace(R.id.main_content, fragment);
		if (!isInit) {
			transaction.addToBackStack(null);
		}
		transaction.commit();
	}
	
	





	@Override
	public void OnLogined() {
		// TODO Auto-generated method stub
		changeFragment(new BookListFragment(), true);
	}





	@Override
	public void OnClickRegist() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, ChoosePositionActivity.class);
		startActivity(intent);
	}
	
	//for receive customer msg from jpush server
	private MessageReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "com.just.weike.MESSAGE_RECEIVED_ACTION";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";
	
	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
              String messge = intent.getStringExtra(KEY_MESSAGE);
              String extras = intent.getStringExtra(KEY_EXTRAS);
              StringBuilder showMsg = new StringBuilder();
              showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
              if (!ExampleUtil.isEmpty(extras)) {
            	  showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
              }
             
			}
		}
	}
	public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}
	
}
