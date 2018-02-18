package de.bluesharp.sbs.ovs.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(exclude = {"id"})
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "CHAIRMEN")
public class Chairman implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
}
