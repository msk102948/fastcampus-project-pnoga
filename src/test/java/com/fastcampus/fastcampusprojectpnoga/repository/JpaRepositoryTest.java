package com.fastcampus.fastcampusprojectpnoga.repository;

import com.fastcampus.fastcampusprojectpnoga.config.JpaConfig;
import com.fastcampus.fastcampusprojectpnoga.domain.Ledger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(JpaConfig.class)
@DisplayName("JPA 연결 테스트")
class JpaRepositoryTest {

    private final LedgerRepository ledgerRepository;
    private final LedgerDetailRepository ledgerDetailRepository;

    public JpaRepositoryTest(
        @Autowired LedgerRepository ledgerRepository,
        @Autowired LedgerDetailRepository ledgerDetailRepository) {
        this.ledgerRepository = ledgerRepository;
        this.ledgerDetailRepository = ledgerDetailRepository;
    }

    @DisplayName("기본 select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        // Given

        // When
        List<Ledger> ledgers = ledgerRepository.findAll();

        // Then
        assertThat(ledgers)
            .isNotNull()
            .hasSize(0);
    }

    @DisplayName("기본 insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        // Given
        long previousCount = ledgerRepository.count();
        Ledger ledger = Ledger.of(LocalDate.now());

        // When
        ledgerRepository.save(ledger);

        // Then
        assertThat(ledgerRepository.count()).isEqualTo(previousCount+1);
    }

    @DisplayName("기본 update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine(){
        // Given
        Ledger ledger = Ledger.of(LocalDate.now());
        Ledger savedLedger = ledgerRepository.save(ledger);
        savedLedger.setBaseDate(LocalDate.now().plusDays(3));

        // When
        Ledger updatedLedger = ledgerRepository.saveAndFlush(savedLedger);

        // Then
        assertThat(updatedLedger.getBaseDate()).isEqualTo(LocalDate.now().plusDays(3));
    }

    @DisplayName("기본 delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine(){
        // Given
        Ledger ledger = Ledger.of(LocalDate.now());
        Ledger savedLedger = ledgerRepository.save(ledger);

        Long cnt = ledgerRepository.count();
        assertThat(cnt).isEqualTo(1L);

        // When
        ledgerRepository.delete(savedLedger);

        // Then
        assertThat(ledgerRepository.count()).isEqualTo(0);
    }
}