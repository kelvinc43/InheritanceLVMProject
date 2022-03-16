import java.util.UUID;

public class Volume extends HardDrive {

    private UUID UUID;

    public Volume(String name, int size) {
        super(name, size);
        UUID = UUID.randomUUID();
    }

    public UUID getUUID() {
        return UUID;
    }
}
