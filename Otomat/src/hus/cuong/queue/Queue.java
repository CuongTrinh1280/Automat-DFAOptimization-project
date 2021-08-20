package hus.cuong.queue;

import java.util.Enumeration;

public class Queue<T> {

    int length = 0;

    QueueElement<T> head = null;
    QueueElement<T> tail = null;

    public Queue() {
    }

    /**
     * Enqueue an object.
     */
    public synchronized void enqueue(T obj) {

        QueueElement<T> newElt = new QueueElement<>(obj);

        if (head == null) {
            head = newElt;
            tail = newElt;
            length = 1;
        } else {
            newElt.next = head;
            head.prev = newElt;
            head = newElt;
            length++;
        }
        notify();
    }

    /**
     * Dequeue the oldest object on the queue.  Will wait indefinitely.
     *
     * @return the oldest object on the queue.
     * @throws java.lang.InterruptedException if any thread has
     *                                        interrupted this thread.
     */
    public T dequeue() throws InterruptedException {
        return dequeue(0L);
    }

    /**
     * Dequeue the oldest object on the queue.
     *
     * @param timeOut the number of milliseconds to wait for something
     *                to arrive.
     * @return the oldest object on the queue.
     * @throws java.lang.InterruptedException if any thread has
     *                                        interrupted this thread.
     */
    public synchronized T dequeue(long timeOut)
            throws InterruptedException {

        while (tail == null) {
            wait(timeOut);
        }
        QueueElement<T> elt = tail;
        tail = elt.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        length--;
        return elt.obj;
    }

    /**
     * Is the queue empty?
     *
     * @return true if the queue is empty.
     */
    public synchronized boolean isEmpty() {
        return (tail == null);
    }

    /**
     * Returns an enumeration of the elements in Last-In, First-Out
     * order. Use the Enumeration methods on the returned object to
     * fetch the elements sequentially.
     */
    public final synchronized Enumeration<T> elements() {
        return new LIFOQueueEnumerator<>(this);
    }

    /**
     * Returns an enumeration of the elements in First-In, First-Out
     * order. Use the Enumeration methods on the returned object to
     * fetch the elements sequentially.
     */
    public final synchronized Enumeration<T> reverseElements() {
        return new FIFOQueueEnumerator<>(this);
    }

    public synchronized void dump(String msg) {
        System.err.println(">> " + msg);
        System.err.println("[" + length + " elt(s); head = " +
                (head == null ? "null" : (head.obj) + "") +
                " tail = " + (tail == null ? "null" : (tail.obj) + ""));
        QueueElement<T> cursor = head;
        QueueElement<T> last = null;
        while (cursor != null) {
            System.err.println("  " + cursor);
            last = cursor;
            cursor = cursor.next;
        }
        if (last != tail) {
            System.err.println("  tail != last: " + tail + ", " + last);
        }
        System.err.println("]");
    }
}
