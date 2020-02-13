package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Rig Bonus that affects fracture delay for moon chunk
 */
public class MoonRigFractureDelayBonus
    extends IntAttribute
{
    public static final MoonRigFractureDelayBonus INSTANCE = new MoonRigFractureDelayBonus();

    @Override
    public int getId() {
        return  2707;
    }

    @Override
    public int getCatId() {
        return  37;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
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
        return "MoonRigFractureDelayBonus";
    }
}
