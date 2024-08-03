package fr.guiguilechat.jcelechat.libs.spring.update.batch;

import java.time.Instant;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch.BatchItem;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a batch fetch
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BatchFetch<Item extends BatchItem<?, ?>> {

	/** an, item that is created from a batch fetch */
	@MappedSuperclass
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static abstract class BatchItem<Fetch extends BatchFetch<?>, Fetched> {

		@ManyToOne
		private Fetch fetch;

		public abstract BatchItem<Fetch, Fetched> update(Fetched fetched);

	}

	@OneToMany(mappedBy = "fetch")
	private List<Item> items;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Lob
	private String errorMessage = null;

	private String etag;

	private Instant expires;

	private Instant lastModified;

	private int nbItems = 0;

	private int responseCode = 0;

	public static enum STATUS {
		SUCCESS, ERROR, CACHED, CREATED
	}

	@Enumerated(EnumType.STRING)
	private STATUS status = STATUS.CREATED;

}
