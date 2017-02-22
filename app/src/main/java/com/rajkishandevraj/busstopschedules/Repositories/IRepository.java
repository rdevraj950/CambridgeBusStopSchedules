package com.rajkishandevraj.busstopschedules.Repositories;

import com.android.internal.util.Predicate;
import com.rajkishandevraj.busstopschedules.Bus.IBus;

public interface IRepository<T> {
    void Add(T entity);
    void Remove(T entity);
    T Find(Predicate<T> predicate);
}
