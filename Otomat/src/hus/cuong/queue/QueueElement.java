package hus.cuong.queue;

public class QueueElement<T> {

    QueueElement<T> next = null;
    QueueElement<T> prev = null;

    T obj = null;

    QueueElement(T obj) {
        this.obj = obj;
    }

    public String toString() {
        return "QueueElement[obj="+obj+(prev == null ? " null" : " prev")+
                (next == null ? " null" : " next")+"]";
    }
}
