import java.util.UUID;
import java.util.ArrayList;

public class Volume {

    private String name;
    private int size;
    private UUID UUID;
    private static ArrayList<Volume> volumeList = new ArrayList<Volume>();
    private HardDrive drive;

    public Volume(String name, HardDrive drive) {
        volumeList.add(this);
        UUID = UUID.randomUUID();

    }

    public void createVolume() {

    }
    public UUID getUUID() {
        return UUID;
    }
}
