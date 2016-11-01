package com.gy.just.VoltageMonitor.View.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.gy.just.VoltageMonitor.Control.Presenter.LarTabPresenter;
import com.gy.just.VoltageMonitor.Control.Utils.TimeUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowKindsEvent;
import com.gy.just.VoltageMonitor.EventFlags.ShowT02Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT04Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT05Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT06Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT08Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT10Event;
import com.gy.just.VoltageMonitor.EventFlags.ShowT12Event;
import com.gy.just.VoltageMonitor.Model.Bean.KindsCardPojo;
import com.gy.just.VoltageMonitor.Model.Bean.LarTabBean.T02Bean;
import com.gy.just.VoltageMonitor.Model.Bean.LarTabBean.T04Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T05Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T06Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T08Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T10Bean;
import com.gy.just.VoltageMonitor.Model.Bean.T12Bean;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetKindsModel;
import com.gy.just.VoltageMonitor.Model.Net.GetT02Model;
import com.gy.just.VoltageMonitor.Model.Net.GetT04Model;
import com.gy.just.VoltageMonitor.Model.Net.GetT05Model;
import com.gy.just.VoltageMonitor.Model.Net.GetT06Model;
import com.gy.just.VoltageMonitor.Model.Net.GetT08Model;
import com.gy.just.VoltageMonitor.Model.Net.GetT10Model;
import com.gy.just.VoltageMonitor.Model.Net.GetT12Model;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Adapter.BaseMultiHostTabAdapter;
import com.gy.just.VoltageMonitor.View.Adapter.T02Adapter;
import com.gy.just.VoltageMonitor.View.Adapter.T04Adapter;
import com.gy.just.VoltageMonitor.View.Adapter.T05Adapter;
import com.gy.just.VoltageMonitor.View.Adapter.T06Adapter;
import com.gy.just.VoltageMonitor.View.Adapter.T08Adapter;
import com.gy.just.VoltageMonitor.View.Adapter.T10Adapter;
import com.gy.just.VoltageMonitor.View.Adapter.T12Adapter;
import com.gy.myframework.IOC.Mvp.annotation.InjectPresenter;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.IOC.UI.view.viewbinder.impl.ListBinder;
import com.gy.myframework.MVP.View.context.activity.BaseAppCompactActivity;
import com.gy.myframework.UI.customwidget.calendar2.CalendarView;
import com.gy.myframework.UI.customwidget.floatactionbt.FloatingActionButton;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@InjectPresenter(LarTabPresenter.class)
public class LarTabListActivity extends BaseAppCompactActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private RelativeLayout mHead;
    private MaterialDialog choosePagedialog,chooseDateDialog,filterDialog;
    private FloatingActionButton dateBt,pageBt,filterBt,pagepre,pagenext;

    private String title;
    private List list;
    private BaseMultiHostTabAdapter adapter;

    private @IdRes int contentViewId;
    private @IdRes int headId;
    private @IdRes int toolBarId;
    private @IdRes int listViewId;
    private @IdRes int headSrcrollViewId;
    private @IdRes int dataBtId;
    private @IdRes int pageBtId;
    private @IdRes int filterBtId;
    private @IdRes int pagepreId = R.id.action_pre_page;
    private @IdRes int pagenextId = R.id.action_next_page;

    private AdapterView.OnItemClickListener listener;
    private Date curData = Global.defaultData;

    private int tabid;
    private String pt_id = "0";
    private ListView kindslist;
    private List<KindsCardPojo> kinds;


    //T05相关
    private String t_from;
    private String t_to;
    private List<T05Bean> t05lsit;
    private MaterialDialog fromantoDialog;
    //T06




    //换页相关
    private int pageid = 0;
    private int pagestart = 0;
    private int pageend = 30;
    private int pageLimit = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventPoster.Regist(this);
        super.onCreate(savedInstanceState);
        tabid = getIntent().getIntExtra("tabid",0);
        if (tabid == 2){
            pt_id = getIntent().getStringExtra("pt_id");
            showT02();
        }else if (tabid == 4){
            showT04();
        }else if (tabid == 5||tabid == 6||tabid == 8||tabid == 10||tabid == 12){
            setFormToDia();
        }
    }

    private void changPage(int action,int id){
        if (action!=0){
            if (action == 1){
                pageid++;

            }else if (action == -1){
                if (pageid < 1)
                    return;
                pageid--;
            }
        }else {
            if (id < 0)
                return;
            pageid = id;
        }
        pagestart = pageid*pageLimit;
        pageend = pagestart + pageLimit;
        switch (tabid){
            case 2:
                getT02();
                break;
            case 4:
                getT04();
                break;
            case 6:
                getT06();
                break;
            case 8:
                getT08();
                break;
            case 10:
                getT10();
                break;
            case 12:
                getT12();
                break;
            default:
                return;
        }
        pageBt.setTitle("第"+(pageid+1)+"页");
    }

    private void Show(){
        intView();
        init();
    }

    private void intView(){
        setContentView(contentViewId);
        Toolbar toolbar = (Toolbar) findViewById(toolBarId);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(listViewId);
        mHead = (RelativeLayout)findViewById(headId);
        dateBt = (FloatingActionButton) findViewById(dataBtId);
        pageBt = (FloatingActionButton) findViewById(pageBtId);
        filterBt = (FloatingActionButton) findViewById(filterBtId);
        pagepre = (FloatingActionButton) findViewById(pagepreId);
        pagenext = (FloatingActionButton) findViewById(pagenextId);
        if (tabid != 2&&tabid != 4){
            filterBt.setVisibility(View.GONE);
        }
    }

    private void init(){
        mHead.setFocusable(true);
        mHead.setClickable(true);
        mHead.setBackgroundColor(Color.parseColor("#696969"));
        mHead.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
        listView.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
        listView.setOnItemClickListener(listener);
        dateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabid == 2 || tabid == 4){
                    selDateDia();
                }else {
                    setFormToDia();
                }
            }
        });
        pageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePageDia();
            }
        });
        filterBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilterDialog();
            }
        });
        pagepre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changPage(-1, 0);
            }
        });
        pagenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changPage(1,0);
            }
        });
    }

    private void choosePageDia(){
        if (choosePagedialog == null){
            choosePagedialog = new MaterialDialog(this);
            final AppCompatAutoCompleteTextView editText = new AppCompatAutoCompleteTextView(this);
            editText.setHint("输入页码");
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            choosePagedialog.setTitle("页码跳转");
            choosePagedialog.setContentView(editText);
            choosePagedialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changPage(0, Integer.parseInt(editText.getText().toString())-1);
//                    Intent intent = new Intent();
//                    intent.setClass(LarTabListActivity.this,DetaillInfoActivity.class);
//                    intent.putExtra("meterid",meterId);
//                    startActivity(intent);
                    choosePagedialog.dismiss();
                }
            });
            choosePagedialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choosePagedialog.dismiss();
                }
            });
        }
        choosePagedialog.show();
    }

    private void selDateDia(){
        if (chooseDateDialog == null) {
            chooseDateDialog = new MaterialDialog(LarTabListActivity.this);
            final CalendarView calendarView = new CalendarView(LarTabListActivity.this);
            calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
                @Override
                public void onDateSelected(Date date) {
                    curData = date;
                }

                @Override
                public void onDateUnselected(Date date) {
                }
            });
            chooseDateDialog.setTitle("选择日期");
            chooseDateDialog.setContentView(calendarView);
            chooseDateDialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chooseDateDialog.dismiss();
                }
            });
            chooseDateDialog.setPositiveButton("确定选择", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tabid == 2) {
                        getT02();
                    } else if (tabid == 4) {
                        getT04();
                    }
                    chooseDateDialog.dismiss();
                }
            });
        }
        chooseDateDialog.show();
    }

    private void setFilterDialog(){
        if (filterDialog == null){
            filterDialog = new MaterialDialog(this);
            kindslist = new ListView(this);
            filterDialog.setTitle("根据种类筛选");
            filterDialog.setContentView(kindslist);
            filterDialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tabid == 2) {
                        getT02();
                    } else if (tabid == 4) {
                        getT04();
                    }
                    filterDialog.dismiss();
                }
            });
            filterDialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filterDialog.dismiss();
                }
            });
        }
        filterDialog.show();
        getFilterKinds();
    }

    private void setFormToDia(){
        if (fromantoDialog == null) {
            fromantoDialog = new MaterialDialog(LarTabListActivity.this);
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            final AppCompatAutoCompleteTextView editText1 = new AppCompatAutoCompleteTextView(this);
            editText1.setHint("输入开始日期");
            editText1.setInputType(InputType.TYPE_CLASS_NUMBER);
            final AppCompatAutoCompleteTextView editText2 = new AppCompatAutoCompleteTextView(this);
            editText2.setHint("输入结束日期");
            editText2.setInputType(InputType.TYPE_CLASS_NUMBER);
            layout.addView(editText1);
            layout.addView(editText2);
            fromantoDialog.setTitle("选择日期");
            fromantoDialog.setContentView(layout);
            setPreDate(editText1,editText2);
            fromantoDialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fromantoDialog.dismiss();
                    finish();
                }
            });
            fromantoDialog.setPositiveButton("确定选择", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t_from = editText1.getText().toString();
                    t_to = editText2.getText().toString();
                    if (tabid == 5)
                        showT05();
                    else if (tabid == 6)
                        showT06();
                    else if (tabid == 8)
                        showT08();
                    else if (tabid == 10)
                        showT10();
                    else if (tabid == 12)
                        showT12();
                    fromantoDialog.dismiss();
                }
            });
        }
        fromantoDialog.show();
    }

    private void setPreDate(AppCompatAutoCompleteTextView editText1, AppCompatAutoCompleteTextView editText2) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        String year = calendar.get(Calendar.YEAR) + "";
        String month = generatorStr(calendar.get(Calendar.MONTH) + 1);
        String day = generatorStr(calendar.get(Calendar.DAY_OF_MONTH));
        editText1.setText(year+month+"01");
        editText2.setText(year+month+day);
    }

    private String generatorStr(int no){
        if (no > 9){
            return no+"";
        }else {
            return "0" + no;
        }
    }

    private void showT02(){
        contentViewId = R.layout.activity_lar_tab_list;
        title = "大图表数据";
        headSrcrollViewId = R.id.horizontalScrollView1;
        headId = R.id.list_head;
        listViewId = R.id.mutihost_list;
        toolBarId = R.id.toolbar;
        pageBtId = R.id.action_sel_page;
        dataBtId = R.id.action_sel_date;
        filterBtId = R.id.action_filter;
        listener = this;
        Show();
        getT02();
    }

    private void showT04(){
        contentViewId = R.layout.activity_month_tab_list;
        title = "大图表数据";
        headSrcrollViewId = R.id.horizontalScrollView1;
        headId = R.id.list_head;
        listViewId = R.id.mutihost_list;
        toolBarId = R.id.toolbar;
        pageBtId = R.id.action_sel_page;
        dataBtId = R.id.action_sel_date;
        filterBtId = R.id.action_filter;
        listener = this;
        Show();
        getT04();
    }

    private void showT05(){
        contentViewId = R.layout.activity_lar_tab05_list;
        title = "大图表数据";
        headSrcrollViewId = R.id.horizontalScrollView1;
        headId = R.id.list_head;
        listViewId = R.id.mutihost_list;
        toolBarId = R.id.toolbar;
        pageBtId = R.id.action_sel_page;
        dataBtId = R.id.action_sel_date;
        filterBtId = R.id.action_filter;
        listener = this;
        Show();
        getView(R.id.multiple_actions).setVisibility(View.GONE);
        getT05();
    }

    private void showT06(){
        contentViewId = R.layout.activity_lar_tab6_list;
        title = "大图表数据";
        headSrcrollViewId = R.id.horizontalScrollView1;
        headId = R.id.list_head;
        listViewId = R.id.mutihost_list;
        toolBarId = R.id.toolbar;
        pageBtId = R.id.action_sel_page;
        dataBtId = R.id.action_sel_date;
        filterBtId = R.id.action_filter;
        listener = this;
        Show();
        getT06();
    }

    private void showT10(){
        contentViewId = R.layout.activity_lar_tab10_list;
        title = "大图表数据";
        headSrcrollViewId = R.id.horizontalScrollView1;
        headId = R.id.list_head;
        listViewId = R.id.mutihost_list;
        toolBarId = R.id.toolbar;
        pageBtId = R.id.action_sel_page;
        dataBtId = R.id.action_sel_date;
        filterBtId = R.id.action_filter;
        listener = this;
        Show();
        getT10();
    }

    private void showT08(){
        contentViewId = R.layout.activity_lar_tab08_list;
        title = "大图表数据";
        headSrcrollViewId = R.id.horizontalScrollView1;
        headId = R.id.list_head;
        listViewId = R.id.mutihost_list;
        toolBarId = R.id.toolbar;
        pageBtId = R.id.action_sel_page;
        dataBtId = R.id.action_sel_date;
        filterBtId = R.id.action_filter;
        listener = this;
        Show();
        getT08();
    }

    private void showT12(){
        contentViewId = R.layout.activity_lar_tab12_list;
        title = "大图表数据";
        headSrcrollViewId = R.id.horizontalScrollView1;
        headId = R.id.list_head;
        listViewId = R.id.mutihost_list;
        toolBarId = R.id.toolbar;
        pageBtId = R.id.action_sel_page;
        dataBtId = R.id.action_sel_date;
        filterBtId = R.id.action_filter;
        listener = this;
        Show();
        getT12();
    }

    private void getT12() {
        GetT12Model model = new GetT12Model(t_from,t_to,pagestart+"",pageLimit+"");
        model.doHttp();
    }

    private void getT08() {
        GetT08Model model = new GetT08Model(t_from,t_to,pagestart+"",pageLimit+"");
        model.doHttp();
    }

    private void getT10() {
        GetT10Model model = new GetT10Model(t_from,t_to,pagestart+"",pageLimit+"");
        model.doHttp();
    }

    private void getT06() {
        GetT06Model model = new GetT06Model(null,t_from,t_to,pagestart+"",pageLimit+"");
        model.doHttp();
    }

    private void getT05(){
        GetT05Model model = new GetT05Model(t_from,t_to);
        model.doHttp();
    }

    private void getT04() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curData);
        GetT04Model model = new GetT04Model(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1, pt_id,pagestart+"",pageLimit+"");
        model.doHttp();
    }

    private void getT02() {
        GetT02Model model = new GetT02Model(TimeUtils.getDateStr(curData),pt_id,pagestart+"",pageLimit+"");
        model.doHttp();
    }

    private void getFilterKinds(){
        GetKindsModel model = new GetKindsModel();
        model.doHttp();
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setTab02(ShowT02Event event){
        list = event.getList();
        setTitle();
        BaseMultiHostTabAdapter adapter = new T02Adapter(list);
        adapter.init(mHead,headSrcrollViewId);
        ListBinder.With(listView).setClass(T02Bean.class).setCallBack(adapter).bind(list);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setTab04(ShowT04Event event){
        list = event.getList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curData);
        setTitle(cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月");
        BaseMultiHostTabAdapter adapter = new T04Adapter(list);
        adapter.init(mHead,headSrcrollViewId);
        ListBinder.With(listView).setClass(T04Bean.class).setCallBack(adapter).bind(list);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setFilter(ShowKindsEvent event){
        kinds = event.getList();
        ListBinder.With(kindslist).setClass(KindsCardPojo.class).bind(kinds);
        filterDialog.setListViewHeightBasedOnChildren(kindslist);
        kindslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pt_id = kinds.get(position).getPt_id();
            }
        });
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setT05(ShowT05Event event){
        list = event.getList();
        setTitle(t_from + "-" + t_to);
        BaseMultiHostTabAdapter adapter = new T05Adapter(list);
        adapter.init(mHead, headSrcrollViewId);
        ListBinder.With(listView).setClass(T05Bean.class).setCallBack(adapter).bind(list);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setT06(ShowT06Event event){
        list = event.getList();
        setTitle(t_from+"-"+t_to);
        BaseMultiHostTabAdapter adapter = new T06Adapter(list);
        adapter.init(mHead,headSrcrollViewId);
        ListBinder.With(listView).setClass(T06Bean.class).setCallBack(adapter).bind(list);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setT08(ShowT08Event event){
        list = event.getList();
        setTitle(t_from+"-"+t_to);
        BaseMultiHostTabAdapter adapter = new T08Adapter(list);
        adapter.init(mHead,headSrcrollViewId);
        ListBinder.With(listView).setClass(T08Bean.class).setCallBack(adapter).bind(list);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setT10(ShowT10Event event){
        list = event.getList();
        setTitle(t_from+"-"+t_to);
        BaseMultiHostTabAdapter adapter = new T10Adapter(list);
        adapter.init(mHead,headSrcrollViewId);
        ListBinder.With(listView).setClass(T10Bean.class).setCallBack(adapter).bind(list);
    }

    @InjectEvent(EventThreadType.MainThread)
    public void setT12(ShowT12Event event){
        list = event.getList();
        setTitle(t_from+"-"+t_to);
        BaseMultiHostTabAdapter adapter = new T12Adapter(list);
        adapter.init(mHead,headSrcrollViewId);
        ListBinder.With(listView).setClass(T12Bean.class).setCallBack(adapter).bind(list);
    }

    private void setTitle(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(curData);
        setTitle(cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DAY_OF_MONTH)+"日");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, MeterInfoActivity.class);
        if (tabid == 4) {
            intent.putExtra("meterid", ((T04Bean) list.get(position)).getMeterid());
        }
        else if (tabid == 2) {
            intent.putExtra("meterid", ((T02Bean) list.get(position)).getMeterid());
        }else if (tabid == 6){
            Intent intent1 = new Intent();
            intent1.setClass(this,SubLartabActivity.class);
            intent1.putExtra("meterid", ((T06Bean) list.get(position)).getMeterid());
            intent1.putExtra("from", t_from);
            intent1.putExtra("to",t_to);
            intent1.putExtra("tabid",7);
            startActivity(intent1);
            return;
        }else if (tabid == 10){
            Intent intent1 = new Intent();
            intent1.setClass(this,SubLartabActivity.class);
            intent1.putExtra("meterid", ((T10Bean) list.get(position)).getMeterid());
            intent1.putExtra("from", t_from);
            intent1.putExtra("to",t_to);
            intent1.putExtra("tabid",11);
            startActivity(intent1);
            return;
        }else if (tabid == 8){
            Intent intent1 = new Intent();
            intent1.setClass(this,SubLartabActivity.class);
            intent1.putExtra("meterid", ((T08Bean) list.get(position)).getMeterid());
            intent1.putExtra("from", t_from);
            intent1.putExtra("to",t_to);
            intent1.putExtra("tabid",9);
            startActivity(intent1);
            return;
        }else if (tabid == 12){
            return;
        }
        startActivity(intent);
    }



    class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            //当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            HorizontalScrollView headSrcrollView = (HorizontalScrollView) mHead
                    .findViewById(headSrcrollViewId);
            headSrcrollView.onTouchEvent(arg1);
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }
}
