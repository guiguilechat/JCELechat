package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class StructureModuleBlueprint
    extends Blueprint
{
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureItemVisualFlag;
    public final static StructureModuleBlueprint.MetaGroup METAGROUP = new StructureModuleBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/StructureModuleBlueprint.yaml";
    private static Map<String, StructureModuleBlueprint> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2334 :
            {
                return StructureItemVisualFlag;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1707;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<StructureModuleBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, StructureModuleBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureModuleBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, StructureModuleBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<StructureModuleBlueprint>
    {

        @Override
        public MetaCategory<? super StructureModuleBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "StructureModuleBlueprint";
        }

        @Override
        public Collection<StructureModuleBlueprint> items() {
            return (load().values());
        }
    }
}
