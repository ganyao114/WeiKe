package com.just.weike.ui.impl.activity;

import java.util.List;

import com.gy.widget.fragment.ui.IndicatorFragmentActivity;
import com.just.weike.test.FragmentOne;
import com.just.weike.test.FragmentThree;
import com.just.weike.test.FragmentTwo;

public class IMActivity extends IndicatorFragmentActivity {
	public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;
	@Override
	protected int supplyTabs(List<TabInfo> tabs) {
		 tabs.add(new TabInfo(FRAGMENT_ONE, "会话",
	                FragmentOne.class));
	        tabs.add(new TabInfo(FRAGMENT_TWO, "好友",
	                FragmentTwo.class));
	        tabs.add(new TabInfo(FRAGMENT_THREE, "群组",
	                FragmentThree.class));

	        return FRAGMENT_TWO;
	}

}
