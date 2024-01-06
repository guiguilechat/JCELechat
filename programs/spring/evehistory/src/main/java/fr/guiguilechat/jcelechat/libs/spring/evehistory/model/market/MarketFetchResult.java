package fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market;

import java.time.Instant;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(indexes = {
		@Index(columnList = "status"),
		@Index(columnList = "lastModified")
})
public class MarketFetchResult {

	//
	// DB fields
	//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreatedDate
	private Instant createdDate;

	public static enum STATUS {

		/** error during process/fetch. See error message for more information */
		FAIL,
		/** the fetch returned a 304 */
		CACHED,
		/** initial state, when the result is being fetched */
		FETCHING,
		/** market has been fetched and stored into DB. */
		FETCHED,
		/** all lines for this order are translated to {@link MarketOrder} */
		ORDERSEXIST,
		/** market lines have been analyzed. */
		LINESANALYZED
		;

		/**
		 * @return the enum set of STATUS that are AFTER the one in argument
		 */
		public static EnumSet<STATUS> after(STATUS elem) {
			int startOrd = elem.ordinal() + 1;
			if (startOrd >= STATUS.values().length) {
				return EnumSet.noneOf(STATUS.class);
			}
			return EnumSet.range(STATUS.values()[startOrd], STATUS.values()[STATUS.values().length - 1]);
		}

		@Getter(lazy = true)
		@Accessors(fluent = true)
		private final Set<STATUS> after = Set.copyOf(after(this));

		/**
		 * @return the enum set of STATUS that are BEFORE the one in argument
		 */
		public static EnumSet<STATUS> before(STATUS elem) {
			int endOrd = elem.ordinal() - 1;
			if (endOrd < 0) {
				return EnumSet.noneOf(STATUS.class);
			}
			return EnumSet.range(STATUS.values()[0], STATUS.values()[endOrd]);
		}

		@Getter(lazy = true)
		@Accessors(fluent = true)
		private final Set<STATUS> before = Set.copyOf(before(this));

	}

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private STATUS status = STATUS.FETCHING;


	/** list of errors description, if any */
	@Column(length = 5000)
	private String errors;

	/** must be equals to the length of the {@link #errors} column */
	static private int ERRORS_MAXLENGTH = 5000;

	/**
	 * add an error and set the status to {@link STATUS#FAIL}. if the errors
	 * message becomes too long, it is truncated.
	 *
	 * @param error error message to add.
	 */
	public void error(String error) {
		setStatus(STATUS.FAIL);
		String newString = (errors == null ? "" : errors + ", ") + error;
		setErrors(newString.length() > ERRORS_MAXLENGTH ? newString.substring(0, ERRORS_MAXLENGTH) : newString);
	}

	/**
	 * add a list of errors and set the status to {@link STATUS#FAIL}.
	 */
	public void error(Iterable<String> errors) {
		error(StreamSupport.stream(errors.spliterator(), false).collect(Collectors.joining(", ")));
	}

	//
	// fields post-fetch
	//


	// previous fetchResult that has no error, if any.
	@OneToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private MarketFetchResult previousResult;

	// next fetchResult that has no error, if any.
	@OneToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private MarketFetchResult nextResult;

	/** region used for the call */
	@ManyToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private ObservedRegion region;

	/** etag of the resource after the fetch if any */
	private String etag;

	/**
	 * response code retrieved if any. In case of error, the last error's response
	 * code is used
	 */
	private Integer responseCode;

	/** number of pages received, if any */
	private Integer pagesFetched;

	/**
	 * number of lines fetched
	 */
	private Integer linesFetched;

	/**
	 * instant retrieved from the first page, if any
	 */
	private Instant lastModified;

	//
	// fields post-analyzis
	//

	/**
	 * number of lines associated with that result after analyzis
	 */
	private Integer linesUpdated;

	/**
	 * duration in MS to fetch the http resource
	 */
	private long durationFetchMS;

	/**
	 * duration in MS to create the orders
	 */
	private long durationCreateOrderMS;

	/**
	 * duration in MS to analyze the lines
	 */
	private long durationAnalyzeMS;

}
