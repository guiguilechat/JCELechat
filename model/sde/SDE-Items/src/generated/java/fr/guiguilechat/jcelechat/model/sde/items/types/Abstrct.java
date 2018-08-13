package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Abstrct
    extends Item
{
    public final static Abstrct.MetaCat METACAT = new Abstrct.MetaCat();

    @Override
    public int getCategoryId() {
        return  29;
    }

    @Override
    public MetaCategory<Abstrct> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Abstrct> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Abstrct>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Abstrct> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Abstrct";
        }

        public Collection<MetaGroup<? extends Abstrct>> groups() {
            return Arrays.asList(groups);
        }
    }
}
