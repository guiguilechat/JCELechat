package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Minimum capacitor need for jump drive operation from full capacitor in modifier%.
 */
public class JumpDriveCapacitorNeed
    extends RealAttribute
{
    public static final JumpDriveCapacitorNeed INSTANCE = new JumpDriveCapacitorNeed();

    @Override
    public int getId() {
        return  898;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "JumpDriveCapacitorNeed";
    }
}
