import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean stop = false;
        while (stop != true) {
            Scanner s = new Scanner(System.in);
            System.out.print("cmd# ");
            String input = s.nextLine();
            if (input.equals("exit")) {
                System.out.println("Good-bye!");
                stop = true;
                break;
            }

            int ind = input.indexOf(" ");
            if (ind != -1 && input.substring(0, ind).equals("install-drive")) {
                String drive = input.substring(ind + 1);
                Install a = new Install();
                a.installHardDrive(drive);
            }
            else if (input.equals("list-drives")) {
                Install a = new Install();
                System.out.println(a.getDrives());
            }
            else if (ind != -1 && input.substring(0, ind).equals("pvcreate")) {
                String drive = input.substring(ind + 1);
                Install a = new Install();
                System.out.println(a.installPhysicalVolume(drive));
            }
            else if (input.equals("pvlist")) {
                Install a = new Install();
                System.out.println(a.getPhysicalVolumes());
            }
            else if (ind != -1 && input.substring(0, ind).equals("vgcreate")) {
                String drive = input.substring(ind + 1);
                Install a = new Install();
                System.out.println(a.installVolumeGroup(drive));
            }
            else if (input.equals("vglist")) {
                Install a = new Install();
                System.out.println(a.getVolumeGroups());
            }
            else if (ind != -1 && input.substring(0, ind).equals("vgextend")) {
                String drive = input.substring(ind + 1);
                Install a = new Install();
                System.out.println(a.extendVolumeGroup(drive));
            }
            else if (ind != -1 && input.substring(0, ind).equals("lvcreate")) {
                String drive = input.substring(ind + 1);
                Install a = new Install();
                System.out.println(a.installLogicalVolume(drive));
            }
            else if (input.equals("lvlist")) {
                Install a = new Install();
                System.out.println(a.getLogicalVolume());
            }
            else System.out.println("That is not a command!\n");
        }
    }
}
