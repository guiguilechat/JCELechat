package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class AuditLogSecureContainer
    extends Celestial
{
    /**
     * This attribute is not needed by default. You only need to add it if you want to stop something from being jettisoned.
     * 
     * The primary case for this was Station Containers.
     * 
     *  0 = Cannot be jettisoned.
     *  1 = Can be jettisoned.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int CanBeJettisoned;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    public final static AuditLogSecureContainer.MetaGroup METAGROUP = new AuditLogSecureContainer.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1852 :
            {
                return CanBeJettisoned;
            }
            case  9 :
            {
                return Hp;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<AuditLogSecureContainer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AuditLogSecureContainer>
    {
        public final static String RESOURCE_PATH = "SDE/items/celestial/AuditLogSecureContainer.yaml";
        private Map<String, AuditLogSecureContainer> cache = (null);

        @Override
        public IMetaCategory<? super AuditLogSecureContainer> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  448;
        }

        @Override
        public String getName() {
            return "AuditLogSecureContainer";
        }

        @Override
        public synchronized Map<String, AuditLogSecureContainer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AuditLogSecureContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AuditLogSecureContainer> items;
        }
    }
}
