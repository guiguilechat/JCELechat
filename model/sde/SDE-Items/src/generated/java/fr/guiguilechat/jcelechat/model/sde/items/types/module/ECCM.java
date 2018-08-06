package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class ECCM
    extends Module
{

    @Override
    public int getGroupId() {
        return  202;
    }

    @Override
    public Class<?> getGroup() {
        return ECCM.class;
    }
}
