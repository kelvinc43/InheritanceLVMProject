import java.util.ArrayList;

public class PhysicalVolume extends Volume {
    private HardDrive drive;
    private static ArrayList<PhysicalVolume> volumeList = new ArrayList<PhysicalVolume>();
    private VolumeGroup group;

    public PhysicalVolume(String name, HardDrive drive) {
        super (name, drive.getSize());
        this.drive = drive;
        volumeList.add(this);
    }

    public HardDrive getDrive() {
        return drive;
    }
    public static ArrayList<PhysicalVolume> getVolumes() {
        return volumeList;
    }
    public VolumeGroup getGroup() { return group; }

    public void setDrive(HardDrive drive) {
        this.drive = drive;
        setSize(drive.getSize());
    }

    public boolean setVolumeGroup(VolumeGroup group) {
        if (group != null) return false;
        else group = group; return true;
    }
}
