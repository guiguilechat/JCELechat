package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Additional percentage to the characters missile damage multiplier.
 */
public class MissileDamageMultiplierBonus
    extends DoubleAttribute
{
    public final static MissileDamageMultiplierBonus INSTANCE = new MissileDamageMultiplierBonus();

    @Override
    public int getId() {
        return  213;
    }

    @Override
    public int getCatId() {
        return  30;
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
