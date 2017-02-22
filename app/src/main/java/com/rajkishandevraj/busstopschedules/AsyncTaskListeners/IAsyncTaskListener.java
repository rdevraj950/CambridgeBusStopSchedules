package com.rajkishandevraj.busstopschedules.AsyncTaskListeners;

import android.content.Context;

public interface IAsyncTaskListener<T> {
    void OnPostExecuteComplete(Context context, T document);
}
