package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Range at which the fighters Explode from the target
 */
public class FighterAbilityKamikazeRange
    extends IntAttribute
{
    public static final FighterAbilityKamikazeRange INSTANCE = new FighterAbilityKamikazeRange();

    @Override
    public int getId() {
        return  2330;
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
        return  500.0;
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
        return "FighterAbilityKamikazeRange";
    }
}
