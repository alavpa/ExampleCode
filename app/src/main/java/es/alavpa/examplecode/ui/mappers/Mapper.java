package es.alavpa.examplecode.ui.mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alavpa on 4/02/17.
 */

public abstract class Mapper<R, T> {

    public abstract R map(T source);

    public List<R> map(List<T> list) {
        ArrayList<R> dest = new ArrayList<>();
        for (T item : list) {
            dest.add(map(item));
        }

        return dest;
    }
}
