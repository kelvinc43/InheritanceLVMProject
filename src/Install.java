import java.util.Scanner;

public class Install {

    public void installHardDrive() {
        boolean stop = false;
        while (stop == false) {
            Scanner a = new Scanner(System.in);
            String drive = a.nextLine();
            int ind = drive.indexOf(" ");
            if (drive.equals("exit")) stop = true;
            if (drive.substring(0, ind).equals("install-drive")) {
                drive = drive.substring(ind + 1);
                for (HardDrive i : HardDrive.getDriveList()) {
                    if (drive.substring(0, drive.indexOf(" ")).equals(i.getName())) {
                        System.out.println("That drive name already exists!");
                        return;
                    }
                }
                HardDrive newDrive = new HardDrive(drive.substring(0, drive.indexOf(" ")), Integer.parseInt(drive.substring(drive.indexOf(" ") + 1, drive.length() - 1)));

                System.out.println("Drive " + newDrive.getName() + " Installed!");
                System.out.println(newDrive.getSize());
            }

        }
    }
}
