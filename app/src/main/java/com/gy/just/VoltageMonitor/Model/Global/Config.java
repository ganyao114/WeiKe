package com.gy.just.VoltageMonitor.Model.Global;

import android.text.TextUtils;

import com.gy.just.VoltageMonitor.Model.Bean.UrlSetting;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;

/**
 * Created by gy on 2016/4/23.
 */
public class Config {
    public static String EXC_INFO="1.存在空字段\n" +
            "2.运行总时间高于理论时间或者小于等于0分钟\n" +
            "3.超上时间大于理论时间或者小于等于0分钟\n" +
            "4.超下时间大于理论时间或者小于等于0分钟\n" +
            "5.合格时间大于理论时间或者小于等于0分钟\n" +
            "6.合格率>100%或<0%\n" +
            "7.最大值最小值发生时间不在同一天\n" +
            "8.最大值、最小值偏离标准电压值30%以上\n" +
            "9.异常类型存在2种或2种以上\n";
    public static class JSONConfig{
        public static class loginRes{
            public static String SID = "sid";
            public static String LOGIN_PAR_NAME = "username";
            public static String LOGIN_PAR_PASS = "password";
            public static String LOGIN_NAME = "u_loginName";
            public static String NAME = "u_name";
            public static String EMPLOYEE_NO = "u_employeeNo";
            public static String TEL_NO = "u_telNo";
            public static String ORG_CODE = "u_orgCode";
            public static String O_NAME = "o_name";
            public static String EMAIL = "u_email";
        }
        public static class mainList{
            public static String LIST_TOTAL = "total";
            public static String PT_ID = "pt_id";
            public static String PT_NAME = "pt_name";
            public static String LIST_KEY = "data";
            public static String METER_SUM = "total";
            public static String EXC_COUNT = "r_exception";
            public static String EXC_RATE = "erate";
            public static String DAY_DATAS = "r_count";
            public static String DAY_OL_RATE = "rate";
            public static String MIN_COUNTS = "r_fcount";
            public static String MIN_OL_RATE = "frate";
        }
        public static class detailBase{
            public static String DATA_KEY = "data";
            public static String ID = "yb_id";
            public static String NAME = "yb_name";

            public static String KIND = "pt_name";
            public static String INSTALL_LOCAL = "yb_address";
            public static String CITY_KIND = "pt2_name";
            public static String VOL_LEVEL = "v_name";
            public static String USER_UNIT = "o_name";
//            public static String MNG_PERSON = "";
        }

        public static class VolTab {
            public static String STATE_KEY = "meta";
            public static String TOTAL_KEY = "total";
            public static String SUCCESS = "success";
            public static String NODE_ID = "RN";
            public static String NODE_TIME = "m_time";
            public static String NODE_VOL = "m_voltage";
            public static String DATA_KEY = "data";
            public static String FLAG = "m_type";

            public static String CUT = "m_score";
        }

        public static class T02{
            public static String ISOL = "r_online" ;
            public static String DATA = "data";

            public static String METER_ID = "yb_id";
            public static String KIND = "pt_name";
            public static String INSTALL = "yb_address";
            public static String OWNER = "o_name";
            public static String MIN_DATAS = "r_count";
            public static String SUM = "r_score";
            public static String CSX = "r_csxCount";
            public static String CXX = "r_cxxCount";
            public static String EXC_BASE = "r_e00";
        }
        public static class DetailT3{
            public static String EXC_BASE = "r_e00";
            public static String DATA = "data";
            public static String CSX = "r_csxCount";
            public static String CXX = "r_cxxCount";
            public static String MIN_DATAS = "r_count";
            public static String ISOL = "r_online" ;
            public static String SUM = "r_score";
        }
        public static class T04{
            public static String METER_ID = "yb_id";
            public static String KIND = "pt_name";
            public static String INSTALL = "yb_address";
            public static String OWNER = "o_name";
            public static String DATA_KEY = "data";
            public static String RUNINFOBASE = "run_x";
        }

        public static class T05 {

            public static String UNIT = "orgName";
            public static String KIND = "pointType";
            public static String PASS_PER = "rate";
            public static String COUNT_RATE = "scoreRate";
            public static String METER_COUNT = "count";

            public static String SUM = "score";
            public static String DAY_CUT = "rLostScore";
            public static String MIN_CUT = "fLostScore";
            public static String EXC_CUT = "eLostScore";
            public static String OVER_UPPER = "upLostScore";
            public static String OVER_DOWN = "dnLostScore";
        }
        public static class NOTIFY{

            public static String METER_ID = "m_ybID";
            public static String EXC_TYPE = "m_type";
            public static String VOL ="m_voltage";
            public static String TIME = "m_time";
        }

        public static class T06 {

            public static String METERID = "ybId";
            public static String UNIT = "orgName";
            public static String CITYKIND = "pt2Name";

            public static String KIND = "ptName";
            public static String VOLLEVEL = "vlName";
            public static String SUNRUNTIME = "zyxs";
            public static String OVER_UPPER = "csxs";
            public static String OVER_UPPER_PER = "csxl";
            public static String OVER_DN = "cxxs";

            public static String OVER_DN_PER = "cxxl";
            public static String MAX = "maxV";
            public static String MAX_TIME = "maxVTime";
            public static String MIN = "minV";
            public static String MIN_TIME = "minVTime";
            public static String PASS_PER = "rate";
            public static String AVR = "avgV";
            public static String INSTALL = "address";
            public static String PASS_TIME = "hgs";
            public static String DATE = "time";
        }

        public static class Exc_List {
            public static String METER_KIND = "ptName";
            public static String PT_ID = "pointTypeId";
            public static String METER_SUM = "n";
            public static String OFFLINES = "rmissingCount";
            public static String EXCS = "ecount";
            public static String OVER_LIMITS = "cxlCount";
            public static String OFFLINE_PER = "missingRate";
            public static String EXC_PER = "eRate";
            public static String OVWE_PER = "cxlRate";
        }

        public static class T10 {

            public static String UNIT = "orgName";
            public static String CITYKIND = "pt2Name";
            public static String INSTALL = "address";
            public static String KIND = "ptName";
            public static String METER_ID = "ybId";
            public static String VOLLEVEL = "vName";
        }
    }
    public static class Url{

        public static String BASE = "http://122.194.21.93:18888/GDZZ";

        public static String GETLIST_URL(){ return BASE + "/servlet/mobile/data/MRsjSummary1"; }
        public static String GETEXCLIST_URL(){ return BASE + "/servlet/mobile/data/MExceptionSummary";}
        public static String BASE_URL(){ return BASE + "/servlet/mobile/home/";}
        public static String LOGIN_URL(){ return BASE + "/servlet/mobile/home/MLoginServlet";}
        public static String VOL_TAB (){ return BASE + "/servlet/mobile/data/FsjOnlyLoad2";}
        public static String BASEMSG_URL (){ return BASE + "/servlet/mobile/basic/MYbOnlyLoad";}
        public static String GET_T02 (){ return BASE + "/servlet/mobile/data/MRsjDetail";}
        public static String DETAIL_LIST (){ return BASE + "/servlet/mobile/data/MRsjOnlyDetail";}
        public static String GET_T04 (){ return BASE + "/servlet/mobile/data/MRunStateLoad";}
        public static String GET_OLINFO (){ return BASE + "/servlet/mobile/data/MRunStateOnlyLoad";}
        public static String GET_TYPELIST (){ return BASE + "/servlet/mobile/common/MSimpleInfoLoad?id=pt_id&name=pt_name&tableName=t_pointType";}
        public static String GET_T05 (){ return BASE + "/servlet/mobile/data/MScoreSummaryLoad";}
        public static String GET_NOTIFY (){ return BASE + "/servlet/mobile/data/MessageServlet";}
        public static String GET_T06 (){ return BASE + "/servlet/mobile/report/MRateListLoad";}
        public static String YW_RES (){ return BASE + "/servlet/mobile/maintenance/MaintenaceSave";}
        public static String GET_T10 (){ return BASE + "/servlet/mobile/report/MLowerReport";}
        public static String GET_T11 (){ return BASE + "/servlet/mobile/report/MLowerReportDetail";}
        public static String OLSTATUE (){ return BASE + "/servlet/mobile/data/MLoginState";}
        public static String OL_DETAIL (){ return BASE + "/servlet/mobile/data/MLoginStateDetail";}
        public static String GET_T08 (){ return BASE + "/servlet/mobile/report/MKfSummary";}
        public static String GET_T09 (){ return BASE + "/servlet/mobile/report/MKfDetail";}
        public static String GET_T12 (){ return BASE + "/servlet/mobile/report/MXSLoad";}
        public static String MAINTAN_DATA (){ return BASE + "/servlet/mobile/maintenance/DataMaintenanceStatus";}
        public static String UPDATE_CHECK (){ return BASE  + "/update.json";}
    }
    public static class Par{
        public static String TOKEN_ID = "sid";
        public static class mainList{
            public static String KEY_ID = "id";
            public static String KEY_NAME = "name";
            public static String KEY_TABNAME = "tableName";
            public static String DATE_KEY = "time";
        }
        public static class baseMsg{
            public static String METER_ID = "ybId";
        }
        public static class volTab{
            public static String METER_ID = "ybId";
            public static String DATE = "date";
        }
        public static class T02{
            public static String DATE = "time";
            public static String START = "start";
            public static String LIMIT = "limit";
            public static String KIND = "pointType";
            public static String CITY = "pointType2";
            public static String SEARCH = "fuzzyWord";
        }
        public static class DETAIL{
            public static String METER_ID = "ybId";
            public static String TIME = "time";
        }
        public static class T04{
            public static String START = "start";
            public static String LIMIT = "limit";
            public static String YEAR = "year";
            public static String MONTH = "month";
        }

        public static class T05 {
            public static String FROM = "timeFrom";
            public static String TO = "timeTo";
        }
        public static class T06{
            public static String LIST = "list";
            public static String TIMEFROM = "timeFrom";
            public static String TIMETO = "timeTo";
            public static String START = "start";
            public static String LIMIT = "limit";
            public static String YBID = "ybId";
        }

        public static class YNWEI {

            public static String METER_ID = "ybId";

            public static String DATE = "date";
            public static String START = "startTime";
            public static String STOP = "endTime";
            public static String X = "longitude";
            public static String Y = "latitude";
            public static String Z = "height";
            public static String TYPE = "type";
            public static String RES = "result";
        }
    }
}
