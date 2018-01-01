package com.auribises.firebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auribises.firebase.R;
import com.auribises.firebase.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by ishantkumar on 01/01/18.
 */

public class UserAdapter extends FirebaseRecyclerAdapter<User,UserViewHolder>{


    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public UserAdapter(Class<User> modelClass, int modelLayout, Class<UserViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(UserViewHolder viewHolder, User model, int position) {

        viewHolder.txtName.setText(model.name);
        viewHolder.txtEmail.setText(model.email);

    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }
}
