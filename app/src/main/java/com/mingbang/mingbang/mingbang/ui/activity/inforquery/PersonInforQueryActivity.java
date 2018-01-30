package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.adapter.StaffInforItemAdapter;
import com.mingbang.mingbang.mingbang.bean.StaffInforBean;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;
import com.mingbang.mingbang.mingbang.utils.CharacterParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/22.
 */

public class PersonInforQueryActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = "PersonInforQueryActivity";

    private Context context;
    private RecyclerView recyclerView;
    private EditText search;

    private TextView title;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.person_infor_query_layout);
        init();
    }

    private void init() {
        context = this;
        findById();
        setTitle();
        setRecyclerView();
        setSearch();
    }

    private void findById() {
        recyclerView = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search);
        title = findViewById(R.id.title);
    }

    /**
     * TODO:设置标题
     */
    private void setTitle() {
        Intent intent = getIntent();
        String titleInfor = intent.getStringExtra("type");
        String str = "";
        switch (titleInfor) {
            case "staff":
                str = getResources().getString(R.string.staffInforQuery);
                break;
            case "client":
                str = getResources().getString(R.string.clientInforQuery);
                break;
            case "driver":
                str = getResources().getString(R.string.driverInforQuery);
                break;
            default:
                break;
        }
        title.setText(str);
    }

    /**
     * TODO:设置 RecyclerView
     */
    @SuppressLint("LongLogTag")
    private void setRecyclerView() {
        final List<StaffInforBean> data = new ArrayList();
        String[] name = {"江城雪", "庐砚秋", "唐念慈", "焉其枝", "清让", "池映月", "顾北音", "冷清秋",
                "姜暮烟", "花满楼", "白流苏", "赵默笙", "江疏影", "南怀瑾", "上官谦修", "南宫玉轩",
                "雅冬青", "琼如", "东方西臣", "兰亭", "程灵素", "师之然", "宛依凝",
                "严歌苓", "唐言蹊", "风咏絮", "徐容止", "流川枫", "叶知秋", "温思莞", "陈沐恩",
                "林风眠", "伤流景", "临晚镜", "紫苏", "风晴雪", "仲依桐", "叶璐雪", "萧瑾萱",
                "上官文萱", "司马嘉树", "夏梦", "江心月", "许斯唯", "沁儒", "应梦妍", "秋叶蝶舞", "汤熙妍",
                "谷雪菲", "黄怡蕾", "云怀风", "陈余落燕", "苏兰青子", "安雨寒", "梅霜寒"};

        //获取首个字符首个字母
        String[] firstLetter = new String[name.length];
        for (int i = 0; i < name.length; i++) {
            String str = CharacterParser.getInstance().convert(
                    String.valueOf(name[i].charAt(0)));
            firstLetter[i] = String.valueOf(str.charAt(0)).toUpperCase();
        }

        //按照字母顺序排序
        for (int i = 0; i < name.length - 1; i++) {
            int k = i;
            String temp = "";

            for (int j = i + 1; j < name.length; j++) {
                if (firstLetter[j].compareTo(firstLetter[k]) < 0) {
                    k = j;
                }
            }

            if (k != i) {
                temp = firstLetter[i];
                firstLetter[i] = firstLetter[k];
                firstLetter[k] = temp;

                temp = name[i];
                name[i] = name[k];
                name[k] = temp;
            }
        }

        //记录当前进行到的字母
        String temp = "";

        for (int i = 0; i < name.length; i++) {
            StaffInforBean sib = new StaffInforBean();
            if (!firstLetter[i].equals(temp)) {
                temp = firstLetter[i];
                sib.setIndex(temp);
            } else {
                sib.setIndex("*");
                sib.setIndexShow(false);
            }
            sib.setName(name[i]);
            data.add(sib);
        }

        StaffInforItemAdapter adapter = new StaffInforItemAdapter(data, this);
        adapter.setOnItemClickListener(new StaffInforItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, PersonInforDetailActivity.class);
                intent.putExtra("name", data.get(position).getName());
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    /**
     * TODO:设置搜索框
     */
    private void setSearch() {
        //设置EditText光标不可见
        search.setCursorVisible(false);

        //设置监听
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setCursorVisible(true);
            }
        });

    }


}
