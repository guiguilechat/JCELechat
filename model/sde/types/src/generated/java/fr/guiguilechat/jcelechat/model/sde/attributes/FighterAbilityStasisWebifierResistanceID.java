package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class FighterAbilityStasisWebifierResistanceID
    extends IntAttribute
{
    public static final FighterAbilityStasisWebifierResistanceID INSTANCE = new FighterAbilityStasisWebifierResistanceID();

    @Override
    public int getId() {
        return  2188;
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
        return "FighterAbilityStasisWebifierResistanceID";
    }
}
