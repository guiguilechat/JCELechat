package fr.guiguilechat.jcelechat.model.sde.types.implant;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapNeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Implantness;
import fr.guiguilechat.jcelechat.model.sde.attributes.IntelligenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemoryBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerNeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.Rechargeratebonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WillpowerBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CyberShields
    extends Implant
{
    /**
     * Autogenerated skill attribute, capNeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int capneedbonus;
    /**
     * +/- bonus to the charisma of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charismabonus;
    /**
     * Whether an item is an implant or not
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int implantness;
    /**
     * +/- bonus to the intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int intelligencebonus;
    /**
     * +/- bonus to the memory of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int memorybonus;
    /**
     * +/- bonus to the perception of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int perceptionbonus;
    /**
     * Autogenerated skill attribute, PowerNeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int powerneedbonus;
    /**
     * Autogenerated skill attribute, rechargeratebonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rechargeratebonus;
    /**
     * Autogenerated skill attribute, shieldCapacityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacitybonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * +/- bonus to the willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int willpowerbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, PowerNeedBonus.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, Implantness.INSTANCE, CharismaBonus.INSTANCE, IntelligenceBonus.INSTANCE, MemoryBonus.INSTANCE, ShieldCapacityBonus.INSTANCE, PerceptionBonus.INSTANCE, Rechargeratebonus.INSTANCE, WillpowerBonus.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, CapNeedBonus.INSTANCE })));
    public static final CyberShields.MetaGroup METAGROUP = new CyberShields.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  317 :
            {
                return capneedbonus;
            }
            case  175 :
            {
                return charismabonus;
            }
            case  331 :
            {
                return implantness;
            }
            case  176 :
            {
                return intelligencebonus;
            }
            case  177 :
            {
                return memorybonus;
            }
            case  178 :
            {
                return perceptionbonus;
            }
            case  323 :
            {
                return powerneedbonus;
            }
            case  338 :
            {
                return rechargeratebonus;
            }
            case  337 :
            {
                return shieldcapacitybonus;
            }
            case  422 :
            {
                return techlevel;
            }
            case  179 :
            {
                return willpowerbonus;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<CyberShields> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CyberShields>
    {
        public static final String RESOURCE_PATH = "SDE/types/implant/CyberShields.yaml";
        private Map<Integer, CyberShields> cache = (null);

        @Override
        public IMetaCategory<? super CyberShields> category() {
            return Implant.METACAT;
        }

        @Override
        public int getGroupId() {
            return  749;
        }

        @Override
        public String getName() {
            return "CyberShields";
        }

        @Override
        public synchronized Map<Integer, CyberShields> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CyberShields.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, CyberShields> types;
        }
    }
}
