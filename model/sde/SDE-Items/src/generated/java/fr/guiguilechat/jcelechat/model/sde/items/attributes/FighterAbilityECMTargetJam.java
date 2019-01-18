package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterAbilityECMTargetJam
    extends IntAttribute
{
    public static final FighterAbilityECMTargetJam INSTANCE = new FighterAbilityECMTargetJam();

    @Override
    public int getId() {
        return  2251;
    }

    @Override
    public int getCatId() {
        return  34;
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
        return "FighterAbilityECMTargetJam";
    }
}
