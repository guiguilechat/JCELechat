package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Abstrct
    extends Item
{
    public final static Abstrct.MetaCat METACAT = new Abstrct.MetaCat();

    @Override
    public IMetaCategory<Abstrct> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Abstrct>
    {

        @Override
        public int getCategoryId() {
            return  29;
        }

        @Override
        public String getName() {
            return "Abstrct";
        }

        @Override
        public Collection<IMetaGroup<? extends Abstrct>> groups() {
            return Arrays.asList();
        }
    }
}
