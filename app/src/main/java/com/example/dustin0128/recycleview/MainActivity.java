package com.example.dustin0128.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> myDataset = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            myDataset.add(String.valueOf(i));
        }

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView = (RecyclerView)findViewById(R.id.list_view);

        //初始化 RecyclerView 一定要設定
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    //建置adapter資料連接的設定
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private List<String> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextview;
            public ViewHolder(View v) {
                super(v);
                mTextview = (TextView)v.findViewById(R.id.info_text);
            }
        }

        public MyAdapter(List<String> data){
            mData = data;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view,parent,false);
            ViewHolder vh=new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            holder.mTextview.setText(mData.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,"Item"+position+"is clicked",
                            Toast.LENGTH_SHORT).show();
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(MainActivity.this,"Item"+position+"is long clicked",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

    }

}