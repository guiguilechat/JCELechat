package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHPMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class EnergizedArmorMembrane
    extends Module
{
    /**
     * Multiplier to the HP of a ships armor module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double armorhpmultiplier;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double emdamageresistancebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double explosivedamageresistancebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double kineticdamageresistancebonus;
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
    public int metagroupid;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double thermaldamageresistancebonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE, Cpu.INSTANCE, ArmorHPMultiplier.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, EmDamageResistanceBonus.INSTANCE, ExplosiveDamageResistanceBonus.INSTANCE, MetaLevelOld.INSTANCE, KineticDamageResistanceBonus.INSTANCE, ThermalDamageResistanceBonus.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE })));
    public static final EnergizedArmorMembrane.MetaGroup METAGROUP = new EnergizedArmorMembrane.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  148 :
            {
                return armorhpmultiplier;
            }
            case  50 :
            {
                return cpu;
            }
            case  984 :
            {
                return emdamageresistancebonus;
            }
            case  985 :
            {
                return explosivedamageresistancebonus;
            }
            case  986 :
            {
                return kineticdamageresistancebonus;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  30 :
            {
                return power;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  987 :
            {
                return thermaldamageresistancebonus;
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
    public IMetaGroup<EnergizedArmorMembrane> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EnergizedArmorMembrane>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/EnergizedArmorMembrane.yaml";
        private Map<Integer, EnergizedArmorMembrane> cache = (null);

        @Override
        public IMetaCategory<? super EnergizedArmorMembrane> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  326;
        }

        @Override
        public String getName() {
            return "EnergizedArmorMembrane";
        }

        @Override
        public synchronized Map<Integer, EnergizedArmorMembrane> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EnergizedArmorMembrane.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, EnergizedArmorMembrane> types;
        }
    }
}
