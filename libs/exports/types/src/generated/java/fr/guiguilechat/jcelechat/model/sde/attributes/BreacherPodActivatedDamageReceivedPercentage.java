package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class BreacherPodActivatedDamageReceivedPercentage
    extends IntAttribute
{
    public static final BreacherPodActivatedDamageReceivedPercentage INSTANCE = new BreacherPodActivatedDamageReceivedPercentage();

    @Override
    public int getId() {
        return  6257;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "BreacherPodActivatedDamageReceivedPercentage";
    }
}
