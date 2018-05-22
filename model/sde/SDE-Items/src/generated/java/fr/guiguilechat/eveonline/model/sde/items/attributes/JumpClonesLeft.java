package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The remaining number of unused clone vats on the ship that are available for installation of jump clones.
 */
public class JumpClonesLeft
    extends IntAttribute
{
    public final static JumpClonesLeft INSTANCE = new JumpClonesLeft();

    @Override
    public int getId() {
        return  1336;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "JumpClonesLeft";
    }
}
