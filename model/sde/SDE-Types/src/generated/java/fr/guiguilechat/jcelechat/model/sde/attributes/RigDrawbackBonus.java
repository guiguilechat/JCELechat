package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class RigDrawbackBonus
    extends IntAttribute
{
    public static final RigDrawbackBonus INSTANCE = new RigDrawbackBonus();

    @Override
    public int getId() {
        return  1139;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  10.0;
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
        return "RigDrawbackBonus";
    }
}
