package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Required skill level for skill 2
 */
public class RequiredSkill2Level
    extends IntAttribute
{
    public static final RequiredSkill2Level INSTANCE = new RequiredSkill2Level();

    @Override
    public int getId() {
        return  278;
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
        return "RequiredSkill2Level";
    }
}
