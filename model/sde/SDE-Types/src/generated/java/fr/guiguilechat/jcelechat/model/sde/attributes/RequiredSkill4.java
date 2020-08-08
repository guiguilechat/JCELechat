package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The type ID of the skill that is required.
 */
public class RequiredSkill4
    extends IntAttribute
{
    public static final RequiredSkill4 INSTANCE = new RequiredSkill4();

    @Override
    public int getId() {
        return  1285;
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
        return "RequiredSkill4";
    }
}
