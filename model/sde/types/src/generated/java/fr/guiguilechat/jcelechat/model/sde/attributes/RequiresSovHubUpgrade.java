package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class RequiresSovHubUpgrade
    extends IntAttribute
{
    public static final RequiresSovHubUpgrade INSTANCE = new RequiresSovHubUpgrade();

    @Override
    public int getId() {
        return  5688;
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
        return "RequiresSovHubUpgrade";
    }
}
