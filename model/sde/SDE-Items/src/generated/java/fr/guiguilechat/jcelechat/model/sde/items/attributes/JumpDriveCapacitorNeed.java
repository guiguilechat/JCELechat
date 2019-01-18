package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Minimum capacitor need for jump drive operation from full capacitor in modifier%.
 */
public class JumpDriveCapacitorNeed
    extends DoubleAttribute
{
    public static final JumpDriveCapacitorNeed INSTANCE = new JumpDriveCapacitorNeed();

    @Override
    public int getId() {
        return  898;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
