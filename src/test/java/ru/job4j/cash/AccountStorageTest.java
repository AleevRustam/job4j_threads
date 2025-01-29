package ru.job4j.cash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AccountStorageTest {
    private AccountStorage storage;

    @BeforeEach
    void setUp() {
        storage = new AccountStorage();
        storage.add(new Account(1, 1000));
        storage.add(new Account(2, 2000));
    }

    @Test
    void whenAdd() {
        storage.add(new Account(3, 300));
        var thirdAccount = storage.getById(3)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 3"));
        assertThat(thirdAccount.amount()).isEqualTo(300);
    }

    @Test
    void whenUpdate() {
        storage.update(new Account(1, 200));
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount.amount()).isEqualTo(200);
    }

    @Test
    void whenDelete() {
        storage.delete(1);
        assertThat(storage.getById(1)).isEmpty();
    }

    @Test
    void whenTransferIsSuccessful() {
        boolean result = storage.transfer(1, 2, 500);
        assertThat(result).isTrue();
        assertThat(storage.getById(1).get().amount()).isEqualTo(500);
        assertThat(storage.getById(2).get().amount()).isEqualTo(2500);
    }

    @Test
    void whenTransferInsufficientFunds() {
        boolean result = storage.transfer(1, 2, 1500);
        assertThat(result).isFalse();
        assertThat(storage.getById(1).get().amount()).isEqualTo(1000);
        assertThat(storage.getById(2).get().amount()).isEqualTo(2000);
    }

    @Test
    void whenTransferFromNonExistentAccount() {
        boolean result = storage.transfer(3, 2, 500);
        assertThat(result).isFalse();
    }

    @Test
    void whenTransferToNonExistentAccount() {
        boolean result = storage.transfer(1, 3, 500);
        assertThat(result).isFalse();
    }

    @Test
    void whenTransferAmountIsZero() {
        boolean result = storage.transfer(1, 2, 0);
        assertThat(result).isTrue();
        assertThat(storage.getById(1).get().amount()).isEqualTo(1000);
        assertThat(storage.getById(2).get().amount()).isEqualTo(2000);
    }

}