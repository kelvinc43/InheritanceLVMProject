import java.util.ArrayList;

public class VolumeGroup  extends Volume{

    private ArrayList<PhysicalVolume> PV;
    private ArrayList<LogicalVolume> LV;
    private int CurrentStorage;
    private static ArrayList<VolumeGroup> groups = new ArrayList<VolumeGroup>();

    public VolumeGroup(String name, PhysicalVolume PV) {
        super(name, PV.getSize());
        this.PV = new ArrayList<PhysicalVolume>();
        this.PV.add(PV);
        PV.setVolumeGroup(this);
        this.LV = new ArrayList<LogicalVolume>();
        CurrentStorage = 0;
        groups.add(this);
    }

    public ArrayList<PhysicalVolume> getPV() {
        return PV;
    }
    public ArrayList<LogicalVolume> getLV() {
        return LV;
    }
    public void addPV(PhysicalVolume PV) {
        this.PV.add(PV);
        PV.setVolumeGroup(this);
    }
    public void addLV(LogicalVolume LV) {
        this.LV.add(LV);
        LV.setGroup(this);
    }
    public static ArrayList<VolumeGroup> getGroups() {
        return groups;
    }
    public void changeSize() {
        int space = 0;
        for (PhysicalVolume i : PV) {
            space += i.getSize();
        }
        setSize(space);
    }
    public void updateStorage() {
        int space = 0;
        for (LogicalVolume i : LV) {
            space += i.getSize();
        }
        CurrentStorage = getSize() - space;
    }
    public int getStorage() {
        changeSize();
        updateStorage();
        return CurrentStorage;
    }
    public int getSize() {
        changeSize();
        return super.getSize();
    }
}
