import java.util.ArrayList;
import java.util.Scanner;
public class HardDrive {
    private String name;
    private int size;
    private Volume volume;
    private static ArrayList<HardDrive> driveList = new ArrayList<HardDrive>();


    public HardDrive(String name, int size) {
        this.name = name;
        this.size = size;
        driveList.add(this);
    }

    public String getName() {
        return name;
    }
    public Volume getVolume() { return volume; }
    public void setVolume(Volume v) { volume = v; }
    public int getSize() {
        return size;
    }
    public static ArrayList<HardDrive> getDriveList() { return driveList; }


}
