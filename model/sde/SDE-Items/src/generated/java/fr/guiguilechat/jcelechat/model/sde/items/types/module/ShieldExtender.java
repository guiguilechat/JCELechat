package fr.guiguilechat.jcelechat.model.sde.items.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class ShieldExtender
    extends Module
{
    /**
     * Bonus to capacity (shield at least).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CapacityBonus;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
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
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SignatureRadiusAdd;
    public final static ShieldExtender.MetaGroup METAGROUP = new ShieldExtender.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  72 :
            {
                return CapacityBonus;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  983 :
            {
                return SignatureRadiusAdd;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<ShieldExtender> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ShieldExtender>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/ShieldExtender.yaml";
        private Map<String, ShieldExtender> cache = (null);

        @Override
        public IMetaCategory<? super ShieldExtender> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  38;
        }

        @Override
        public String getName() {
            return "ShieldExtender";
        }

        @Override
        public synchronized Map<String, ShieldExtender> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ShieldExtender.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ShieldExtender> items;
        }
    }
}
