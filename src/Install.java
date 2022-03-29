import java.util.Scanner;

public class Install {

    public void installHardDrive(String drive) {
        for (HardDrive i : HardDrive.getDriveList()) {
            if (drive.substring(0, drive.indexOf(" ")).equals(i.getName())) {
                System.out.println("That drive name already exists!\n");
                return;
            }
        }
        HardDrive newDrive = new HardDrive(drive.substring(0, drive.indexOf(" ")), Integer.parseInt(drive.substring(drive.indexOf(" ") + 1, drive.length() - 1)));
        System.out.println("Drive " + newDrive.getName() + " Installed!\n");
    }

    public String getDrives() {
        String list = "";
        for (HardDrive i : HardDrive.getDriveList()) {
            list += i.getName() + " [" + i.getSize() + "G]\n";
        }
        if (list.length() == 0) return "No drives!\n";
        return list;
    }

    public String installPhysicalVolume(String drive) {
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            if (drive.substring(0, drive.indexOf(" ")).equals(i.getName())) {
                return "That physical volume already exists!\n";
            }
        }
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        HardDrive d = null;
        for (HardDrive i : HardDrive.getDriveList()) {
            if (drive.equals(i.getName())) d = i;
        }
        if (d == null) return "No drive found named " + drive + "\n";
        if (d.getVolume() != null) return "Drive " + drive + " already has a physical volume!\n";
        PhysicalVolume newPV = new PhysicalVolume(name, d);
        d.setVolume(newPV);
        return name + " created!\n";
    }

    public String getPhysicalVolumes() {
        String list = "";
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            String temp = i.getName() + ": [" + i.getSize() + "G] ";
            if (i.getGroup() == null) temp += "[" + i.getUUID() + "]";
            else temp += "[" + i.getGroup().getName() + "] [" + i.getUUID() + "]";
            list += temp + "\n";
        }
        if (list.length() == 0) return "No Physical Volumes\n";
        return list;
    }

    public String installVolumeGroup(String drive) {
        for (VolumeGroup i : VolumeGroup.getGroups()) {
            if (drive.substring(0, drive.indexOf(" ")).equals(i.getName())) {
                return "That volume group already exists!\n";
            }
        }
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        PhysicalVolume d = null;
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            if (drive.equals(i.getName())) d = i;
        }
        if (d == null) return "No volume found named " + drive + "\n";
        if (d.getGroup() != null) return "Drive " + drive + " already has a physical volume!\n";

        VolumeGroup newVG = new VolumeGroup(name, d);
        return name + " created!\n";
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
        if (list.length() == 0) return "No Volume Groups\n";
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
            return drive + " does not exist.\n";
        }
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        PhysicalVolume volume = null;
        for (PhysicalVolume i : PhysicalVolume.getVolumes()) {
            if (name.equals(i.getName())) volume = i;
        }
        if (volume.getGroup() != null) {
            return name + " is already part of a volume group.\n";
        }
        if (volume == null) {
            return name + " is not an existing physical volume.\n";
        }
        group.addPV(volume);
        return name + "extended!\n";
    }
    public String installLogicalVolume(String drive) {
        String name = drive.substring(0, drive.indexOf(" "));
        drive = drive.substring(drive.indexOf(" ") + 1);
        String VG = drive.substring(drive.indexOf(" ") + 1);
        drive = drive.substring(0, drive.indexOf(" ") + 1);
        for (LogicalVolume i : LogicalVolume.getLogicalList()) {
            if (name.equals(i.getName())) {
                return "That Logical Volume already exists!\n";
            }
        }
        VolumeGroup d = null;
        for (VolumeGroup i : VolumeGroup.getGroups()) {
            if (VG.equals(i.getName())) d = i;
        }
        if (d == null) return "No volume group found named " + drive + "\n";
        int i = Integer.parseInt(name.substring(0, name.length() - 1));
        if (i > d.getStorage()) return "There is no storage remaining!\n";
        LogicalVolume v = new LogicalVolume(name, i, d);
        d.addLV(v);
        return name + " created!\n";
    }
    public String getLogicalVolume() {
        String list = "";
        for (LogicalVolume i : LogicalVolume.getLogicalList()) {
            list += i.getName() + ": [" + i.getSize() + "G] [" + i.getGroup().getName() + "] [" + i.getUUID() + "]\n";
        }
        if (list.length() == 0) return "No Logical Volumes!\n";
        return list;
    }
}

