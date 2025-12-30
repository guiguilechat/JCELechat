package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class CovertOpsAndReconOpsCloakModuleDelay
    extends IntAttribute
{
    public static final CovertOpsAndReconOpsCloakModuleDelay INSTANCE = new CovertOpsAndReconOpsCloakModuleDelay();

    @Override
    public int getId() {
        return  1034;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
