package com.johnosezele.keepnote;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class NoteActivityViewModel extends ViewModel {
    public static final String ORIGINAL_NOTE_COURSE_ID = "com.johnosezele.keepnote.ORIGINAL_NOTE_COURSE_ID";
    public static final String ORIGINAL_NOTE_TITLE = "com.johnosezele.keepnote.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_TEXT = "com.johnosezele.keepnote.ORIGINAL_NOTE_TEXT";

    public String mOriginalNoteCourseId;
    public String mMOriginalNoteTitle;
    public String mMOriginalNoteText;
    public boolean mIsNewlyCreated = true;

    public void saveState(Bundle outState) {
        outState.putString(ORIGINAL_NOTE_COURSE_ID, mOriginalNoteCourseId);
        outState.putString(ORIGINAL_NOTE_TITLE, mMOriginalNoteTitle);
        outState.putString(ORIGINAL_NOTE_TEXT, mMOriginalNoteText);
    }

    public void restoreState(Bundle inState) {
        mOriginalNoteCourseId = inState.getString(ORIGINAL_NOTE_COURSE_ID);
        mMOriginalNoteTitle = inState.getString(ORIGINAL_NOTE_TITLE);
        mMOriginalNoteText = inState.getString(ORIGINAL_NOTE_TEXT);
    }
}
