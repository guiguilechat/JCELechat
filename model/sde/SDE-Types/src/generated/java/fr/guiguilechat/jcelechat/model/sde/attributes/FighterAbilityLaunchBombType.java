package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityLaunchBombType
    extends IntAttribute
{
    public static final FighterAbilityLaunchBombType INSTANCE = new FighterAbilityLaunchBombType();

    @Override
    public int getId() {
        return  2324;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "FighterAbilityLaunchBombType";
    }
}
