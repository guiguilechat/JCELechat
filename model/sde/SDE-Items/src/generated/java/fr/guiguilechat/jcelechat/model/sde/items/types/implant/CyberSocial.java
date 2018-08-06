package fr.guiguilechat.jcelechat.model.sde.items.types.implant;

import fr.guiguilechat.jcelechat.model.sde.items.types.Implant;

public class CyberSocial
    extends Implant
{

    @Override
    public int getGroupId() {
        return  750;
    }

    @Override
    public Class<?> getGroup() {
        return CyberSocial.class;
    }
}
