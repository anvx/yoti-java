package com.yoti.sample.hoover.clean.persistence.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoti.sample.hoover.clean.Hoover;
import com.yoti.sample.hoover.clean.common.RepositoryConverter;
import com.yoti.sample.hoover.clean.common.exception.ConverterException;
import com.yoti.sample.hoover.clean.persistence.entities.HooverEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * Implementation for {@link RepositoryConverter}
 *
 * @author Artem Lukyanau*/
@Component
@AllArgsConstructor
public class HooverRepositoryConverter implements RepositoryConverter<HooverEntity, Hoover> {

    private final ObjectMapper objectMapper;

    @Override
    public HooverEntity mapToTable(Hoover hoover) {
        String serializedHoover;

        try {
            serializedHoover = objectMapper.writeValueAsString(hoover);
        } catch (JsonProcessingException e) {
            throw new ConverterException("Error while serialize hoover.");
        }

        return HooverEntity.builder()
                .hoover(serializedHoover)
                .build();
    }

    @Override
    public Hoover mapToEntity(HooverEntity hooverEntity) {
        Hoover hoover;

        try {
            hoover = objectMapper.readValue(hooverEntity.getHoover(), Hoover.class);
        } catch (JsonProcessingException e) {
            throw new ConverterException("Error while deserialize hoover entity.");
        }

        return hoover;
    }
}