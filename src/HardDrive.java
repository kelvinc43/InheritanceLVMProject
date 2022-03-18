import java.util.ArrayList;
import java.util.Scanner;
public class HardDrive {
    private String name;
    private int size;
    private static ArrayList<HardDrive> driveList = new ArrayList<HardDrive>();
    private static int length;

    public HardDrive(String name, int size) {
        this.name = name;
        this.size = size;
        driveList.add(this);
        length++;
    }

    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public static ArrayList<HardDrive> getDriveList() { return driveList; }
    public int getIndex() { return length; }

}
