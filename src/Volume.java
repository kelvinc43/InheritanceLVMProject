import java.util.UUID;
import java.util.ArrayList;

public class Volume {

    private String name;
    private int size;
    private String id;

    public Volume(String name, int size) {
        this.name = name;
        this.size = size;
        UUID a = UUID.randomUUID();
        id = a.toString() + "";
    }

    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getUUID() {
        return id;
    }
}
