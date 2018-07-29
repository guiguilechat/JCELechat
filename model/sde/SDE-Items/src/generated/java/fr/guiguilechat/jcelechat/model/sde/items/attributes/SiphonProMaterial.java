package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Amount of Processed Materials stolen from active Simple Reactor Array every cycle.
 */
public class SiphonProMaterial
    extends IntAttribute
{
    public final static SiphonProMaterial INSTANCE = new SiphonProMaterial();

    @Override
    public int getId() {
        return  1929;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SiphonProMaterial";
    }
}
