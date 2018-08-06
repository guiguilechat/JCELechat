package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import fr.guiguilechat.jcelechat.model.sde.items.types.Module;

public class SignatureScrambling
    extends Module
{

    @Override
    public int getGroupId() {
        return  341;
    }

    @Override
    public Class<?> getGroup() {
        return SignatureScrambling.class;
    }
}
