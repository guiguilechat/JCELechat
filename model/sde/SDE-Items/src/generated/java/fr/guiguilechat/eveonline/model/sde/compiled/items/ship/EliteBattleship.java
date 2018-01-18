
package fr.guiguilechat.eveonline.model.sde.compiled.items.ship;

import fr.guiguilechat.eveonline.model.sde.compiled.items.Ship;

public class EliteBattleship
    extends Ship
{


    @Override
    public int getGroupId() {
        return  381;
    }

    @Override
    public Class<?> getGroup() {
        return EliteBattleship.class;
    }

}
