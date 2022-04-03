package com.yoti.sample.hoover.clean.persistence.impl;

import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.persistence.converters.HooverRepositoryConverter;
import com.yoti.sample.hoover.clean.persistence.entities.HooverEntity;
import com.yoti.sample.hoover.clean.persistence.repositories.HooverRepository;
import com.yoti.sample.hoover.clean.ports.HooverRepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Implementation for Repository service for Hoover.
 *
 * @author Artem Lukyanau
 */
@Repository
@AllArgsConstructor
public class HooverRepositoryServiceImpl implements HooverRepositoryService {

    private final HooverRepository hooverRepository;
    private final HooverRepositoryConverter hooverRepositoryConverter;

    /**
     * Method for saving {@link Hoover}
     *
     * @param hoover entity to save
     * @author Artem Lukyanau
     */
    @Override
    public void saveHoover(Hoover hoover) {
        HooverEntity hooverEntity = hooverRepositoryConverter.mapToTable(hoover);
        hooverRepository.save(hooverEntity);
    }
}