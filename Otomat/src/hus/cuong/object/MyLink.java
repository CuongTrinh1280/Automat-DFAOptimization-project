package hus.cuong.object;

public class MyLink {

    int id;
    String name;

    public MyLink(String name) {
        this.id++;
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
