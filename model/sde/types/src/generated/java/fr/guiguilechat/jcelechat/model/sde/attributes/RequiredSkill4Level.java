package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Required skill level for skill 4
 */
public class RequiredSkill4Level
    extends IntAttribute
{
    public static final RequiredSkill4Level INSTANCE = new RequiredSkill4Level();

    @Override
    public int getId() {
        return  1286;
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
        return "RequiredSkill4Level";
    }
}
