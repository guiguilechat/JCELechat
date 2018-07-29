package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class ComputerInterfaceNode
    extends Module
{

    @Override
    public int getGroupId() {
        return  317;
    }

    @Override
    public Class<?> getGroup() {
        return ComputerInterfaceNode.class;
    }
}
