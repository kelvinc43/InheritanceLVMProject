import java.util.ArrayList;

public class LogicalVolume extends Volume{
    private VolumeGroup group;
    private static ArrayList<LogicalVolume> logicalList = new ArrayList<LogicalVolume>();

    public LogicalVolume(String name, int size, VolumeGroup VG) {
        super (name, size);
        this.group = VG;
        logicalList.add(this);
    }

    public VolumeGroup getGroup() {
        return group;
    }
    public void setGroup(VolumeGroup group) {
        this.group = group;
    }
    public static ArrayList<LogicalVolume> getLogicalList() {
        return logicalList;
    }
}
