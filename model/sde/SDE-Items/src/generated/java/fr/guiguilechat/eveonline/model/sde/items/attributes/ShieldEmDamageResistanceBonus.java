package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * EM resistance bonus for shields
 */
public class ShieldEmDamageResistanceBonus
    extends IntAttribute
{
    public final static ShieldEmDamageResistanceBonus INSTANCE = new ShieldEmDamageResistanceBonus();

    @Override
    public int getId() {
        return  1489;
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

    @Override
    public String toString() {
        return "ShieldEmDamageResistanceBonus";
    }
}
