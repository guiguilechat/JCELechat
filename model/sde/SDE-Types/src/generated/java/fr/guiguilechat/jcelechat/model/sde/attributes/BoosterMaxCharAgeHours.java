package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This attribute deactivates the booster after the character's age reaches a certain amount
 */
public class BoosterMaxCharAgeHours
    extends IntAttribute
{
    public static final BoosterMaxCharAgeHours INSTANCE = new BoosterMaxCharAgeHours();

    @Override
    public int getId() {
        return  1647;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BoosterMaxCharAgeHours";
    }
}
