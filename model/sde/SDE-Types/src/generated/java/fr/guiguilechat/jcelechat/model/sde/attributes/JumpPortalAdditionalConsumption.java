package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Additional units of fuel that are consumed on each jump through a jump portal, not subject to any of the mass or distance multipliers
 */
public class JumpPortalAdditionalConsumption
    extends IntAttribute
{
    public static final JumpPortalAdditionalConsumption INSTANCE = new JumpPortalAdditionalConsumption();

    @Override
    public int getId() {
        return  2793;
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
        return "JumpPortalAdditionalConsumption";
    }
}
