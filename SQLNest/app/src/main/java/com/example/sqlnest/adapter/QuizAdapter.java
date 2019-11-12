package com.example.sqlnest.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnest.R;
import com.example.sqlnest.models.Test;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private ArrayList<Test> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mListener;
    private Context context;

    {
        mInflater = null;
        mData = new ArrayList<>();
        mListener = null;
        this.context = null;

    }//init block

    public QuizAdapter(Context context , ArrayList<Test> mDataFiltered , ItemClickListener listener){

        this.mInflater = LayoutInflater.from(context);
        this.mData = mDataFiltered;
        this.mListener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  mInflater.inflate(R.layout.item_quiz, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (mData.get(position).getTestName()){

            case "ddl" : {
                holder.item.setBackground(context.getDrawable(R.drawable.ddl_test_background));
            }
            break;
            case "dml" : {
                holder.item.setBackground(context.getDrawable(R.drawable.dml_test_background));
            }
            break;
            case "af" : {
                holder.item.setBackground(context.getDrawable(R.drawable.af_test_background));
            }
            break;
            default :{
                holder.item.setBackground(context.getDrawable(R.drawable.acf_test_background));
            }
            break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        RelativeLayout item;

         ViewHolder(View itemView){
            super(itemView);
             itemView.setOnClickListener(this);

             item = itemView.findViewById(R.id.rlItem);
        }

        @Override
        public void onClick(View view) {
            mListener.onDetail(mData.get(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {
        public void onDetail(Test test);
    }

}
