package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplier for jump fatigue distance
 */
public class JumpFatigueMultiplier
    extends DoubleAttribute
{
    public final static JumpFatigueMultiplier INSTANCE = new JumpFatigueMultiplier();

    @Override
    public int getId() {
        return  1971;
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
}
