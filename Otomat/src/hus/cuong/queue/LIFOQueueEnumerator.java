package hus.cuong.queue;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class LIFOQueueEnumerator<T> implements Enumeration<T> {

    Queue<T> queue;
    QueueElement<T> cursor;

    LIFOQueueEnumerator(Queue<T> q) {
        queue = q;
        cursor = q.head;
    }

    public boolean hasMoreElements() {
        return (cursor != null);
    }

    public T nextElement() {
        synchronized (queue) {
            if (cursor != null) {
                QueueElement<T> result = cursor;
                cursor = cursor.next;
                return result.obj;
            }
        }
        throw new NoSuchElementException("LIFOQueueEnumerator");
    }
}
