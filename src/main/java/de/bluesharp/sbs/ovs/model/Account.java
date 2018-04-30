package de.bluesharp.sbs.ovs.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;


@Data
@EqualsAndHashCode(exclude = {"id", "version"})
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "ACCOUNTS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName", name = "UK_USER_NAME") ,
        @UniqueConstraint(columnNames = "email", name = "UK_EMAIL"),
})
public class Account implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @Version
    private Long version;

    @OneToOne
    @JoinColumn(name = "chairman_id", foreignKey = @ForeignKey(name = "FK_ACCOUNTS_CHAIRMEN"))
    private Chairman chairman;

    @Size(max = 50)
    @NotEmpty
    private String userName;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Sex sex;

    @Past
    @NotNull
    private LocalDate birthday;

    @NotEmpty
    private String birthplace;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    @NotNull
    private Locale locale;

    @NotEmpty
    private String zip;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;
}
