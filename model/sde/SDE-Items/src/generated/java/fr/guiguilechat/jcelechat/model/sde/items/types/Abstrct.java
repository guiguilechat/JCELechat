package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Abstrct> [] groups = new IMetaGroup[] { };

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
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Abstrct> load() {
            HashMap<String, Abstrct> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
