package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Required skill level for skill 3
 */
public class RequiredSkill3Level
    extends IntAttribute
{
    public static final RequiredSkill3Level INSTANCE = new RequiredSkill3Level();

    @Override
    public int getId() {
        return  279;
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
        return "RequiredSkill3Level";
    }
}
