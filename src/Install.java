import java.util.Scanner;

public class Install {

    public void installHardDrive(String drive) {
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

    public String getDrives() {
        String list = "";
        for (HardDrive i : HardDrive.getDriveList()) {
            list += i.getName() + " [" + i.getSize() + "G]\n";
        }
        if (list.length() == 0) return "No drives";
        return list;
    }

    public String installPhysicalVolume(String drive) {
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            if (drive.substring(0, drive.indexOf(" ")).equals(i.getName())) {
                return "That physical volume already exists!";
            }
        }
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        HardDrive d = null;
        for (HardDrive i : HardDrive.getDriveList()) {
            if (drive.equals(i.getName())) d = i;
        }
        if (d == null) return "No drive found named " + drive;
        if (d.getVolume() != null) return "Drive " + drive + " already has a physical volume!";

        PhysicalVolume newPV = new PhysicalVolume(name, d);
        d.setVolume(newPV);
        return name + " created!";
    }

    public String getPhysicalVolumes() {
        String list = "";
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            String temp = i.getName() + ": [" + i.getSize() + "G] ";
            if (i.getGroup() == null) temp += "[" + i.getUUID() + "]";
            else temp += "[" + i.getGroup().getName() + "] [" + i.getUUID() + "]";
            list += temp + "\n";
        }
        if (list.length() == 0) return "No Physical Volumes";
        return list;
    }

    public String installVolumeGroup(String drive) {
        for (VolumeGroup i : VolumeGroup.getGroups()) {
            if (drive.substring(0, drive.indexOf(" ")).equals(i.getName())) {
                return "That volume group already exists!";
            }
        }
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        PhysicalVolume d = null;
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            if (drive.equals(i.getName())) d = i;
        }
        if (d == null) return "No volume found named " + drive;
        if (d.getGroup() != null) return "Drive " + drive + " already has a physical volume!";

        VolumeGroup newVG = new VolumeGroup(name, d);
        return name + " created!";
    }
    public String getVolumeGroups() {
        String list = "";
        for (VolumeGroup i : VolumeGroup.getGroups()) {
            list += i.getName() + ": total:[" + i.getSize() + "G] available:[" + i.getStorage() + "G] [";
            for (PhysicalVolume v : i.getPV()) {
                list += v.getName() + ",";
            }
            list = list.substring(0, list.length() -1);
            if (i.getLV().size() > 0) {
                list += "] [";
                for (LogicalVolume l : i.getLV()) { list += l.getName() + ","; }
                list = list.substring(0, list.length() - 1);
            }
            list += "] [" + i.getUUID() + "]\n";
        }
        if (list.length() == 0) return "No Volume Groups";
        return list;
    }
    public String extendVolumeGroup(String drive) {
        VolumeGroup group = null;
        for (VolumeGroup i : VolumeGroup.getGroups()) {
            if (drive.substring(0, drive.indexOf(" ")).equals(i.getName())) {
                group = i;
            }
        }
        if (group == null) {
            return drive + " does not exist.";
        }
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        PhysicalVolume volume = null;
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            if (name.equals(i.getName())) volume = i;
        }
        if (volume.getGroup() != null) {
            return name + " is already part of a volume group.";
        }
        if (volume == null) {
            return name + " is not an existing physical volume";
        }
        group.addPV(volume);
        return name + "extended!";
    }
    public String installLogicalVolume(String drive) {
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        String VG = drive.substring(drive.indexOf(" ") + 1);
        drive = drive.substring(0, drive.indexOf(" ") + 1);
        for (LogicalVolume i : LogicalVolume.getLogicalList()) {
            if (name.equals(i.getName())) {
                return "That Logical Volume already exists!";
            }
        }
        VolumeGroup d = null;
        for (VolumeGroup i : VolumeGroup.getGroups()) {
            if (VG.equals(i.getName())) d = i;
        }
        if (d == null) return "No volume group found named " + drive;
        int i = Integer.parseInt(name.substring(0, name.length() - 1));
        if (i > d.getStorage()) return "There is no storage remaining!";
        LogicalVolume v = new LogicalVolume(i, name, d);



        VolumeGroup newVG = new VolumeGroup(name, d);
        return name + " created!";
    }
}

