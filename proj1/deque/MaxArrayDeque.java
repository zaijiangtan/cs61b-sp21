package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        T maxItem = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (comparator.compare(this.get(i), maxItem) > 0) {
                maxItem = this.get(i);
            }
        }

        return maxItem;
    }

    public T max(Comparator<T> c) {
        T maxItem = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(this.get(i), maxItem) > 0) {
                maxItem = this.get(i);
            }
        }

        return maxItem;
    }
}
