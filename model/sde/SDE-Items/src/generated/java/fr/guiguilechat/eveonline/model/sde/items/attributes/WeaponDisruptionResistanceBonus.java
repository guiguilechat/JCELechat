package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class WeaponDisruptionResistanceBonus
    extends DoubleAttribute
{
    public final static WeaponDisruptionResistanceBonus INSTANCE = new WeaponDisruptionResistanceBonus();

    @Override
    public int getId() {
        return  2353;
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
}
