package it.extrasys.tesi.tagsystem.wallet_service.resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.extrasys.tesi.tagsystem.wallet_service.api.OrderTransactionDto;
import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionDto;
import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionDtoConverter;
import it.extrasys.tesi.tagsystem.wallet_service.api.WalletDto;
import it.extrasys.tesi.tagsystem.wallet_service.api.WalletDtoConverter;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.manager.WalletManager;

/**
 * The Class WalletManager.
 */
@RestController
public class WalletController {

    @Autowired
    private WalletManager walletManager;

    /**
     * Gets the user wallet.
     *
     * @param userId
     *            the user id
     * @return the user wallet
     */
    @RequestMapping(value = "/wallets/{userId}", method = RequestMethod.GET)
    public WalletDto getUserWallet(@PathVariable Long userId) {

        return WalletDtoConverter
                .toDto(this.walletManager.getWalletFromUserId(userId));
    }

    /**
     * Gets the user transactions.
     *
     * @param userId
     *            the user id
     * @return the user transactions
     */
    @RequestMapping(value = "/transactions/", method = RequestMethod.GET)
    public List<TransactionDto> getUserTransactions(@RequestParam Long userId) {

        List<TransactionEntity> entities = this.walletManager
                .getWalletFromUserId(userId).getTransactions();
        List<TransactionDto> transactions = new ArrayList<>();
        for (TransactionEntity transaction : entities) {
            transactions.add(TransactionDtoConverter.toDto(transaction));
        }
        return transactions;
    }

    /**
     * Gets the transaction order.
     *
     * @param id
     *            the id
     * @return the transaction order
     */
    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public OrderTransactionDto getTransaction(@PathVariable Long id) {

        OrderTransactionEntity entity = this.walletManager
                .getOrderTransactionById(id);
        if (entity == null) {
            return null;
        }
        return TransactionDtoConverter.toDto(entity);
    }

    /**
     * Adds the transaction to wallet.
     *
     * @param userId
     *            the user id
     * @param transaction
     *            the transaction
     */
    @RequestMapping(value = "/wallets/{userId}", method = RequestMethod.POST)
    public BigDecimal addTransactionToWallet(@PathVariable Long userId,
            @RequestBody TransactionDto transaction) {

        TransactionEntity entity = TransactionDtoConverter
                .toEntity(transaction);
        Long walletId = this.walletManager.getWalletFromUserId(userId)
                .getWalletId();
        this.walletManager.addTransactionToWallet(walletId, entity);
        return this.walletManager.updateFunds(walletId, entity);
    }

    /**
     * Adds the transaction.
     *
     * @param transaction
     *            the transaction
     * @return the transaction dto
     */
    @RequestMapping(value = "/transactions/", method = RequestMethod.POST)
    public TransactionDto addTransaction(
            @RequestBody TransactionDto transaction) {

        TransactionEntity entity = TransactionDtoConverter
                .toEntity(transaction);
        return TransactionDtoConverter
                .toDto(this.walletManager.addTransaction(entity));
    }

    /**
     * Adds the order transaction.
     *
     * @param transaction
     *            the transaction
     * @return the transaction dto
     */
    @RequestMapping(value = "/transactions/order", method = RequestMethod.POST)
    public TransactionDto addOrderTransaction(
            @RequestBody OrderTransactionDto transaction) {

        OrderTransactionEntity entity = TransactionDtoConverter
                .toEntity(transaction);
        return TransactionDtoConverter
                .toDto(this.walletManager.addOrderTransaction(entity));
    }
}
