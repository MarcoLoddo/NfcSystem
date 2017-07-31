package it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class WalletEntity.
 */
public class WalletDto {

    /** The wallet id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    /** The user id. */
    private Long userId;

    /** The funds. */
    private BigDecimal funds;

    private List<TransactionDto> transactions = new ArrayList<TransactionDto>();

    public Long getWalletId() {
        return this.walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getFunds() {
        return this.funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public List<TransactionDto> getTransactions() {
        return this.transactions;
    }

}
