package hus.cuong.queue;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class FIFOQueueEnumerator<T> implements Enumeration<T> {

    Queue<T> queue;
    QueueElement<T> cursor;

    FIFOQueueEnumerator(Queue q) {
        queue = q;
        cursor = q.tail;
    }

    public boolean hasMoreElements() {
        return (cursor != null);
    }

    public T nextElement() {
        synchronized (queue) {
            if (cursor != null) {
                QueueElement<T> result = cursor;
                cursor = cursor.prev;
                return result.obj;
            }
        }
        throw new NoSuchElementException("FIFOQueueEnumerator");
    }
}
