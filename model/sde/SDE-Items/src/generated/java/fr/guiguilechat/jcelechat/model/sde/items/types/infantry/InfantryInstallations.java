package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryInstallations
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  354753;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryInstallations.class;
    }
}
