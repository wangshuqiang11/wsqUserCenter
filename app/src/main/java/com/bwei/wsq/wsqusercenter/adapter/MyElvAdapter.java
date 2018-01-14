package com.bwei.wsq.wsqusercenter.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.wsq.wsqusercenter.R;
import com.bwei.wsq.wsqusercenter.bean.CartBean;
import com.bwei.wsq.wsqusercenter.eventbus.MessageEvent;
import com.bwei.wsq.wsqusercenter.eventbus.PriceAndCountEvent;
import com.bwei.wsq.wsqusercenter.view.AddDeleteView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class MyElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CartBean.DataBean> groupList;
    private List<List<CartBean.DataBean.ListBean>> childList;
    private LayoutInflater inflater;

    public MyElvAdapter(Context context, List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final GroupViewHolder holder;
        if (convertView == null) {
            holder = new GroupViewHolder();
            view = inflater.inflate(R.layout.cart_group_item, null);
            holder.cb_group = view.findViewById(R.id.cb_group);
            holder.tv_dian = view.findViewById(R.id.gou_dian);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        final CartBean.DataBean dataBean = groupList.get(groupPosition);
        holder.cb_group.setChecked(dataBean.isChecked());
        holder.tv_dian.setText(dataBean.getSellerName());
        //一级列表的Checkbox
        holder.cb_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(holder.cb_group.isChecked());
                //当一级选中时，改变二级列表CheckBox状态
                changeChildCbState(groupPosition, holder.cb_group.isChecked());
                //将对应的数量和价格传到PriceAndCountEvent
                EventBus.getDefault().post(computer());
                //当一级的全部选中是，改变全选CheckBox状态
                changeAllCbState(isAllGroupCbSelected());
                //刷新列表
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder;
        if (convertView == null) {
            holder = new ChildViewHolder();
            view = inflater.inflate(R.layout.cart_child_item, null);
            holder.cb_child = view.findViewById(R.id.cb_child);
            holder.del = view.findViewById(R.id.del);
            holder.sdv = view.findViewById(R.id.child_sdv);
            //holder.adv_main = view.findViewById(R.id.adv_main);
            holder.tv_info = view.findViewById(R.id.child_info);
            holder.tv_price = view.findViewById(R.id.child_price);
            holder.tv_tit = view.findViewById(R.id.child_tit);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        final CartBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        holder.cb_child.setChecked(listBean.isChecked());
        String[] strings = listBean.getImages().split("\\!");
        holder.sdv.setImageURI(strings[0]);
        holder.tv_tit.setText(listBean.getSubhead());
        holder.tv_info.setText(listBean.getTitle());
        holder.tv_price.setText("￥" + listBean.getBargainPrice());
        //给二级CheckBox设置点击事件
        holder.cb_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置该条目对象里的checked属性值
                listBean.setChecked(holder.cb_child.isChecked());
                PriceAndCountEvent priceAndCountEvent = computer();
                EventBus.getDefault().post(priceAndCountEvent);

                if (holder.cb_child.isChecked()) {
                    //当前checkbox是选中状态
                    if (isAllChildCbSelected(groupPosition)) {
                        changeGroupCbState(groupPosition, true);
                        changeAllCbState(isAllGroupCbSelected());
                    }
                } else {
                    changeGroupCbState(groupPosition, false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                notifyDataSetChanged();
            }
        });
        //自定义View加减号
     /*   holder.adv_main.setOnAddDelClickListener(new AddDeleteView.OnAddDelClickListener() {
            @Override
            public void onAddClick(View v) {
                //加号
                int num = listBean.getNum();
                holder.adv_main.setNumber(++num);
                listBean.setNum(num);
                if (holder.cb_child.isChecked()) {
                    PriceAndCountEvent priceAndCountEvent = computer();
                    EventBus.getDefault().post(computer());
                }
            }

            @Override
            public void onDelClick(View v) {
                //减号
                int num = listBean.getNum();
                if (num == 1) {
                    return;
                }
                holder.adv_main.setNumber(--num);
                listBean.setNum(num);
                if (holder.cb_child.isChecked()) {
                    PriceAndCountEvent priceAndCountEvent = computer();
                    EventBus.getDefault().post(computer());
                }
            }
        });*/

        holder.del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("删除商品")
                        .setMessage("确定删除商品吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<CartBean.DataBean.ListBean> datasBeen = childList.get(groupPosition);
                        CartBean.DataBean.ListBean remove = datasBeen.remove(childPosition);
                        if (datasBeen.size() == 0) {
                            childList.remove(groupPosition);
                            groupList.remove(groupPosition);
                        }
                        EventBus.getDefault().post(computer());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "您取消了删除" + which, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                return true;
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        CheckBox cb_group;
        TextView tv_dian;
    }

    class ChildViewHolder {
        LinearLayout del;
        CheckBox cb_child;
        SimpleDraweeView sdv;
        TextView tv_tit;
        TextView tv_info;
        TextView tv_price;
       // AddDeleteView adv_main;
    }

    /**
     * 改变全选CheckBox状态
     *
     * @param flag
     */
    private void changeAllCbState(boolean flag) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setChecked(flag);
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 改变二级列表CheckBox状态
     *
     * @param groupPosition
     * @param flag
     */
    private void changeChildCbState(int groupPosition, boolean flag) {
        List<CartBean.DataBean.ListBean> listBeans = childList.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            CartBean.DataBean.ListBean listBean = listBeans.get(i);
            listBean.setChecked(flag);
        }
    }

    /**
     * 改变一级列表CheckBox状态
     *
     * @param groupPosition
     * @param flag
     */
    private void changeGroupCbState(int groupPosition, boolean flag) {
        CartBean.DataBean dataBean = groupList.get(groupPosition);
        dataBean.setChecked(flag);
    }

    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    public boolean isAllGroupCbSelected() {
        for (int i = 0; i < groupList.size(); i++) {
            CartBean.DataBean dataBean = groupList.get(i);
            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二级列表是否全部选中
     *
     * @param groupPosition
     * @return
     */
    public boolean isAllChildCbSelected(int groupPosition) {
        List<CartBean.DataBean.ListBean> listBeans = childList.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            CartBean.DataBean.ListBean listBean = listBeans.get(i);
            if (!listBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < groupList.size(); i++) {
            //改变一级列表CheckBox状态
            changeGroupCbState(i, flag);
            //改变二级列表CheckBox状态
            changeChildCbState(i, flag);
        }
        //将数量和价格传值
        EventBus.getDefault().post(computer());
        //刷新列表
        notifyDataSetChanged();
    }

    /**
     * 计算列表中选中的钱和数量
     *
     * @return
     */
    private PriceAndCountEvent computer() {
        int count = 0;
        int price = 0;
        for (int i = 0; i < childList.size(); i++) {
            List<CartBean.DataBean.ListBean> listBeans = childList.get(i);
            for (int j = 0; j < listBeans.size(); j++) {
                CartBean.DataBean.ListBean listBean = listBeans.get(j);
                if (listBean.isChecked()) {
                    count += listBean.getNum();
                    price += listBean.getNum() * listBean.getPrice();
                }
            }
        }
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setCount(count);
        priceAndCountEvent.setPrice(price);
        return priceAndCountEvent;
    }
}
