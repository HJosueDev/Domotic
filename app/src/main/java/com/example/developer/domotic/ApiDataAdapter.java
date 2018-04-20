package com.example.developer.domotic;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.developer.domotic.models.ApiRest;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Developer on 26/11/2017.
 */

public class ApiDataAdapter extends RecyclerView.Adapter<ApiDataAdapter.ApiDataViewHolder> {

    List<ApiRest> list;

    public ApiDataAdapter(List<ApiRest> list){
        this.list=list;
    }

    @Override
    public ApiDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.element_grid,parent,false);

        return new ApiDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ApiDataViewHolder holder, int position) {
        holder.bindApiData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ApiDataViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCode;
        TextView textViewName;
        TextView textViewStatus;
        AppCompatImageButton imageButton;
        public ApiDataViewHolder (View itemView){
            super(itemView);
            textViewCode=(TextView)itemView.findViewById(R.id.textViewCode);
            textViewName=(TextView)itemView.findViewById(R.id.textViewName);
            textViewStatus=(TextView)itemView.findViewById(R.id.textViewStatus);
            imageButton=(AppCompatImageButton)itemView.findViewById(R.id.imageButtonStatus);
        }

        public void bindApiData(ApiRest apiRest){
            textViewCode.setText(String.valueOf(apiRest.getId()));
            textViewName.setText(apiRest.getName());
            textViewStatus.setText(String.valueOf(apiRest.getStatus()));
            if (apiRest.getStatus()==0){
                imageButton.setImageTintList(new ColorStateList(
                        new int[][]{new int[]{android.R.attr.state_selected},
                                new int[]{}
                        },
                        new int[]{Color.YELLOW,Color.GRAY}
                ));
            }else if (apiRest.getStatus()==1){
                imageButton.setImageTintList(new ColorStateList(
                        new int[][]{new int[]{android.R.attr.state_selected},
                                new int[]{}
                        },
                        new int[]{Color.GRAY,Color.YELLOW}
                ));
            }

            imageButton.setSupportBackgroundTintMode(PorterDuff.Mode.SRC_OVER);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageButton.setSelected(!imageButton.isSelected());
                    if (textViewStatus.getText()=="1"){
                        textViewStatus.setText("0");
                    }else if(textViewStatus.getText()=="0"){
                        textViewStatus.setText("1");
                    }
                }
            });
        }
    }
}
