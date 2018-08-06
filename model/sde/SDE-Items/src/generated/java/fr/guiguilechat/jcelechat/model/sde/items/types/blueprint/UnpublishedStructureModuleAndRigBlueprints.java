package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class UnpublishedStructureModuleAndRigBlueprints
    extends Blueprint
{

    @Override
    public int getGroupId() {
        return  1709;
    }

    @Override
    public Class<?> getGroup() {
        return UnpublishedStructureModuleAndRigBlueprints.class;
    }
}
