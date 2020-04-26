package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Tells if this type (ship) can be affected by the Rorqual Invulnerability Module
 */
public class AffectedByIndustrialInvulnModule
    extends IntAttribute
{
    public static final AffectedByIndustrialInvulnModule INSTANCE = new AffectedByIndustrialInvulnModule();

    @Override
    public int getId() {
        return  2464;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AffectedByIndustrialInvulnModule";
    }
}
