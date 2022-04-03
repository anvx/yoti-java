package com.yoti.sample.hoover.clean.common;

import java.io.Serializable;

/**
 * A RepositoryConverter converts a source object of type P to a target of type T.
 *
 * @author Artem Lukyanau
 */
public interface RepositoryConverter<T extends Serializable, P extends Serializable> {

    /**
     * Converts domain model to table model
     *
     * @param persistenceObject object to convert into table model
     */
    default T mapToTable(final P persistenceObject) {
        throw new UnsupportedOperationException();
    }

    /**
     * Converts table model to domain model
     *
     * @param tableObject object to convert into domain model
     */
    default P mapToEntity(final T tableObject) {
        throw new UnsupportedOperationException();
    }

}
