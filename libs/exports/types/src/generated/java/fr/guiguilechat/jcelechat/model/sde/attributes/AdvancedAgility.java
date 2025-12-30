package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute on ship to make advanced command affect only ships that we want.
 */
public class AdvancedAgility
    extends IntAttribute
{
    public static final AdvancedAgility INSTANCE = new AdvancedAgility();

    @Override
    public int getId() {
        return  853;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "AdvancedAgility";
    }
}
