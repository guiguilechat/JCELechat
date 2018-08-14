package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.lights.PointLights;

public abstract class Lights
    extends Item
{
    public final static Lights.MetaCat METACAT = new Lights.MetaCat();

    @Override
    public IMetaCategory<Lights> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Lights>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Lights> [] groups = new IMetaGroup[] {PointLights.METAGROUP };

        @Override
        public int getCategoryId() {
            return  54;
        }

        @Override
        public String getName() {
            return "Lights";
        }

        @Override
        public Collection<IMetaGroup<? extends Lights>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Lights> load() {
            HashMap<String, Lights> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
