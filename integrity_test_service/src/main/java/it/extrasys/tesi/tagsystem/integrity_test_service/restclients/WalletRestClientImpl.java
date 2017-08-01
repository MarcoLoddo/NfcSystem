package it.extrasys.tesi.tagsystem.integrity_test_service.restclients;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.extrasys.tesi.tagsystem.integrity_test_service.Messages;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet.OrderTransactionDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet.WalletDto;

@Component
public class WalletRestClientImpl implements WalletRestClient {
    /** The rest template. */
    private RestTemplate restTemplate;

    @Autowired
    private Messages messages;
    /**
     * Instantiates a new rest client.
     */
    public WalletRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * WalletRestClient#addWallet(it.extrasys.tesi.tagsystem.
     * integrity_test_service.api.wallet.WalletDto)
     */
    @Override
    public WalletDto addWallet(WalletDto walletDto) {
        String uri = this.messages.getMessages("add.wallet");

        return this.restTemplate.postForEntity(uri, walletDto, WalletDto.class)
                .getBody();
    }
    @Override
    public BigDecimal addTransactionToWallet(Long userId,
            OrderTransactionDto orderTransactionDto) {
        String uri = this.messages.getMessages("add.transaction.to.wallet");

        Map<String, Long> map = new HashMap<>();
        map.put("userId", userId);
        return this.restTemplate
                .postForEntity(uri, orderTransactionDto, BigDecimal.class, map)
                .getBody();
    }
    public List<OrderTransactionDto> getUserTransactions(Long userId) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("userId", userId);
        String uri = this.messages.getMessages("get.transactions");
        return this.restTemplate.getForEntity(uri,
                (Class<? extends List<OrderTransactionDto>>) ArrayList.class,
                map).getBody();
    }
}
