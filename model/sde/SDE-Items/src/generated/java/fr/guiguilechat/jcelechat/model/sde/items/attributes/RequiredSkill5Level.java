package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Required skill level for skill 5
 */
public class RequiredSkill5Level
    extends IntAttribute
{
    public static final RequiredSkill5Level INSTANCE = new RequiredSkill5Level();

    @Override
    public int getId() {
        return  1287;
    }

    @Override
    public int getCatId() {
        return  8;
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
        return "RequiredSkill5Level";
    }
}
