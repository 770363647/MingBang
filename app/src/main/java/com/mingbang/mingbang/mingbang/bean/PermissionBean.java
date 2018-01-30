package com.mingbang.mingbang.mingbang.bean;

import com.mingbang.mingbang.mingbang.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhaojy
 * @data:On 2018/1/27.
 */

public class PermissionBean {
    private final String TAG = "PermissionBean";

    private static Map<String, Integer[]> permission;

    /**
     * 考勤管理
     */
    private Integer[] attendance = new Integer[]{R.layout.punching_time_card};
    /**
     * 财务报表
     */
    private Integer[] financialStatement = new Integer[]{R.layout.daily_paper, R.layout.weekly_paper,
            R.layout.monthly_paper, R.layout.quarterly_paper, R.layout.semiyearly_paper,
            R.layout.yearly_paper};
    /**
     * 审批管理
     */
    private Integer[] approval = new Integer[]{R.layout.indent_approval, R.layout.adjust_price};
    /**
     * 信息查询
     */
    private Integer[] inforQuery = new Integer[]{R.layout.staff_infor_query,
            R.layout.car_infor_query,
            R.layout.driver_infor_query, R.layout.tender_infor_query,
            R.layout.client_infor_query,
            R.layout.indent_infor_query, R.layout.freight_infor_query,
            R.layout.warehouse_infor_query, R.layout.my_performance_query,
            R.layout.car_income_expense_query, R.layout.logistics_infor_query,
            R.layout.staff_performance_infor_query,
            R.layout.salesman_task_infor_query, R.layout.sale_cargo_infor_query,
            R.layout.staff_attendance_infor_query};
    /**
     * 信息录入
     */
    private Integer[] inforEntry = new Integer[]{R.layout.driver_infor_entry, R.layout.car_infor_entry,
            R.layout.staff_infor_entry, R.layout.single_way_infor_entry,
            R.layout.car_infor_entry, R.layout.freight_infor_entry,
            R.layout.cargo_adjust_price_entry};
    /**
     * 管理员功能
     */
    private Integer[] administratorFunction = new Integer[]{R.layout.task_set, R.layout.permission_setting,
            R.layout.credit_limits};
    /**
     * 订单管理
     */
    private Integer[] orderManage = new Integer[]{R.layout.record_real_send, R.layout.record_real_get,
            R.layout.fill_order};
    /**
     * 车辆管理
     */
    private Integer[] carManage = new Integer[]{R.layout.accident_handler};
    /**
     * 招标管理
     */
    private Integer[] tenderManage = new Integer[]{R.layout.issue_tender};
    /**
     * 报销管理
     */
    private Integer[] reimbursementManage = new Integer[]{R.layout.reimbursement};

    private static PermissionBean pb = new PermissionBean();

    private PermissionBean() {
        permission = new HashMap<>();

        permission.put("attendance", attendance);
        permission.put("financialStatement", financialStatement);
        permission.put("approval", approval);
        permission.put("inforQuery", inforQuery);
        permission.put("inforEntry", inforEntry);
        permission.put("administratorFunction", administratorFunction);
        permission.put("orderManage", orderManage);
        permission.put("carManage", carManage);
        permission.put("tenderManage", tenderManage);
        permission.put("reimbursementManage", reimbursementManage);

    }

    public static PermissionBean getInstance() {
        return pb;
    }

    /**
     * TODO:根据key值获取value
     *
     * @param key
     * @return
     */
    public Integer[] getValueBaseKey(String key) {
        return permission.get(key);
    }

}
