package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The remaining number of unused clone vats on the ship that are available for installation of jump clones.
 */
public class JumpClonesLeft
    extends IntAttribute
{
    public static final JumpClonesLeft INSTANCE = new JumpClonesLeft();

    @Override
    public int getId() {
        return  1336;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return true;
    }

    @Override
    public String toString() {
        return "JumpClonesLeft";
    }
}
