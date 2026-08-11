package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ConduitPassengerBonusModAddSkill
    extends IntAttribute
{
    public static final ConduitPassengerBonusModAddSkill INSTANCE = new ConduitPassengerBonusModAddSkill();

    @Override
    public int getId() {
        return  6350;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  5.0;
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
        return "ConduitPassengerBonusModAddSkill";
    }
}
