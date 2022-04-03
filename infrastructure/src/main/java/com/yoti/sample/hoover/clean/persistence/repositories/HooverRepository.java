package com.yoti.sample.hoover.clean.persistence.repositories;

import com.yoti.sample.hoover.clean.persistence.entities.HooverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository service for {@link HooverEntity}
 * CRUD operations provided by Spring Data
 *
 * @author Artem Lukyanau
 */
@Repository
public interface HooverRepository extends JpaRepository<HooverEntity, Long> {
}