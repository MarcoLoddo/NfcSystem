package it.extrasys.tesi.tagsystem.wallet_service.api;

import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionDtoConverter.
 */
public final class TransactionDtoConverter {

    /**
     * Instantiates a new transaction dto converter.
     */
    private TransactionDtoConverter() {

    }

    /**
     * To dto.
     *
     * @param entity
     *            the entity
     * @return the transaction dto
     */
    public static TransactionDto toDto(TransactionEntity entity) {
        TransactionDto dto = new TransactionDto();
        dto.setDate(entity.getDate());
        dto.setPrice(entity.getPrice());
        dto.setTransactionId(entity.getTransactionId());
        dto.setType(entity.getType());
        dto.setUserNfc(entity.getUserNfc());
        return dto;
    }

    /**
     * To dto.
     *
     * @param entity
     *            the entity
     * @return the order transaction dto
     */
    public static OrderTransactionDto toDto(OrderTransactionEntity entity) {
        OrderTransactionDto dto = new OrderTransactionDto();
        dto.setDate(entity.getDate());
        dto.setPrice(entity.getPrice());
        dto.setTransactionId(entity.getTransactionId());
        dto.setType(entity.getType());
        dto.setUserNfc(entity.getUserNfc());
        dto.setOrderId(entity.getOrderId());
        return dto;
    }

    /**
     * To entity.
     *
     * @param entity
     *            the entity
     * @return the transaction entity
     */
    public static TransactionEntity toEntity(TransactionDto entity) {
        TransactionEntity dto = new TransactionEntity();
        dto.setDate(entity.getDate());
        dto.setPrice(entity.getPrice());
        dto.setTransactionId(entity.getTransactionId());
        dto.setType(entity.getType());
        dto.setUserNfc(entity.getUserNfc());
        return dto;
    }

    /**
     * To entity.
     *
     * @param entity
     *            the entity
     * @return the order transaction entity
     */
    public static OrderTransactionEntity toEntity(OrderTransactionDto entity) {
        OrderTransactionEntity dto = new OrderTransactionEntity();
        dto.setDate(entity.getDate());
        dto.setPrice(entity.getPrice());
        dto.setTransactionId(entity.getTransactionId());
        dto.setType(entity.getType());
        dto.setUserNfc(entity.getUserNfc());
        dto.setOrderId(entity.getOrderId());
        return dto;
    }
}
