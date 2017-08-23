package it.extrasys.tesi.tagsystem.integrity_test_service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.AddMealDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.ConfigurationDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.ListMealTypeDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.OrderDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.OrderType;
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

    @RequestMapping("/1")
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

        return "ok";
    }
    @RequestMapping("/2")
    public String userTest2() {
        UserDto userDto = this.usersRestClient.getById(1L);
        NfcTagDto nfcTagDto = new NfcTagDto();
        nfcTagDto.setNfcId("prova");
        NfcTagDto nfcTagDto2 = new NfcTagDto();
        nfcTagDto2.setNfcId("prova2");
        userDto = this.usersRestClient.addNfc(userDto, nfcTagDto);
        userDto = this.usersRestClient.addNfc(userDto, nfcTagDto2);

        System.out.println(this.usersRestClient.getById(userDto.getUserId()));
        return "ok";
    }
    @RequestMapping("/3")
    public String userTest3() {
        UserDto userDto = this.usersRestClient.getById(1L);

        NfcTagDto nfcTagDto = userDto.getNfcTags().get(0);
        NfcTagDto copy = new NfcTagDto();
        copy.setNfcId(nfcTagDto.getNfcId());

        nfcTagDto.setDisabled(true);

        this.usersRestClient.updateNfc(userDto.getUserId(), copy, nfcTagDto);

        return "ok";
    }
    @RequestMapping("/4")
    public String userTest4() {
        UserDto userDto = this.usersRestClient.getById(1L);

        System.out.println("Info utente: " + userDto.getName() + " "
                + userDto.getEmail() + " " + userDto.getNfcTags().size());
        return "ok";
    }
    @RequestMapping("/5")
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

        MealDto mealDto3 = new MealDto();
        mealDto3.setPrice(new BigDecimal(10));
        mealDto3.setDescription("prova3");
        mealDto3.setType(MealType.DRINK);
        mealDto3 = this.mealRestClient.addMeal(mealDto3);
        return "Ok";
    }

    @RequestMapping("/6")
    public String menuTest2() throws ParseException {
        MealDto[] list = this.mealRestClient.getAllMeals();
        for (MealDto mealDto : list) {
            System.out.println("Meals in db: " + mealDto.getDescription());
        }
        return "ok";
    }
    @RequestMapping("/7")
    public String menuTest3() throws ParseException {
        MenuDto menuDto = new MenuDto();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        menuDto.setDate(dFormat.parse("2017-07-31"));
        menuDto.setType("Generic");
        this.mealRestClient.addMenu(menuDto);
        return "ok";
    }
    @RequestMapping("/8")
    public String menuTest4() throws ParseException {
        MealDto[] list = this.mealRestClient.getAllMeals();
        MenuDto menuDto = this.mealRestClient.getMenu(1L);
        menuDto = this.mealRestClient.addMenu(menuDto);
        menuDto = this.mealRestClient.addMealToMenu(list[0], menuDto);
        menuDto = this.mealRestClient.addMealToMenu(list[1], menuDto);
        menuDto.getMeals().remove(list[0]);
        menuDto = this.mealRestClient.updateMenu(menuDto);

        return "ok";
    }
    @RequestMapping("/9")
    public String menuTest5() throws ParseException {
        MenuDto menuDto = this.mealRestClient.getMenu(1L);

        System.out.println("Menu : " + menuDto.getMeals());

        return "ok";
    }

    @RequestMapping("/10")
    public String confTest() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        ConfigurationDto configurationDto = new ConfigurationDto();
        configurationDto.setName("Prova1");
        configurationDto.setSpecialPrice(new BigDecimal(5));
        configurationDto.setStartDate(dFormat.parse("2017-07-31"));
        configurationDto.setEndDate(dFormat.parse("2017-08-01"));

        ConfigurationDto configurationDto2 = new ConfigurationDto();
        configurationDto2.setName("Prova2");
        configurationDto2.setSpecialPrice(new BigDecimal(6));
        configurationDto2.setStartDate(dFormat.parse("2017-07-31"));
        configurationDto2.setEndDate(dFormat.parse("2017-08-01"));

        configurationDto = this.orderRestClient
                .addConfiguration(configurationDto);
        configurationDto = this.orderRestClient
                .addConfiguration(configurationDto2);

        return "ok";
    }
    @RequestMapping("/11")
    public String confTest1() throws ParseException {
        ConfigurationDto configurationDto = this.orderRestClient
                .getConfigurationById(1L);
        System.out.println(
                "ID configuration: " + configurationDto.getConfigurationId());
        configurationDto.setName("Prova update");
        configurationDto = this.orderRestClient
                .updateConfiguration(configurationDto);
        System.out.println("Nome configurazione aggiornato: "
                + configurationDto.getName());

        return "ok";
    }
    @RequestMapping("/11a")
    public String confTest2() throws ParseException {
        ConfigurationDto configurationDto = this.orderRestClient
                .getConfigurationById(1L);
        ConfigurationDto configurationDto2 = this.orderRestClient
                .getConfigurationById(2L);
        List<MealDto> meals = Arrays.asList(this.mealRestClient.getAllMeals());
        List<MealType> mealTypes = meals.stream().map(meal -> meal.getType())
                .collect(Collectors.toList());
        configurationDto.getMealtypes().addAll(mealTypes);
        configurationDto2.getMealtypes().add(meals.get(0).getType());
        this.orderRestClient.updateConfiguration(configurationDto);
        this.orderRestClient.updateConfiguration(configurationDto2);
        return "ok";
    }
    @RequestMapping("/11b") // correggere stampa
    public String confTest3() throws ParseException {
        ConfigurationDto configurationDto = this.orderRestClient
                .getConfigurationById(1L);
        System.out.println("Configurazione: " + configurationDto.getName() + " "
                + configurationDto.getMealtypes() + " "
                + configurationDto.getStarDate());
        return "ok";
    }
    @RequestMapping("/12")
    public String cornerTest() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<MealDto> meals = Arrays.asList(this.mealRestClient.getAllMeals());
        CornerDto cornerDto = new CornerDto();
        cornerDto.setMealId(meals.get(0).getMealId());

        cornerDto = this.cornerRestClient.addCorner(cornerDto);
        System.out.println("Id corner:" + cornerDto.getCornerId());

        return "ok";
    }

    @RequestMapping("/13-14")
    public String cornerTest3() throws ParseException {
        CornerDto cornerDto = this.cornerRestClient.getCornerById(1L);
        List<MealDto> meals = Arrays.asList(this.mealRestClient.getAllMeals());

        NfcReaderDto nfcReaderDto = new NfcReaderDto();

        nfcReaderDto.setReaderId("provareader");
        cornerDto.setReader(nfcReaderDto);
        nfcReaderDto = this.cornerRestClient.addReader(nfcReaderDto);

        System.out.println("Id reader:" + nfcReaderDto.getReaderId());

        cornerDto = this.cornerRestClient.updateCorner(cornerDto,
                meals.get(1).getMealId(), nfcReaderDto.getReaderId());
        return "ok";
    }

    @RequestMapping("/15")
    public String orderTest() throws ParseException {
        NfcReaderDto nfcReaderDto = this.cornerRestClient
                .getReader("provareader");
        System.out.println(nfcReaderDto.getReaderId());
        System.out
                .println(
                        "Ordine con meal aggiunto: " + this.cornerRestClient
                                .callAddMealFromUser(nfcReaderDto,
                                        this.usersRestClient.getById(1L)
                                                .getNfcTags().get(1))
                                .getMealId());

        return "ok";
    }
    @RequestMapping("/16")
    public String orderTest2() throws ParseException {

        AddMealDto addMealDto = new AddMealDto();
        addMealDto.setMealId(2L);
        addMealDto.setNfc("prova");
        addMealDto.setTypeCaller(OrderType.LUNCHBOX);
        System.out.println("Ordine con meal aggiunto: "
                + this.orderRestClient.addMealToOrder(addMealDto));

        return "ok";
    }
    @RequestMapping("/17")
    public String orderTest3() throws ParseException {

        System.out.println("Open orders: " + this.orderRestClient
                .getOrdersByStatusAndNfc(false, "prova2").get(0).getOrderId());

        return "ok";
    }
    @RequestMapping("/18-19")
    public String priceTest() throws ParseException {
        List<OrderDto> orders = this.orderRestClient.getOrdersByNfc("prova");
        OrderDto orderDto = orders.get(0);
        System.out.println("Prezzo ordine :"
                + this.orderRestClient.getTotal(orderDto.getOrderId()));
        return "ok";
    }
    @RequestMapping("/20")
    public String closeTest() throws ParseException {
        List<OrderDto> orders = this.orderRestClient.getOrdersByNfc("prova");
        this.orderRestClient.closeOrder(orders.get(0).getOrderId());
        return "ok";
    }
    @RequestMapping("/21")
    public String walletTest() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        UserDto userDto = this.usersRestClient.getById(1L);

        OrderDto orderDto = this.orderRestClient
                .getOrdersByNfc(userDto.getNfcTags().get(0).getNfcId()).get(0);
        WalletDto walletDto = new WalletDto();
        walletDto.setUserId(userDto.getUserId());
        walletDto = this.walletRestClient.addWallet(walletDto);
        System.out.println("ID wallet: " + walletDto.getWalletId());

        return "ok";
    }
    @RequestMapping("/22")
    public String walletTest3() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        UserDto userDto = this.usersRestClient.getById(1L);
        WalletDto walletDto = this.walletRestClient.getUserWallet(1L);
        OrderTransactionDto transactionDto = new OrderTransactionDto();

        transactionDto.setPrice(new BigDecimal(10));
        transactionDto.setType(TransactionType.ADD_FUNDS);
        transactionDto.setUserNfc(userDto.getNfcTags().get(0).getNfcId());
        transactionDto.setWalletId(walletDto.getWalletId());
        transactionDto.setDate(dFormat.parse("2017-08-01"));

        System.out.println("Saldo: " + this.walletRestClient
                .addTransactionToWallet(userDto.getUserId(), transactionDto));
        return "ok";
    }
    @RequestMapping("/23")
    public String walletTest4() throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        UserDto userDto = this.usersRestClient.getById(1L);
        OrderTransactionDto transactionDto = new OrderTransactionDto();
        OrderDto orderDto = this.orderRestClient
                .getOrdersByNfc(userDto.getNfcTags().get(0).getNfcId()).get(0);

        transactionDto.setPrice(orderDto.getTotalPrice());
        transactionDto.setType(TransactionType.LOCAL_PURCHASE);
        transactionDto.setTransactionId(null);
        transactionDto.setOrderId(orderDto.getOrderId());

        transactionDto.setDate(new Date());
        transactionDto.setUserNfc(orderDto.getNfcId());
        this.walletRestClient.addTransactionToWallet(userDto.getUserId(),
                transactionDto);
        return "ok";
    }

    @RequestMapping("/24")
    public String walletTest5() throws ParseException {
        UserDto userDto = this.usersRestClient.getById(1L);
        System.out.println("Transazioni: " + this.walletRestClient
                .getUserTransactions(userDto.getUserId()));
        return "ok";
    }
    @RequestMapping("/match")
    public String matchTest() throws ParseException {

        List<MealDto> meals = Arrays.asList(this.mealRestClient.getAllMeals());
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        OrderDto orderDto = new OrderDto();
        orderDto.setData(dFormat.parse("2017-08-01"));
        orderDto.setNfcId("prova");
        List<Long> mealids = new ArrayList<>();
        mealids.add(meals.get(0).getMealId());
        mealids.add(meals.get(1).getMealId());

        mealids.add(meals.get(2).getMealId());

        orderDto.setMealId(mealids);
        orderDto.setType(OrderType.LOCAL_PURCHASE);
        ListMealTypeDto listMealTypeDto = new ListMealTypeDto();
        listMealTypeDto.getMealtypes().addAll(meals.stream()
                .map(meal -> meal.getType()).collect(Collectors.toList()));
        listMealTypeDto.getMealtypes().remove(2);
        listMealTypeDto.getMealtypes().remove(1);
        List<ConfigurationDto> confs = this.orderRestClient
                .matchConfiguration(listMealTypeDto);

        System.out.println("Conf suggerite: " + confs);

        return "ok";
    }

}
