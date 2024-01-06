package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class DisruptionLanceSkillBoostCapacitorCost
    extends IntAttribute
{
    public static final DisruptionLanceSkillBoostCapacitorCost INSTANCE = new DisruptionLanceSkillBoostCapacitorCost();

    @Override
    public int getId() {
        return  5419;
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
        return "DisruptionLanceSkillBoostCapacitorCost";
    }
}
