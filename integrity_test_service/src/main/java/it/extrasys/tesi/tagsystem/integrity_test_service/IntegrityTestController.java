package it.extrasys.tesi.tagsystem.integrity_test_service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.corners.CornerRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.meals.MealRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.orders.OrderRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.users.UsersRestClient;
import it.extrasys.tesi.tagsystem.integrity_test_service.restclients.wallets.WalletRestClient;

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

    @RequestMapping("/users")
    public String userTest() {
        UserDto userDto = new UserDto();
        userDto.setEmail("prova@prova");
        userDto.setName("clark");
        userDto.setPassword("password");
        userDto = this.usersRestClient.addUser(userDto);

        UserDto userDto2 = new UserDto();
        userDto2.setEmail("prova2@prova");
        userDto2.setName("clark2");
        userDto2.setPassword("password2");
        userDto2 = this.usersRestClient.addUser(userDto2);

        NfcTagDto nfcTagDto = new NfcTagDto();
        nfcTagDto.setNfcId("prova");
        NfcTagDto nfcTagDto2 = new NfcTagDto();
        nfcTagDto2.setNfcId("prova2");
        userDto = this.usersRestClient.addNfc(userDto, nfcTagDto);
        userDto = this.usersRestClient.addNfc(userDto, nfcTagDto2);

        System.out.println(this.usersRestClient.getById(userDto.getUserId()));

        NfcTagDto copy = new NfcTagDto();
        copy.setNfcId(nfcTagDto.getNfcId());

        nfcTagDto.setDisabled(true);

        this.usersRestClient.updateNfc(userDto.getUserId(), copy, nfcTagDto);
        System.out.println("Info utente: "
                + this.usersRestClient.getById(userDto.getUserId()));
        return "ok";
    }

    @RequestMapping("/wallets")
    public String walletTest() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        UserDto userDto = this.usersRestClient.getById(1L);
        OrderDto orderDto = this.orderRestClient
                .getOrdersByNfc(userDto.getNfcTags().get(0).getNfcId()).get(0);
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

    @RequestMapping("/menus")
    public String menuTest() throws ParseException {
        MealDto mealDto = new MealDto();
        mealDto.setPrice(new BigDecimal(10));
        mealDto.setDescription("prova");
        mealDto.setType(MealType.DESSERT);
        mealDto = this.mealRestClient.addMeal(mealDto);

        MealDto mealDto2 = new MealDto();
        mealDto2.setPrice(new BigDecimal(10));
        mealDto2.setDescription("prova2");
        mealDto2.setType(MealType.MEAT);
        mealDto2 = this.mealRestClient.addMeal(mealDto2);

        MealDto[] list = this.mealRestClient.getAllMeals();
        System.out.println("Meals in db: " + list);

        MenuDto menuDto = new MenuDto();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        menuDto.setDate(dFormat.parse("2017-07-31"));
        menuDto.setType("Generic");

        menuDto = this.mealRestClient.addMenu(menuDto);
        menuDto = this.mealRestClient.addMealToMenu(mealDto, menuDto);
        menuDto = this.mealRestClient.addMealToMenu(mealDto2, menuDto);

        menuDto.getMeals().remove(mealDto);
        menuDto = this.mealRestClient.updateMenu(menuDto);
        System.out.println("Menu : " + menuDto.getMeals());
        return "Ok";
    }
    @RequestMapping("/corner")
    public String mainTest() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<MealDto> meals = Arrays.asList(this.mealRestClient.getAllMeals());

        CornerDto cornerDto = new CornerDto();
        cornerDto.setMealId(meals.get(0).getMealId());
        NfcReaderDto nfcReaderDto = new NfcReaderDto();
        nfcReaderDto.setReaderId("provareader");

        cornerDto = this.cornerRestClient.addCorner(cornerDto);
        System.out.println("Id corner:" + cornerDto.getCornerId());
        cornerDto.setReader(nfcReaderDto);
        nfcReaderDto = this.cornerRestClient.addReader(nfcReaderDto);
        System.out.println("Id reader:" + nfcReaderDto.getReaderId());
        cornerDto = this.cornerRestClient.updateCorner(cornerDto,
                meals.get(1).getMealId(), nfcReaderDto.getReaderId());

        System.out
                .println(
                        "Ordine con meal aggiunto: " + this.cornerRestClient
                                .callAddMealFromUser(nfcReaderDto,
                                        this.usersRestClient.getById(1L)
                                                .getNfcTags().get(1))
                                .getMealId());

        return "ok";
    }

    @RequestMapping("/matchprice")
    public String matchAndPriceTest() throws ParseException {
        List<OrderDto> orders = this.orderRestClient.getOrdersByNfc("prova");
        System.out.println("Prezzo ordine :"
                + this.orderRestClient.getTotal(orders.get(0).getOrderId()));
        return "ok";
    }
    @RequestMapping("/orders")
    public String orderTest() throws ParseException {

        List<MealDto> meals = Arrays.asList(this.mealRestClient.getAllMeals());

        MealDto mealDto = meals.get(0);
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        OrderDto orderDto = new OrderDto();
        orderDto.setData(dFormat.parse("2017-08-01"));
        orderDto.setNfcId("prova");
        List<Long> mealids = new ArrayList<>();
        mealids.add(mealDto.getMealId());
        mealids.add(meals.get(1).getMealId());
        orderDto.setMealId(mealids);

        orderDto = this.orderRestClient.addOrder(orderDto);
        System.out.println("Not closed status: "
                + this.cornerRestClient.getOrdersByStatus(false));
        this.orderRestClient.closeOrder(orderDto.getOrderId());

        return "oK";
    }
    @RequestMapping("/confs")
    public String confTest() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<MealDto> meals = Arrays.asList(this.mealRestClient.getAllMeals());
        List<MealType> mealTypes = meals.stream().map(meal -> meal.getType())
                .collect(Collectors.toList());
        ConfigurationDto configurationDto = new ConfigurationDto();
        configurationDto.setName("Prova1");
        configurationDto.setSpecialPrice(new BigDecimal(5));
        configurationDto.setStartDate(dFormat.parse("2017-07-31"));
        configurationDto.setEndDate(dFormat.parse("2017-08-01"));

        configurationDto.getMealtypes().addAll(mealTypes);

        configurationDto = this.orderRestClient
                .addConfiguration(configurationDto);

        System.out.println(
                "ID configuration: " + configurationDto.getConfigurationId());
        configurationDto.setName("Prova update");
        configurationDto = this.orderRestClient
                .updateConfiguration(configurationDto);
        System.out.println("Nome configurazione aggiornato: "
                + configurationDto.getName());

        System.out.println(this.orderRestClient
                .getConfigurationById(configurationDto.getConfigurationId()));
        return "ok";
    }
}
