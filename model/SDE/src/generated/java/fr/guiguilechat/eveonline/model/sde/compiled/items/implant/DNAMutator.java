
package fr.guiguilechat.eveonline.model.sde.compiled.items.implant;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Implant;

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
