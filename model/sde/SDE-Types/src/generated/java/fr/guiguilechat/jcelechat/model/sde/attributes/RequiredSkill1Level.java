package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Required skill level for skill 1
 */
public class RequiredSkill1Level
    extends IntAttribute
{
    public static final RequiredSkill1Level INSTANCE = new RequiredSkill1Level();

    @Override
    public int getId() {
        return  277;
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
        return "RequiredSkill1Level";
    }
}
