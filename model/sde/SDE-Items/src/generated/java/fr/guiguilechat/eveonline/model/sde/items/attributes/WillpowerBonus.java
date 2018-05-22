package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * +/- bonus to the willpower of a character.
 */
public class WillpowerBonus
    extends IntAttribute
{
    public final static WillpowerBonus INSTANCE = new WillpowerBonus();

    @Override
    public int getId() {
        return  179;
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

    @Override
    public String toString() {
        return "WillpowerBonus";
    }
}
