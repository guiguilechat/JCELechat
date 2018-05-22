package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The type ID of the skill that is required.
 */
public class RequiredSkill4
    extends IntAttribute
{
    public final static RequiredSkill4 INSTANCE = new RequiredSkill4();

    @Override
    public int getId() {
        return  1285;
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
        return "RequiredSkill4";
    }
}
