package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class RemoteRepDurationCapBonus
    extends RealAttribute
{
    public static final RemoteRepDurationCapBonus INSTANCE = new RemoteRepDurationCapBonus();

    @Override
    public int getId() {
        return  3024;
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
        return "RemoteRepDurationCapBonus";
    }
}
