package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.lights.PointLights;

public abstract class Lights
    extends Item
{
    public final static Lights.MetaCat METACAT = new Lights.MetaCat();

    @Override
    public int getCategoryId() {
        return  54;
    }

    @Override
    public MetaCategory<Lights> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Lights> loadCategory() {
        return Stream.of(PointLights.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Lights>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Lights> [] groups = new MetaGroup[] {PointLights.METAGROUP };

        @Override
        public String getName() {
            return "Lights";
        }

        public Collection<MetaGroup<? extends Lights>> groups() {
            return Arrays.asList(groups);
        }
    }
}
