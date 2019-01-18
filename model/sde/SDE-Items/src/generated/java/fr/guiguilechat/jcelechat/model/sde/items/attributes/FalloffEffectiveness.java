package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * distance from maximum range at which effectiveness has fallen by half
 */
public class FalloffEffectiveness
    extends IntAttribute
{
    public static final FalloffEffectiveness INSTANCE = new FalloffEffectiveness();

    @Override
    public int getId() {
        return  2044;
    }

    @Override
    public int getCatId() {
        return  29;
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
        return false;
    }

    @Override
    public String toString() {
        return "FalloffEffectiveness";
    }
}
