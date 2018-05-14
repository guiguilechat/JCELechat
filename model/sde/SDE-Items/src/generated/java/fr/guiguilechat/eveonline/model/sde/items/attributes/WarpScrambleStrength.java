package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Amount to modify ships warp scramble status by.
 */
public class WarpScrambleStrength
    extends IntAttribute
{
    public final static WarpScrambleStrength INSTANCE = new WarpScrambleStrength();

    @Override
    public int getId() {
        return  105;
    }

    @Override
    public int getCatId() {
        return  27;
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
}
