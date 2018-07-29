package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class CovertOpsAndReconOpsCloakModuleDelay
    extends IntAttribute
{
    public final static CovertOpsAndReconOpsCloakModuleDelay INSTANCE = new CovertOpsAndReconOpsCloakModuleDelay();

    @Override
    public int getId() {
        return  1034;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  30000.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "CovertOpsAndReconOpsCloakModuleDelay";
    }
}
