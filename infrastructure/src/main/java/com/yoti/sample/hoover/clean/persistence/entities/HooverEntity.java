package com.yoti.sample.hoover.clean.persistence.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * A HooverEntity is an entity for storing data
 *
 * @author Artem Lukyanau
 */
@Entity
@Table(name = "HOOVER_HISTORY")
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class HooverEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hooverHistorySeq")
    @SequenceGenerator(name = "hooverHistorySeq", sequenceName = "hoover_history_id_seq", allocationSize = 1)
    private Long id;

    @Lob
    @NotBlank
    private String hoover;
}
