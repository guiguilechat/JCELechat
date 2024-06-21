package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TypeAttributeService {

	private final TypeAttributeRepository repo;

	public void deleteByTypes(Collection<Type> types) {
		repo.deleteByTypeId(types.stream().map(Type::getId).toList());
	}

	public List<TypeAttribute> saveAll(Iterable<TypeAttribute> data) {
		return repo.saveAll(data);
	}

	public List<TypeAttribute> byAttributeId(int attributeId) {
		return repo.findAllByAttributeId(attributeId);
	}

	public List<TypeAttribute> byAttribute(Attribute attribute) {
		return repo.findAllByAttribute(attribute);
	}

	public List<TypeAttribute> findAll() {
		return repo.findAll();
	}

}
