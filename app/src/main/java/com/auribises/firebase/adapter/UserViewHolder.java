package com.auribises.firebase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.auribises.firebase.R;

/**
 * Created by ishantkumar on 01/01/18.
 */

public class UserViewHolder extends RecyclerView.ViewHolder{

    TextView txtName;
    TextView txtEmail;

    public UserViewHolder(View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.textViewName);
        txtEmail = itemView.findViewById(R.id.textViewEmail);
    }


}
