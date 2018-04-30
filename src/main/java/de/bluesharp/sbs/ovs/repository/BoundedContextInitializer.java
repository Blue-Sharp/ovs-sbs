package de.bluesharp.sbs.ovs.repository;

import de.bluesharp.sbs.ovs.Application;
import de.bluesharp.sbs.ovs.model.Sex;
import de.bluesharp.sbs.ovs.model.Account;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

@Configuration
@Slf4j
public class BoundedContextInitializer {

    @Profile(Application.Profile.LOCAL)
    @Bean
    CommandLineRunner dummyAccounts(AccountRepository repository) {
        return (args) -> {

            logger.debug("Enabled local profile generating dummy accounts");

            val me = Account.builder()
                    .userName("pirat")
                    .firstName("Rafael")
                    .lastName("Kansy")
                    .sex(Sex.MALE)
                    .birthday(LocalDate.of(1983, 06, 20))
                    .birthplace("Krappitz")
                    .email("rafael.kansy@xxx-xxx.de")
                    .phone("+49 160 xx xx xx xx")
                    .locale(Locale.GERMANY)
                    .zip("80939")
                    .city("München")
                    .street("XXX XXX xx")
                    .build();

            repository.save(me);

            val dataFactory = new DataFactory();

            for (short i = 0; i < 64; i++) {
                // Bug in DataFactory. See: https://github.com/andygibson/datafactory/pull/12
                String email = dataFactory.getEmailAddress();
                while (email.contains(" ")) {
                    email = dataFactory.getEmailAddress();
                }

                val account = Account.builder()
                        .userName(dataFactory.getRandomText(50))
                        .firstName(dataFactory.getFirstName())
                        .lastName(dataFactory.getLastName())
                        .sex(dataFactory.chance(50) ? Sex.MALE : Sex.FEMALE)
                        .birthday(dataFactory.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .birthplace(dataFactory.getCity())
                        .email(email)
                        .phone(dataFactory.getNumberText(8))
                        .locale(Locale.getDefault())
                        .zip(dataFactory.getNumberText(5))
                        .city(dataFactory.getCity())
                        .street(String.format("%s %s %d",
                                dataFactory.getStreetName(),
                                dataFactory.getStreetSuffix(),
                                dataFactory.getNumberBetween(1, 999))
                        )
                        .build();

                logger.debug("Added dummy account: {}", account);

                repository.save(account);
            }
        };
    }
}
