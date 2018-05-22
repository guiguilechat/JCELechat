package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Tells if this type (ship) can be affected by the Rorqual Invulnerability Module
 */
public class AffectedByIndustrialInvulnModule
    extends IntAttribute
{
    public final static AffectedByIndustrialInvulnModule INSTANCE = new AffectedByIndustrialInvulnModule();

    @Override
    public int getId() {
        return  2464;
    }

    @Override
    public int getCatId() {
        return  37;
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
