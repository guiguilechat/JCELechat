package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class StasisWebifierResistanceBonus
    extends IntAttribute
{
    public static final StasisWebifierResistanceBonus INSTANCE = new StasisWebifierResistanceBonus();

    @Override
    public int getId() {
        return  3422;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
        return "StasisWebifierResistanceBonus";
    }
}
