package com.yoti.sample.hoover.clean.common;

import java.io.Serializable;

/**
 * A RestConverter converts a source object of type R to a target of type E.
 *
 * @author Artem Lukyanau
 */
public interface RestConverter<R extends Serializable, E extends Serializable> {

	/**
	 * Converts domain model to table model
	 *
	 * @param rest object to convert into domain model
	 */
	default E mapToEntity(final R rest) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Converts domain model to table model
	 *
	 * @param entity object to convert into presentation model
	 */
	default R mapToRest(final E entity) {
		throw new UnsupportedOperationException();
	}
}