package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class WalletEntity.
 */
public class WalletEntity {

    /** The wallet id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    /** The user id. */
    private Long userId;

    /** The funds. */
    private BigDecimal funds;

    /** The transactions. */
    @OneToMany
    private List<TransactionEntity> transactions = new ArrayList<TransactionEntity>();

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

    public List<TransactionEntity> getTransactions() {
        return this.transactions;
    }

}
