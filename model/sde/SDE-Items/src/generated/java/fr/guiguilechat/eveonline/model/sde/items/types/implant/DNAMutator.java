
package fr.guiguilechat.eveonline.model.sde.items.types.implant;

import fr.guiguilechat.eveonline.model.sde.items.types.Implant;

public class DNAMutator
    extends Implant
{


    @Override
    public int getGroupId() {
        return  304;
    }

    @Override
    public Class<?> getGroup() {
        return DNAMutator.class;
    }

}
