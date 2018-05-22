package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class RemoteShieldBoosterFalloffBonus
    extends IntAttribute
{
    public final static RemoteShieldBoosterFalloffBonus INSTANCE = new RemoteShieldBoosterFalloffBonus();

    @Override
    public int getId() {
        return  2693;
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
        return "RemoteShieldBoosterFalloffBonus";
    }
}
