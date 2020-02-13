package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The skill required to reprocess this ore type.
 */
public class ReprocessingSkillType
    extends IntAttribute
{
    public static final ReprocessingSkillType INSTANCE = new ReprocessingSkillType();

    @Override
    public int getId() {
        return  790;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "ReprocessingSkillType";
    }
}
