package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class SdeEntityService<Entity extends SdeEntity<IdType>, IdType extends Number, Repository extends SdeEntityRepository<Entity, IdType>> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repository repo;

	private final Supplier<Entity> creator;

	// searching

	public Entity ofId(IdType id) {
		return repo().findById(id).orElse(null);
	}

	public List<Entity> ofId(Iterable<IdType> ids) {
		return repo().findAllById(ids);
//		return partitionInList(ids)
//				.flatMap(l -> repo().findAllById(l).stream())
//				.toList();
	}

	public Entity activeOfId(IdType id) {
		return repo().findByIdAndReceivedTrueAndRemovedFalse(id);
	}

	public List<Entity> activeOfId(Iterable<IdType> ids) {
		return repo().findAllByIdInAndReceivedTrueAndRemovedFalse(ids);
	}

	public Map<IdType, Entity> mapAllById() {
		return repo().mapAllById();
	}

	public Map<IdType, Entity> mapOfIdById(Iterable<IdType> ids) {
		return repo().mapOfIdById(ids);
	}

	public Map<IdType, Entity> mapActiveById() {
		return repo().mapActiveById();
	}

	public Map<IdType, Entity> mapActiveOfIdById(Iterable<IdType> ids) {
		return repo().mapActiveOfIdById(ids);
	}

	public List<Entity> listNotReceived() {
		return repo().findAllByReceivedFalse();
	}

	// create/save

	/**
	 * create an empty entity for given id, and save it. The entity returned is the
	 * one saved already.
	 *
	 * @param entityId new entity's id
	 * @return
	 */
	public Entity create(IdType entityId) {
		Entity create = creator.get();
		create.setId(entityId);
		return repo().save(create); // don't flush as it will cause one DB roundtrip per entity created.
	}

	public Entity createIfAbsent(IdType id) {
		var op = repo().findById(id);
		if (op.isEmpty()) {
			Entity ret = create(id);
			return ret;
		} else {
			return op.get();
		}
	}

	public Map<IdType, Entity> createIfAbsent(Collection<IdType> ids) {
		var l = repo().findAllById(ids);
		if (l.size() == ids.size()) {
			return l.stream().collect(Collectors.toMap(Entity::getId, o -> o));
		}
		Map<IdType, Entity> mapById = repo().mapById(l);
		Map<IdType, Entity> ret = new HashMap<>();
		for (IdType id : ids) {
			Entity matchingEntity = mapById.get(id);
			if (matchingEntity == null) {
				matchingEntity = create(id);
			}
			ret.put(id, matchingEntity);
		}
		return ret;
	}

	/**
	 * list all items and make a getter on them
	 *
	 * @return a function that has the list of internal items and return from it, or
	 *         create a new one if absent
	 */
	public Function<IdType, Entity> getterAll() {
		Map<IdType, Entity> items = new HashMap<>(repo().mapAllById());
		return i -> i == null ? null : items.computeIfAbsent(i, this::create);
	}

	/**
	 * list items for given ids, creating the missing, and return a mapper of
	 * provided ids to their corresponding entity
	 *
	 * @param ids stream of ids. They are concatenated as distinct
	 * @return a function that memorizes the items for the provided ids, after
	 *         creating the missing entities.
	 */
	public Function<IdType, Entity> getter(Stream<IdType> ids) {
		return createIfAbsent(ids.distinct().toList())::get;
	}

	// hardcoded inList to limit the query in(ids) quantity

//	/**
//	 * maybe use actual implementation : 1000 for oracle, Integer.maxInt for others
//	 *
//	 * @return maximum elements we can add in a list query param.
//	 */
//	protected int maxInList() {
//		return 1000;
//	}
//
//	protected <T> Stream<List<T>> partitionInList(List<T> elements) {
//		return partition(elements, maxInList());
//	}
//
//	protected <T> Stream<List<T>> partitionInList(Iterable<T> elements) {
//		return partition(StreamSupport.stream(elements.spliterator(), false).toList(), maxInList());
//	}

//	/**
//	 * partition a list of items into a list of limited-size sublists
//	 *
//	 * @param <T>
//	 * @param elements
//	 * @return
//	 */
//	protected static <T> Stream<List<T>> partition(List<T> elements, int maxSize) {
//		if (elements == null) {
//			return Stream.of();
//		}
//		return IntStream.iterate(0, i -> i < elements.size(), i -> i + maxSize)
//				.mapToObj(i -> elements.subList(i, Math.min(elements.size(), i + maxSize)));
//	}

}
