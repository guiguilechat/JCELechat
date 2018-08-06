package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;

public class SleeperElectronicsRelics
    extends AncientRelics
{

    @Override
    public int getGroupId() {
        return  990;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperElectronicsRelics.class;
    }
}
