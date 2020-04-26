package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * When set to 1 this attribute allows Spawn Containers to refill and relock. 
 */
public class AllowRefills
    extends IntAttribute
{
    public static final AllowRefills INSTANCE = new AllowRefills();

    @Override
    public int getId() {
        return  2019;
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
        return "AllowRefills";
    }
}
