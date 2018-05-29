package com.bwie.myjingdongxiangmu.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.myjingdongxiangmu.R;
import com.bwie.myjingdongxiangmu.bean.MyPriceHeNumBean;
import com.bwie.myjingdongxiangmu.bean.MySelectBean;
import com.bwie.myjingdongxiangmu.presenter.PresenterSelect;
import com.bwie.myjingdongxiangmu.util.ApiUtil;
import com.bwie.myjingdongxiangmu.util.OkHttp3Util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/12.
 */

public class MyGouWuCheAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final MySelectBean mySelectBean;
    private final PresenterSelect presenterSelect;
    private final RelativeLayout relative_bar;
    private final Handler handler;
    private int childindex;
    private int childAllindex;
    private SharedPreferences preferences;
    private String uid;

    public MyGouWuCheAdapter(Context context, MySelectBean mySelectBean, PresenterSelect presenterSelect, RelativeLayout relative_bar, Handler handler) {

        this.context = context;
        this.mySelectBean = mySelectBean;
        this.presenterSelect = presenterSelect;
        this.relative_bar = relative_bar;
        this.handler = handler;
    }

    @Override
    public int getGroupCount() {
        return mySelectBean.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mySelectBean.getData().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mySelectBean.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mySelectBean.getData().get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        preferences = context.getSharedPreferences("yonghuxinxi", Activity.MODE_PRIVATE);
        uid = preferences.getString("uid", "");
        final GroupHolder holder;
        if (view==null){
            view=View.inflate(context, R.layout.item_group,null);
            holder=new GroupHolder();
            holder.check_group=view.findViewById(R.id.check_group);
            holder.text_group=view.findViewById(R.id.text_group);
            view.setTag(holder);
        }else {
            holder= (GroupHolder) view.getTag();
        }
        final MySelectBean.DataBean dataBean = mySelectBean.getData().get(groupPosition);
        holder.check_group.setChecked(dataBean.isGroup_checked());
        holder.text_group.setText(dataBean.getSellerName());

        holder.check_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                childindex = 0;
                allChildinGroup(holder.check_group.isChecked(),dataBean);
            }
        });
        return view;
    }

    private void allChildinGroup(final boolean checked, final MySelectBean.DataBean dataBean) {
        relative_bar.setVisibility(View.VISIBLE);
        //明天接着改
        MySelectBean.DataBean.ListBean listBean = dataBean.getList().get(childindex);

        Map<String, String> params=new HashMap<>();
        //uid=71&sellerid=1&pid=1&selected=0&num=10
        params.put("uid",uid);
        params.put("sellerid", String.valueOf(listBean.getSellerid()));
        params.put("pid", String.valueOf(listBean.getPid()));
        params.put("selected", String.valueOf(checked?1:0));
        params.put("num", String.valueOf(listBean.getNum()));
        OkHttp3Util.doPost(ApiUtil.gengxin, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    childindex++;
                    if (childindex<dataBean.getList().size()){
                        allChildinGroup(checked,dataBean);
                    }
                    if (uid!=null&&uid!=""){
                        presenterSelect.getSelectUrl(ApiUtil.chaxun,uid);
                    }
                }
            }
        });

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildHolder holder;
        if (view==null){
            view=View.inflate(context,R.layout.item_child,null);
            holder=new ChildHolder();
            holder.check_child=view.findViewById(R.id.check_child);
            holder.image_child=view.findViewById(R.id.image_child);
            holder.textchild_title=view.findViewById(R.id.textchild_title);
            holder.textchild_price=view.findViewById(R.id.textchild_price);
            holder.textchild_jian=view.findViewById(R.id.textchild_jian);
            holder.text_shuliang=view.findViewById(R.id.text_shuliang);
            holder.textchild_jia=view.findViewById(R.id.textchild_jia);
            holder.text_delete=view.findViewById(R.id.text_delete);
            view.setTag(holder);
        }else {
            holder= (ChildHolder) view.getTag();
        }
        final MySelectBean.DataBean.ListBean listBean = mySelectBean.getData().get(groupPosition).getList().get(childPosition);
        //holder.check_child
        //holder.image_child
        holder.check_child.setChecked(listBean.getSelected()==0?false:true);
        String[] split = listBean.getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.image_child);
        holder.textchild_title.setText(listBean.getTitle());
        holder.textchild_price.setText(listBean.getBargainPrice()+"");
        holder.text_shuliang.setText(listBean.getNum()+"");



        holder.check_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_bar.setVisibility(View.VISIBLE);
                Map<String, String> params=new HashMap<>();
                //uid=71&sellerid=1&pid=1&selected=0&num=10
                params.put("uid",uid);
                params.put("sellerid", String.valueOf(listBean.getSellerid()));
                params.put("pid", String.valueOf(listBean.getPid()));
                params.put("selected", String.valueOf(listBean.getSelected()==0?1:0));
                params.put("num", String.valueOf(listBean.getNum()));
                OkHttp3Util.doPost(ApiUtil.gengxin, params, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            if (uid!=null&&uid!=""){
                                presenterSelect.getSelectUrl(ApiUtil.chaxun,uid);
                            }
                        }
                    }
                });
            }
        });



        holder.textchild_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_bar.setVisibility(View.VISIBLE);
                Map<String, String> params=new HashMap<>();
                //uid=71&sellerid=1&pid=1&selected=0&num=10
                params.put("uid",uid);
                params.put("sellerid", String.valueOf(listBean.getSellerid()));
                params.put("pid", String.valueOf(listBean.getPid()));
                params.put("selected", String.valueOf(listBean.getSelected()));
                params.put("num", String.valueOf(listBean.getNum()+1));
                OkHttp3Util.doPost(ApiUtil.gengxin, params, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            if (uid!=null&&uid!=""){
                                presenterSelect.getSelectUrl(ApiUtil.chaxun,uid);
                            }
                        }
                    }
                });
            }
        });

        holder.textchild_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_bar.setVisibility(View.VISIBLE);
                int num = listBean.getNum();
                if (num==1){
                    return;
                }

                Map<String, String> params=new HashMap<>();
                //uid=71&sellerid=1&pid=1&selected=0&num=10
                params.put("uid",uid);
                params.put("sellerid", String.valueOf(listBean.getSellerid()));
                params.put("pid", String.valueOf(listBean.getPid()));
                params.put("selected", String.valueOf(listBean.getSelected()));
                params.put("num", String.valueOf(num-1));
                OkHttp3Util.doPost(ApiUtil.gengxin, params, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            if (uid!=null&&uid!=""){
                                presenterSelect.getSelectUrl(ApiUtil.chaxun,uid);
                            }
                        }
                    }
                });
            }
        });


        holder.text_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative_bar.setVisibility(View.VISIBLE);
                //uid=72&pid=1
                Map<String, String> params=new HashMap<>();
                params.put("uid",uid);
                params.put("pid", String.valueOf(listBean.getPid()));
                OkHttp3Util.doPost(ApiUtil.shanchu, params, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            if (uid!=null&&uid!=""){
                                presenterSelect.getSelectUrl(ApiUtil.chaxun,uid);
                            }
                        }
                    }
                });
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setAllChecked(boolean checked) {
        List<MySelectBean.DataBean.ListBean> listBeans = new ArrayList<>();
        List<MySelectBean.DataBean> data = mySelectBean.getData();
        for (int i=0;i<data.size();i++){
            List<MySelectBean.DataBean.ListBean> beans = data.get(i).getList();
            for (int j = 0; j< beans.size(); j++){
                listBeans.add(beans.get(j));
            }
        }
        childAllindex = 0;

        ChildAll(checked,listBeans);

    }

    private void ChildAll(final boolean checked, final List<MySelectBean.DataBean.ListBean> listBeans) {
        relative_bar.setVisibility(View.VISIBLE);
        final MySelectBean.DataBean.ListBean listBean = listBeans.get(childAllindex);
        Map<String, String> params=new HashMap<>();
        //uid=71&sellerid=1&pid=1&selected=0&num=10
        params.put("uid",uid);
        params.put("sellerid", String.valueOf(listBean.getSellerid()));
        params.put("pid", String.valueOf(listBean.getPid()));
        params.put("selected", String.valueOf(checked?1:0));
        params.put("num", String.valueOf(listBean.getNum()));
        OkHttp3Util.doPost(ApiUtil.gengxin, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    childAllindex++;
                    if (childAllindex<listBeans.size()){
                        ChildAll(checked,listBeans);
                    }else {
                        if (uid!=null&&uid!=""){
                            presenterSelect.getSelectUrl(ApiUtil.chaxun,uid);
                        }
                    }

                }
            }
        });


    }

    public void sendAllPrice() {

        double price = 0;
        int num = 0;
        List<MySelectBean.DataBean> data = mySelectBean.getData();
        for (int i=0;i<data.size();i++){
            List<MySelectBean.DataBean.ListBean> listBeans = data.get(i).getList();
            for (int j=0;j<listBeans.size();j++){
                if (listBeans.get(j).getSelected()==1){
                    price+=listBeans.get(j).getBargainPrice()*listBeans.get(j).getNum();
                    num+=listBeans.get(j).getNum();
                }
            }
        }



        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(price);
        MyPriceHeNumBean myPriceHeNumBean = new MyPriceHeNumBean(format, num);
        Message msg=Message.obtain();
        msg.what=0;
        msg.obj=myPriceHeNumBean;
        handler.sendMessage(msg);
    }


    class GroupHolder{
        CheckBox check_group;
        TextView text_group;
    }
    class ChildHolder{
        CheckBox check_child;
        ImageView image_child;
        TextView textchild_title;
        TextView textchild_price;
        TextView textchild_jian;
        TextView text_shuliang;
        TextView textchild_jia;
        TextView text_delete;
    }
}
