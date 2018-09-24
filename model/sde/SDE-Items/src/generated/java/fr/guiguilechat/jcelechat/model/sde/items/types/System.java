package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class System
    extends Item
{
    public final static System.MetaCat METACAT = new System.MetaCat();

    @Override
    public IMetaCategory<System> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<System>
    {

        @Override
        public int getCategoryId() {
            return  0;
        }

        @Override
        public String getName() {
            return "System";
        }

        @Override
        public Collection<IMetaGroup<? extends System>> groups() {
            return Arrays.asList(fr.guiguilechat.jcelechat.model.sde.items.types.system.System.METAGROUP);
        }
    }
}
