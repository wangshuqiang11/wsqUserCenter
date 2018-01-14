package com.bwei.wsq.wsqusercenter.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.wsq.wsqusercenter.R;
import com.bwei.wsq.wsqusercenter.bean.ShowBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MVH> {

    private List<ShowBean.TuijianBean.ListBean> list;
    private Context context;
    private Onclick onclick;


    public interface Onclick {
        void Onclik(String pid);
    }

    public void setOnclick(Onclick onclick) {

        this.onclick = onclick;
    }


    public MyAdapter(List<ShowBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        return new MVH(view);
    }

    @Override
    public void onBindViewHolder(MVH holder, int position) {

        MVH holder1 = holder;
        final ShowBean.TuijianBean.ListBean listBean = list.get(position);
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        holder1.simp.setImageURI(split[0]);
        holder1.title.setText(listBean.getTitle());
        holder1.price.setText("￥:"+listBean.getPrice());
        holder1.price1.setText("￥"+listBean.getBargainPrice()+"");
        holder1.price1.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onclick.Onclik(listBean.getPid()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVH extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;
        private final TextView title;
        private final TextView price;
        private final TextView price1;
        private final LinearLayout lin;

        public MVH(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.simp);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            price1 = itemView.findViewById(R.id.price1);
            lin = itemView.findViewById(R.id.zi_li);
        }
    }

}
