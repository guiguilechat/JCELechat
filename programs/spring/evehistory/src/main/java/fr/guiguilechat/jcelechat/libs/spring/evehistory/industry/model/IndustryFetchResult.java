package fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model;

import java.time.Instant;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class IndustryFetchResult {

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
		FETCHED
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

	/** etag of the resource after the fetch if any */
	private String etag;

	/**
	 * response code retrieved if any. In case of error, the last error's response
	 * code is used
	 */
	private Integer responseCode;

}
