package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerHybridDamageBonus
    extends IntAttribute
{
    public static final ControlTowerHybridDamageBonus INSTANCE = new ControlTowerHybridDamageBonus();

    @Override
    public int getId() {
        return  766;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ControlTowerHybridDamageBonus";
    }
}
