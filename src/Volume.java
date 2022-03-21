import java.util.UUID;
import java.util.ArrayList;

public class Volume {

    private String name;
    private int size;
    private UUID UUID;
    private static ArrayList<Volume> volumeList = new ArrayList<Volume>();
    private HardDrive drive;

    public Volume(String name, HardDrive drive) {
        for (HardDrive d : HardDrive.getDriveList()) {
            if (!d.getName().equals(drive.getName()))
            if (getHardDrive() == null) volumeList.add(this);
        }
        UUID = UUID.randomUUID();

    }

    public void createVolume() {

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
    public HardDrive getHardDrive() {
        return drive;
    }
    public UUID getUUID() {
        return UUID;
    }
}
