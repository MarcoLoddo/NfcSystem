package it.extrasys.tesi.tagsystem.wallet_service.api;

import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class WalletDtoConverter.
 */
public final class WalletDtoConverter {

    /**
     * Instantiates a new wallet dto converter.
     */
    private WalletDtoConverter() {

    }

    /**
     * To dto.
     *
     * @param entity
     *            the entity
     * @return the wallet dto
     */
    public static WalletDto toDto(WalletEntity entity) {

        WalletDto dto = new WalletDto();
        dto.setFunds(entity.getFunds());
        dto.setUserId(entity.getUserId());
        dto.setWalletId(entity.getWalletId());
        for (TransactionEntity transaction : entity.getTransactions()) {
            dto.getTransactions()
                    .add(TransactionDtoConverter.toDto(transaction));
        }
        return dto;
    }

    /**
     * To entity.
     *
     * @param entity
     *            the entity
     * @return the wallet entity
     */
    public static WalletEntity toEntity(WalletDto entity) {

        WalletEntity dto = new WalletEntity();
        dto.setFunds(entity.getFunds());
        dto.setUserId(entity.getUserId());
        dto.setWalletId(entity.getWalletId());
        for (TransactionDto transaction : entity.getTransactions()) {
            dto.getTransactions()
                    .add(TransactionDtoConverter.toEntity(transaction));
        }
        return dto;
    }
}
