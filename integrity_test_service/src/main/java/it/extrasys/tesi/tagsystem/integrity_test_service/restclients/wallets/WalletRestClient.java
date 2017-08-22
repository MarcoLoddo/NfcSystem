package it.extrasys.tesi.tagsystem.integrity_test_service.restclients.wallets;

import java.math.BigDecimal;
import java.util.List;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet.OrderTransactionDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet.WalletDto;

public interface WalletRestClient {

    WalletDto addWallet(WalletDto walletDto);
    BigDecimal addTransactionToWallet(Long userId,
            OrderTransactionDto orderTransactionDto);
    List<OrderTransactionDto> getUserTransactions(Long userId);
    WalletDto getUserWallet(Long userId);
}