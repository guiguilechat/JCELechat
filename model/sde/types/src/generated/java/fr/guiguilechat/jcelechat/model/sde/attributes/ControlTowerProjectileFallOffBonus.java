package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerProjectileFallOffBonus
    extends IntAttribute
{
    public static final ControlTowerProjectileFallOffBonus INSTANCE = new ControlTowerProjectileFallOffBonus();

    @Override
    public int getId() {
        return  753;
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
        return "ControlTowerProjectileFallOffBonus";
    }
}
