package it.extrasys.tesi.tagsystem.integrity_test_service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.common.MealType;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.CornerDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.corners.NfcReaderDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MealDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MenuDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.ConfigurationDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.OrderDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.NfcTagDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.users.UserDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet.OrderTransactionDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet.TransactionType;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.wallet.WalletDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.CornerRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.MealRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.OrderRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.UsersRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.WalletRestClient;

@Controller
public class IntegrityTestController {

    @Autowired
    private MealRestClient mealRestClient;

    @Autowired
    private OrderRestClient orderRestClient;

    @Autowired
    private UsersRestClient usersRestClient;

    @Autowired
    private CornerRestClient cornerRestClient;

    @Autowired
    private WalletRestClient walletRestClient;
    @RequestMapping("/")
    public String mainTest() throws ParseException {

        UserDto userDto = new UserDto();
        userDto.setEmail("prova@prova");
        userDto.setName("clark");
        userDto.setPassword("password");
        userDto = this.usersRestClient.addUser(userDto);

        NfcTagDto nfcTagDto = new NfcTagDto();
        nfcTagDto.setNfcId("prova");
        NfcTagDto nfcTagDto2 = new NfcTagDto();
        nfcTagDto2.setNfcId("prova2");
        userDto = this.usersRestClient.addNfc(userDto, nfcTagDto);
        userDto = this.usersRestClient.addNfc(userDto, nfcTagDto2);

        UserDto userpers = this.usersRestClient.getById(userDto.getUserId());

        if (!userDto.getEmail().equals(userpers.getEmail())
                || !userDto.getPassword().equals(userpers.getPassword())) {
            System.out.println(userDto.getEmail() != userpers.getEmail());
            System.out.println(userDto.getPassword() != userpers.getPassword());
        }

        NfcTagDto copy = new NfcTagDto();
        copy.setNfcId(nfcTagDto.getNfcId());

        nfcTagDto.setDisabled(true);

        this.usersRestClient.updateNfc(userDto.getUserId(), copy, nfcTagDto);

        MealDto mealDto = new MealDto();
        mealDto.setPrice(new BigDecimal(10));
        mealDto.setDescription("prova");
        mealDto.setType(MealType.DESSERT);
        mealDto = this.mealRestClient.addMeal(mealDto);

        MealDto[] list = this.mealRestClient.getAllMeals();
        System.out.println("Meals in db: " + list.length);

        MenuDto menuDto = new MenuDto();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        menuDto.setDate(dFormat.parse("2017-07-31"));
        menuDto.setType("Generic");

        menuDto = this.mealRestClient.addMenu(menuDto);
        menuDto = this.mealRestClient.addMealToMenu(mealDto, menuDto);

        System.out.println("Menu size: " + menuDto.getMeals().size());

        ConfigurationDto configurationDto = new ConfigurationDto();
        configurationDto.setName("Prova1");
        configurationDto.setSpecialPrice(new BigDecimal(5));
        configurationDto.setStartDate(dFormat.parse("2017-07-31"));
        configurationDto.setEndDate(dFormat.parse("2017-08-01"));

        configurationDto = this.orderRestClient
                .addConfiguration(configurationDto);

        System.out.println(
                "ID configuration: " + configurationDto.getConfigurationId());
        configurationDto.setName("Prova update");
        configurationDto = this.orderRestClient
                .updateConfiguration(configurationDto);
        System.out.println(configurationDto.getName());

        CornerDto cornerDto = new CornerDto();
        cornerDto.setMealId(mealDto.getMealId());
        NfcReaderDto nfcReaderDto = new NfcReaderDto();
        nfcReaderDto.setReaderId("provareader");

        cornerDto = this.cornerRestClient.addCorner(cornerDto);
        System.out.println("Id corner:" + cornerDto.getCornerId());
        cornerDto.setReader(nfcReaderDto);
        nfcReaderDto = this.cornerRestClient.addReader(nfcReaderDto);
        System.out.println("Id reader:" + nfcReaderDto.getReaderId());
        cornerDto = this.cornerRestClient.updateCorner(cornerDto, 1L,
                nfcReaderDto.getReaderId());

        OrderDto orderDto = new OrderDto();
        orderDto.setData(dFormat.parse("2017-08-01"));
        orderDto.setNfcId("prova");
        List<Long> mealids = new ArrayList<>();
        mealids.add(mealDto.getMealId());
        orderDto.setMealId(mealids);

        orderDto = this.orderRestClient.addOrder(orderDto);
        System.out.println("Not closed status: "
                + this.cornerRestClient.getOrdersByStatus(false));
        this.orderRestClient.closeOrder(orderDto.getOrderId());

        WalletDto walletDto = new WalletDto();
        walletDto.setUserId(userDto.getUserId());
        walletDto = this.walletRestClient.addWallet(walletDto);
        System.out.println("ID wallet: " + walletDto.getWalletId());

        OrderTransactionDto transactionDto = new OrderTransactionDto();
        transactionDto.setPrice(new BigDecimal(10));
        transactionDto.setType(TransactionType.ADD_FUNDS);
        transactionDto.setUserNfc(userDto.getNfcTags().get(0).getNfcId());
        transactionDto.setWalletId(walletDto.getWalletId());
        transactionDto.setDate(dFormat.parse("2017-08-01"));

        System.out.println("Saldo: " + this.walletRestClient
                .addTransactionToWallet(userDto.getUserId(), transactionDto));

        transactionDto.setType(TransactionType.LOCAL_PURCHASE);
        transactionDto.setTransactionId(null);
        transactionDto.setOrderId(orderDto.getOrderId());

        System.out.println("Saldo: " + this.walletRestClient
                .addTransactionToWallet(userDto.getUserId(), transactionDto));
        System.out.println("Transazioni: " + this.walletRestClient
                .getUserTransactions(userDto.getUserId()));
        return "ok";
    }
}
