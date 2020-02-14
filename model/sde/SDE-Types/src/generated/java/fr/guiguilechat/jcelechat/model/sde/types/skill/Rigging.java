package fr.guiguilechat.jcelechat.model.sde.types.skill;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import org.yaml.snakeyaml.Yaml;

public class Rigging
    extends Skill
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int RigDrawbackBonus;
    public static final Rigging.MetaGroup METAGROUP = new Rigging.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1139 :
            {
                return RigDrawbackBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<Rigging> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Rigging>
    {
        public static final String RESOURCE_PATH = "SDE/types/skill/Rigging.yaml";
        private Map<String, Rigging> cache = (null);

        @Override
        public IMetaCategory<? super Rigging> category() {
            return Skill.METACAT;
        }

        @Override
        public int getGroupId() {
            return  269;
        }

        @Override
        public String getName() {
            return "Rigging";
        }

        @Override
        public synchronized Map<String, Rigging> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Rigging.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Rigging> types;
        }
    }
}
