package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * role bonus for command destroyers
 */
public class RoleBonusCD
    extends IntAttribute
{
    public static final RoleBonusCD INSTANCE = new RoleBonusCD();

    @Override
    public int getId() {
        return  2064;
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
        return "RoleBonusCD";
    }
}
