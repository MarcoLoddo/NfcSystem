package it.extrasys.tesi.tagsystem.integrity_test_service.api.orders;

// TODO: Auto-generated Javadoc
/**
 * The Class AddMealDto.
 */
public class AddMealDto {

    /** The nfc. */
    private String nfc;

    /** The meal id. */
    private Long mealId;

    /** The type caller. */
    private OrderType typeCaller;
    /**
     * Gets the nfc.
     *
     * @return the nfc
     */
    public String getNfc() {
        return this.nfc;
    }

    /**
     * Sets the nfc.
     *
     * @param nfc
     *            the new nfc
     */
    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    /**
     * Gets the meal id.
     *
     * @return the meal id
     */
    public Long getMealId() {
        return this.mealId;
    }

    /**
     * Sets the meal id.
     *
     * @param mealId
     *            the new meal id
     */
    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    /**
     * Gets the type caller.
     *
     * @return the type caller
     */
    public OrderType getTypeCaller() {
        return this.typeCaller;
    }

    /**
     * Sets the type caller.
     *
     * @param typeCaller
     *            the new type caller
     */
    public void setTypeCaller(OrderType typeCaller) {
        this.typeCaller = typeCaller;
    }

}
