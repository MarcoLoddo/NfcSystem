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
import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionType;
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
     * Gets the transaction.
     *
     * @param id
     *            the id
     * @return the transaction
     */
    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public TransactionDto getTransaction(@PathVariable Long id) {

        TransactionEntity entity = this.walletManager.getTransactionById(id);
        if (entity == null) {
            return null;
        }
        return TransactionDtoConverter.toDto(entity);
    }

    /**
     * Gets the order transaction.
     *
     * @param order
     *            the order
     * @return the order transaction
     */
    @RequestMapping(value = "/transactions/", method = RequestMethod.GET)
    public OrderTransactionDto getOrderTransaction(
            @RequestParam(name = "order") Long order) {

        OrderTransactionEntity entity = this.walletManager
                .getOrderTransactionById(order);
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
            @RequestBody OrderTransactionDto transaction) {

        TransactionEntity entity = new TransactionEntity();
        if (transaction.getType() == TransactionType.ADD_FUNDS) {
            entity = TransactionDtoConverter.toEntity(transaction);
            this.walletManager.addTransaction(entity);
        } else {
            OrderTransactionEntity orderTransactionEntity = TransactionDtoConverter
                    .toEntity(transaction);
            this.walletManager.addOrderTransaction(orderTransactionEntity);
            entity = orderTransactionEntity;
        }
        Long walletId = this.walletManager.getWalletFromUserId(userId)
                .getWalletId();
        this.walletManager.addTransactionToWallet(walletId, entity);
        return this.walletManager.updateFunds(walletId, entity);
    }

}
