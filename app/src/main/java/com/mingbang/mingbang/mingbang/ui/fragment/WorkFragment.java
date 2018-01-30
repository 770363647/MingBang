package com.mingbang.mingbang.mingbang.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.bean.PermissionBean;
import com.mingbang.mingbang.mingbang.ui.activity.administratorfunction.CreditLimitsSetting;
import com.mingbang.mingbang.mingbang.ui.activity.administratorfunction.PermissionSetting;
import com.mingbang.mingbang.mingbang.ui.activity.administratorfunction.TaskSetActivity;
import com.mingbang.mingbang.mingbang.ui.activity.attendance.AttendanceActivity;
import com.mingbang.mingbang.mingbang.ui.activity.carmanage.AccidentDisposeActivity;
import com.mingbang.mingbang.mingbang.ui.activity.financepaper.DailyPaperActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforentry.CarInforEntryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforentry.CargoAdjustPriceActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforentry.CargoInforEntryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforentry.DriverInforEntryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforentry.SingleInforEntryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforentry.StaffInforEntryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.CarIncomeExpenseActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.CarInforQueryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.MyPerformanceQueryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.OrderInforQueryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.SaleCargoQueryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.SalemanTaskQueryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.StaffAttendActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.PersonInforQueryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.StaffPerformanceQueryActivity;
import com.mingbang.mingbang.mingbang.ui.activity.inforquery.TenderInforActivity;
import com.mingbang.mingbang.mingbang.ui.activity.ordermanage.FactGetSendActivity;
import com.mingbang.mingbang.mingbang.ui.activity.ordermanage.FillOrderActivity;
import com.mingbang.mingbang.mingbang.ui.activity.reimbursementmanage.ReimbursementActivity;
import com.mingbang.mingbang.mingbang.ui.activity.tendermanage.IssueTenderActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhaojy
 * @data:On 2018/1/18.
 */

public class WorkFragment extends Fragment {
    private final String TAG = "WorkFragment";
    private View view;
    private Context context;

    /**
     * 权限表实例对象
     */
    private PermissionBean pb = PermissionBean.getInstance();

    /**
     * key值数组
     */
    private String[] keyList = {"attendance", "financialStatement", "approval", "inforQuery",
            "inforEntry", "administratorFunction", "orderManage", "carManage",
            "tenderManage", "reimbursementManage"};


    private Map<String, LinearLayout> linearList;

    private LinearLayout attendance;
    private LinearLayout financialStatement;
    private LinearLayout approval;
    private LinearLayout inforQuery;
    private LinearLayout inforEntry;
    private LinearLayout administratorFunction;
    private LinearLayout orderManage;
    private LinearLayout carManage;
    private LinearLayout tenderManage;
    private LinearLayout reimbursementManage;

    //监听对象集合
    private Map<Integer, Intent> listenerObject;


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work_layout, null);
        init();

        return view;
    }

    private void init() {
        this.context = this.getActivity();
        findById();
        initListenerIntent();
        initLinearList();
        generateLayout();

    }

    private void findById() {
        attendance = view.findViewById(R.id.attendance);
        financialStatement = view.findViewById(R.id.financialStatement);
        approval = view.findViewById(R.id.approval);
        inforQuery = view.findViewById(R.id.inforQuery);
        inforEntry = view.findViewById(R.id.inforEntry);
        administratorFunction = view.findViewById(R.id.administratorFunction);
        orderManage = view.findViewById(R.id.orderManage);
        carManage = view.findViewById(R.id.carManage);
        tenderManage = view.findViewById(R.id.tenderManage);
        reimbursementManage = view.findViewById(R.id.reimbursementManage);
    }

    /**
     * TODO:初始化 linearList
     */
    private void initLinearList() {
        linearList = new HashMap<>();
        linearList.put(keyList[0], attendance);
        linearList.put(keyList[1], financialStatement);
        linearList.put(keyList[2], approval);
        linearList.put(keyList[3], inforQuery);
        linearList.put(keyList[4], inforEntry);
        linearList.put(keyList[5], administratorFunction);
        linearList.put(keyList[6], orderManage);
        linearList.put(keyList[7], carManage);
        linearList.put(keyList[8], tenderManage);
        linearList.put(keyList[9], reimbursementManage);
    }

    /**
     * TODO:初始化监听对象
     */
    private void initListenerIntent() {
        listenerObject = new HashMap<>();
        //考勤管理
        initAttendListener();
        //财务报表
        initFianceListener();
        //信息查询
        initInforQueryListener();
        //信息录入
        initInforEntryListener();
        //管理员功能
        initAdministratorListener();
        //订单管理
        initOrderManageListener();
        //车辆管理
        initCarManageListener();
        //招标管理
        initTenderManage();
        //报销管理
        initReimbursementaManageListener();

    }

    /**
     * TODO:考勤管理监听事件
     */
    private void initAttendListener() {
        //考勤打卡
        Intent attend = new Intent(context, AttendanceActivity.class);
        listenerObject.put(R.layout.punching_time_card, attend);
    }

    /**
     * TODO:财务报表监听事件
     */
    private void initFianceListener() {
        //日报
        Intent daily = new Intent(context, DailyPaperActivity.class);
        listenerObject.put(R.layout.daily_paper, daily);
    }

    /**
     * TODO:信息查询监听事件
     */
    private void initInforQueryListener() {
        //员工信息
        Intent staffInfor = new Intent(context, PersonInforQueryActivity.class);
        staffInfor.putExtra("type", "staff");
        listenerObject.put(R.layout.staff_infor_query, staffInfor);

        //车辆信息
        Intent carInfor = new Intent(context, CarInforQueryActivity.class);
        listenerObject.put(R.layout.car_infor_query, carInfor);

        //司机信息
        Intent driverInfor = new Intent(context, PersonInforQueryActivity.class);
        driverInfor.putExtra("type", "driver");
        listenerObject.put(R.layout.driver_infor_query, driverInfor);

        //招标信息
        Intent tenderInfor = new Intent(context, TenderInforActivity.class);
        listenerObject.put(R.layout.tender_infor_query, tenderInfor);

        //客户信息
        Intent clientInfor = new Intent(context, PersonInforQueryActivity.class);
        clientInfor.putExtra("type", "client");
        listenerObject.put(R.layout.client_infor_query, clientInfor);

        //订单信息
        Intent orderInfor = new Intent(context, OrderInforQueryActivity.class);
        listenerObject.put(R.layout.indent_infor_query, orderInfor);

        //车辆收支信息
        Intent carIncomeExpense = new Intent(context, CarIncomeExpenseActivity.class);
        listenerObject.put(R.layout.car_income_expense_query, carIncomeExpense);

        //员工考勤信息
        Intent staffAttendInfor = new Intent(context, StaffAttendActivity.class);
        listenerObject.put(R.layout.staff_attendance_infor_query, staffAttendInfor);

        //员工绩效查询
        Intent staffPerformance = new Intent(context, StaffPerformanceQueryActivity.class);
        listenerObject.put(R.layout.staff_performance_infor_query, staffPerformance);

        //我的绩效查询
        Intent myPerformance = new Intent(context, MyPerformanceQueryActivity.class);
        listenerObject.put(R.layout.my_performance_query, myPerformance);

        //可售货物查询
        Intent saleingCargo = new Intent(context, SaleCargoQueryActivity.class);
        listenerObject.put(R.layout.sale_cargo_infor_query, saleingCargo);

        //销售员任务查询
        Intent salemanTask = new Intent(context, SalemanTaskQueryActivity.class);
        listenerObject.put(R.layout.salesman_task_infor_query, salemanTask);

    }

    /**
     * TODO:信息录入监听事件
     */
    private void initInforEntryListener() {
        //司机信息
        Intent driverInfor = new Intent(context, DriverInforEntryActivity.class);
        listenerObject.put(R.layout.driver_infor_entry, driverInfor);

        //车辆信息
        Intent carInfor = new Intent(context, CarInforEntryActivity.class);
        listenerObject.put(R.layout.car_infor_entry, carInfor);

        //员工信息
        Intent staffInfor = new Intent(context, StaffInforEntryActivity.class);
        listenerObject.put(R.layout.staff_infor_entry, staffInfor);

        //单程信息
        Intent singleWay = new Intent(context, SingleInforEntryActivity.class);
        listenerObject.put(R.layout.single_way_infor_entry, singleWay);

        //货物信息
        Intent cargoInfor = new Intent(context, CargoInforEntryActivity.class);
        listenerObject.put(R.layout.cargo_infor_entry, cargoInfor);

        //货物调价
        Intent cargpAdjustPrice = new Intent(context, CargoAdjustPriceActivity.class);
        listenerObject.put(R.layout.cargo_adjust_price_entry, cargpAdjustPrice);
    }

    /**
     * TODO:管理员功能事件监听
     */
    private void initAdministratorListener() {

        //权限设定
        Intent permissionSet = new Intent(context, PermissionSetting.class);
        listenerObject.put(R.layout.permission_setting, permissionSet);

        //赊信额度
        Intent creditLimitSet = new Intent(context, CreditLimitsSetting.class);
        listenerObject.put(R.layout.credit_limits, creditLimitSet);

        //任务设置
        Intent taskSet = new Intent(context, TaskSetActivity.class);
        listenerObject.put(R.layout.task_set, taskSet);
    }

    /**
     * TODO:订单管理监听事件
     */
    private void initOrderManageListener() {
        //记录实发
        Intent recordRealSend = new Intent(context, FactGetSendActivity.class);
        recordRealSend.putExtra("type", "send");
        listenerObject.put(R.layout.record_real_send, recordRealSend);

        //记录实收
        Intent recordRealGet = new Intent(context, FactGetSendActivity.class);
        recordRealGet.putExtra("type", "get");
        listenerObject.put(R.layout.record_real_get, recordRealGet);

        //填写订单
        Intent fillOrder = new Intent(context, FillOrderActivity.class);
        listenerObject.put(R.layout.fill_order, fillOrder);
    }

    /**
     * TODO:车辆管理监听事件
     */
    private void initCarManageListener() {
        //事故处理
        Intent accidentDispose = new Intent(context, AccidentDisposeActivity.class);
        listenerObject.put(R.layout.accident_handler, accidentDispose);
    }

    /**
     * TODO:招标管理监听事件
     */
    private void initTenderManage() {
        //发布招标
        Intent issueTender = new Intent(context, IssueTenderActivity.class);
        listenerObject.put(R.layout.issue_tender, issueTender);
    }

    /**
     * TODO:报销管理监听事件
     */
    private void initReimbursementaManageListener() {
        //报销
        Intent reimbursement = new Intent(context, ReimbursementActivity.class);
        listenerObject.put(R.layout.reimbursement, reimbursement);
    }

    /**
     * TODO:根据用户权限动态生成布局
     */
    private void generateLayout() {
        Integer[] itemIdList = null;
        for (int i = 0; i < keyList.length; i++) {
            itemIdList = pb.getValueBaseKey(keyList[i]);
            final LinearLayout container = linearList.get(keyList[i]);

            if (itemIdList == null) {
                continue;
            }
            //子项目个数
            final int itemSum = itemIdList.length;
            if (itemSum != 0) {
                container.setVisibility(View.VISIBLE);
            } else {
                return;
            }
            //获取子view个数
            int viewSum = container.getChildCount();
            //当前添加的item索引下标
            int curItemIndex = 0;

            for (int j = 1; j < viewSum - 1; j++) {
                //获取一行的view
                LinearLayout row = (LinearLayout) container.getChildAt(j);

                for (int k = 0; k < 4; k++) {
                    View view = getLayoutInflater().inflate(itemIdList[curItemIndex], null);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                    view.setLayoutParams(lp);
                    row.addView(view);

                    final int finalCurItemIndex = curItemIndex;
                    final Integer[] finalItemIdList = itemIdList;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = listenerObject.get(finalItemIdList[finalCurItemIndex]);
                            if (intent != null) {
                                startActivity(intent);
                            }
                        }
                    });

                    curItemIndex = curItemIndex + 1;
                    if (curItemIndex % 4 == 0) {
                        continue;
                    } else if (curItemIndex == itemSum) {
                        break;
                    }
                }
            }

            //占位item总数
            int placeItemSum = 4 - itemSum % 4;
            //最后一行的对象实例
            LinearLayout row = (LinearLayout) container.getChildAt(viewSum - 2);
            //添加占位item
            for (int j = 0; j < placeItemSum; j++) {
                View view = getLayoutInflater().inflate(R.layout.work_item_place, null);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                view.setLayoutParams(lp);
                row.addView(view);
            }
        }

    }

}
