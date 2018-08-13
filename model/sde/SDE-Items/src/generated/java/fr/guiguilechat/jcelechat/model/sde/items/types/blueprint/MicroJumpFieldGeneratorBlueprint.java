package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class MicroJumpFieldGeneratorBlueprint
    extends Blueprint
{
    public final static MicroJumpFieldGeneratorBlueprint.MetaGroup METAGROUP = new MicroJumpFieldGeneratorBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/MicroJumpFieldGeneratorBlueprint.yaml";
    private static Map<String, MicroJumpFieldGeneratorBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1543;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<MicroJumpFieldGeneratorBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, MicroJumpFieldGeneratorBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MicroJumpFieldGeneratorBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, MicroJumpFieldGeneratorBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<MicroJumpFieldGeneratorBlueprint>
    {

        @Override
        public MetaCategory<? super MicroJumpFieldGeneratorBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "MicroJumpFieldGeneratorBlueprint";
        }

        @Override
        public Collection<MicroJumpFieldGeneratorBlueprint> items() {
            return (load().values());
        }
    }
}
