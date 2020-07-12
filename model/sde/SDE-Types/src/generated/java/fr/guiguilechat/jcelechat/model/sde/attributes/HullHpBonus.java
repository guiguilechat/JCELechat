package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Autogenerated skill attribute, hullHpBonus
 */
public class HullHpBonus
    extends DoubleAttribute
{
    public static final HullHpBonus INSTANCE = new HullHpBonus();

    @Override
    public int getId() {
        return  327;
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
        return "HullHpBonus";
    }
}