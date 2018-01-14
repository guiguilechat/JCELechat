
package fr.guiguilechat.eveonline.model.sde.compiled.items.implant;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Implant;

public class CyberTrade
    extends Implant
{


    @Override
    public int getGroupId() {
        return  751;
    }

    @Override
    public Class<?> getGroup() {
        return CyberTrade.class;
    }

}
