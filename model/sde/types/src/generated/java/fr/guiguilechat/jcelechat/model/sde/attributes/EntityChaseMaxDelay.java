package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The maximum amount of time stalled before entity chase speed kicks in.
 */
public class EntityChaseMaxDelay
    extends RealAttribute
{
    public static final EntityChaseMaxDelay INSTANCE = new EntityChaseMaxDelay();

    @Override
    public int getId() {
        return  580;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  5000.0;
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
        return "EntityChaseMaxDelay";
    }
}
