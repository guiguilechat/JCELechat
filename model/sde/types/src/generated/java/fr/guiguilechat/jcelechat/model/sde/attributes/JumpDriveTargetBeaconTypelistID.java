package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Pointer to type-list that describes which beacons a ship's jump drive can connect to
 */
public class JumpDriveTargetBeaconTypelistID
    extends IntAttribute
{
    public static final JumpDriveTargetBeaconTypelistID INSTANCE = new JumpDriveTargetBeaconTypelistID();

    @Override
    public int getId() {
        return  3317;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "JumpDriveTargetBeaconTypelistID";
    }
}
