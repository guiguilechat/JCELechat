package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Damage multiplier for smart bombs
 */
public class SmartbombDamageMultiplier
    extends DoubleAttribute
{
    public final static SmartbombDamageMultiplier INSTANCE = new SmartbombDamageMultiplier();

    @Override
    public int getId() {
        return  1488;
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
}
