package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Kinetic resistance bonus for shields
 */
public class ShieldKineticDamageResistanceBonus
    extends IntAttribute
{
    public final static ShieldKineticDamageResistanceBonus INSTANCE = new ShieldKineticDamageResistanceBonus();

    @Override
    public int getId() {
        return  1491;
    }

    @Override
    public int getCatId() {
        return  2;
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
