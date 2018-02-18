package de.bluesharp.sbs.ovs.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

@Data
@EqualsAndHashCode(exclude = {"id", "version"})
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @Version
    private Long version;

    @OneToOne
    private Chairman chairman;

    @Size(max = 50)
    @NotNull
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Sex sex;

    @Past
    @NotNull
    // TODO RK LocalDateTime
    // TODO RK UTC mapping in database
    private Date birthday;

    @NotNull
    private String birthplace;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private Locale locale;

    @NotNull
    private String zip;

    @NotNull
    private String city;

    @NotNull
    private String street;
}
