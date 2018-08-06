package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class SensorBackupArray
    extends Module
{

    @Override
    public int getGroupId() {
        return  203;
    }

    @Override
    public Class<?> getGroup() {
        return SensorBackupArray.class;
    }
}
