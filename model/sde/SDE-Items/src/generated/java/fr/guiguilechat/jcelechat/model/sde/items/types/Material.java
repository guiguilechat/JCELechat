package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.AbyssalMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.AncientSalvage;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.BiochemicalMaterial;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.Composite;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.FuelBlock;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.HybridPolymers;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.IceProduct;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.IntermediateMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.Mineral;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.MoonMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.NamedComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.RogueDroneComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.SalvagedMaterials;

public abstract class Material
    extends Item
{
    public final static Material.MetaCat METACAT = new Material.MetaCat();

    @Override
    public int getCategoryId() {
        return  4;
    }

    @Override
    public MetaCategory<Material> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Material> loadCategory() {
        return Stream.of(AbyssalMaterials.load(), AncientSalvage.load(), BiochemicalMaterial.load(), Composite.load(), FuelBlock.load(), HybridPolymers.load(), IceProduct.load(), IntermediateMaterials.load(), Mineral.load(), MoonMaterials.load(), NamedComponents.load(), RogueDroneComponents.load(), SalvagedMaterials.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Material>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Material> [] groups = new MetaGroup[] {Mineral.METAGROUP, IceProduct.METAGROUP, MoonMaterials.METAGROUP, IntermediateMaterials.METAGROUP, Composite.METAGROUP, BiochemicalMaterial.METAGROUP, SalvagedMaterials.METAGROUP, RogueDroneComponents.METAGROUP, AncientSalvage.METAGROUP, HybridPolymers.METAGROUP, FuelBlock.METAGROUP, NamedComponents.METAGROUP, AbyssalMaterials.METAGROUP };

        @Override
        public String getName() {
            return "Material";
        }

        public Collection<MetaGroup<? extends Material>> groups() {
            return Arrays.asList(groups);
        }
    }
}
