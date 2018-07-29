package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The type ID of the skill that is required.
 */
public class RequiredSkill1
    extends IntAttribute
{
    public final static RequiredSkill1 INSTANCE = new RequiredSkill1();

    @Override
    public int getId() {
        return  182;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RequiredSkill1";
    }
}
