package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ControlTowerProjectileROFBonus
    extends IntAttribute
{
    public static final ControlTowerProjectileROFBonus INSTANCE = new ControlTowerProjectileROFBonus();

    @Override
    public int getId() {
        return  754;
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
        return "ControlTowerProjectileROFBonus";
    }
}
