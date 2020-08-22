package com.johnosezele.keepnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// We create a NoteRecyclerAdapter to handle RecyclerView, this class NoteRecyclerAdapter.ViewHolder is used to hold info
// about the individual views.
public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final List<NoteInfo> mNotes; //Create field that'll hold list of notes.
    private final LayoutInflater mLayoutInflater; //In order to create views from context we use the LayoutInflater class

    public NoteRecyclerAdapter(Context context, List<NoteInfo> notes) {
       //constructor parameters for mContext, mNotes & mLayoutInflater fields
        mContext = context;
        mNotes = notes;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    // onCreateViewHolder() method creates our ViewHolder instances.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //onCreateViewHolder() method inflates our view, passes it into our ViewHolder class and
        //returns it back so that it can go into the pool so that the
        // RecyclerView recycles and calls them into onBindViewHolder() method.

        //Inflating the View
        View itemView = mLayoutInflater. inflate(R.layout.item_note_list, parent, false);
        //Returning a new ViewHolder instance
        return new ViewHolder(itemView);
    }           //summary of what onCreateViewHolder() method does:
    //onCreateViewHolder() method inflates the view, returns a new ViewHolder
    // instance and associates the view with that ViewHolder instance.

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //  onBindViewHolder() method associates(binds) data with our views within a ViewHolder.

        NoteInfo note = mNotes.get(position); //First we get note that correspond to a particular position.

        // Then we get each of the TextView(mTextCourse & mTextTitle) from our ViewHolder.
       holder.mTextCourse.setText(note.getCourse().getTitle());
       holder.mTextTitle.setText(note.getTitle());

       //this is called each time we set the value for a given position
       // i.e when the ViewHolder is associated with a different set of data.
        holder.mId = note.getId();
    }

    @Override
    public int getItemCount() {
    // getItemCount() method indicates the no. of data items we have.
        return mNotes.size(); //mNotes.size() method Returns size of note.
    }


//          initiate class that holds views within the recycler adapter i.e the ViewHolder class.
    public class ViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder class receives a view from onCreateViewHolder() method and
       // gets reference to the TextViews inside of it.


// mTextView & mTextTitle fields are made public because we want our outer class
// NoteRecyclerAdapter to be able to reference those fields directly.
        public final TextView mTextCourse;
        public final TextView mTextTitle;

//To know the position in which the current ViewHolder is associated with we
// add a field for the current position. This field wont be final because it would
// have to be set each time the ViewHolder is associated with a different data item.
        public int mId;
//We set mCurrentPosition each time a this ViewHolder is associated with a different set of Data
// this is done inside the onBindViewHolder() method.


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextCourse = itemView.findViewById(R.id.text_course);
            mTextTitle = itemView.findViewById(R.id.text_title);

//Associate ClickEventHandlerWith the itemView
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setup intent to show our NoteActivity
                //pass in a context(mContext) which is part of our NoteRecyclerAdapter class's field,
                //and the class for the activity(NoteActivity.class).
                Intent intent = new Intent(mContext, NoteActivity.class);

                //Next set the extra for the NotePosition
                //pass in the NotePosition's extra name and our mCurrentPosition.
                intent.putExtra(NoteActivity.NOTE_ID, mId);

                //Start our Activity
                mContext.startActivity(intent);
            }
        });
        }
    }
}
