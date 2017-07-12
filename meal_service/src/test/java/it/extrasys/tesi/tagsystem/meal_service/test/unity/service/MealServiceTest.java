package it.extrasys.tesi.tagsystem.meal_service.test.unity.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.extrasys.tesi.tagsystem.meal_service.MealManagingApplication;
import it.extrasys.tesi.tagsystem.meal_service.api.MealDto;
import it.extrasys.tesi.tagsystem.meal_service.api.MealDtoConverter;
import it.extrasys.tesi.tagsystem.meal_service.api.MenuDto;
import it.extrasys.tesi.tagsystem.meal_service.db.MealManaging;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MenuEntity;
import it.extrasys.tesi.tagsystem.meal_service.test.JsonConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderServiceIntegrationTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MealManagingApplication.class)
@WebAppConfiguration
public class MealServiceTest {

    /** The content type. */
    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    /** The mock mvc. */
    private MockMvc mockMvc;

    /** The web application context. */
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JsonConverter jsonConverter;

    /** The conf managing. */
    @MockBean
    private MealManaging mealManager;;

    /**
     * Setup.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext).build();
    }
    int mealId = 0;
    private MealDto createMealDto(MealEntity mealEntity) {
        MealDto mealDto = new MealDto();
        mealDto.setDescription(mealEntity.getDescription());
        mealDto.setMealId(mealEntity.getMealId());
        mealDto.setPrice(mealEntity.getPrice());
        mealDto.setType(mealEntity.getType());
        return mealDto;
    }
    private MealEntity createMealEntity() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setPrice(new BigDecimal("5.0"));
        mealEntity.setDescription("prova");

        mealEntity.setType(MealType.MEAT);
        return mealEntity;
    }
    private MealEntity mockMealId(MealEntity mealEntity) {
        mealEntity.setMealId((long) this.mealId++);
        return mealEntity;

    }
    private MenuEntity createMenuEntity() {
        MenuEntity entity = new MenuEntity();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            entity.setDate(dFormat.parse("2017-06-27"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        entity.setType("Generic");
        return entity;

    }
    private MenuDto createMenuDto(MenuEntity entity) {
        MenuDto menuDto = new MenuDto();
        menuDto.setDate(entity.getDate());
        menuDto.setMenuId(entity.getMenuId());
        menuDto.setType(entity.getType());
        menuDto.setMeals(new ArrayList<MealDto>());
        for (MealEntity meal : entity.getMeals()) {
            menuDto.getMeals().add(MealDtoConverter.mealEntitytoDto(meal));
        }
        return menuDto;
    }
    int menuId = 0;
    private MenuEntity mockIdMenu(MenuEntity menuEntity) {
        menuEntity.setMenuId((long) this.menuId++);
        return menuEntity;
    }
    @Test
    public void addMealTest() throws IOException, Exception {
        this.mealId = 0;
        MealEntity mealEntity = createMealEntity();
        Mockito.when(this.mealManager.addMeal(any(MealEntity.class)))
                .thenReturn(mockMealId(mealEntity));
        this.mockMvc
                .perform(post("/meals/").contentType(this.contentType).content(
                        this.jsonConverter.json(createMealDto(mealEntity))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.mealId", is(0)))
                .andExpect(jsonPath("$.description", is("prova")));
    }
    @Test
    public void getAllMealsTest() throws Exception {
        List<MealEntity> meals = new ArrayList<>();
        meals.add(mockMealId(createMealEntity()));
        meals.add(mockMealId(createMealEntity()));
        meals.add(mockMealId(createMealEntity()));
        Mockito.when(this.mealManager.getAllMeal()).thenReturn(meals);
        this.mockMvc.perform(get("/meals/")).andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].mealId", is(0)))
                .andExpect(jsonPath("$[1].mealId", is(1)))
                .andExpect(jsonPath("$[2].mealId", is(2)));

    }

    @Test
    public void getMenuOfDay() throws Exception {
        MenuEntity menuEntity = mockIdMenu(createMenuEntity());
        List<MenuEntity> menulist = new ArrayList<>();
        menulist.add(menuEntity);
        Mockito.when(this.mealManager.getMenuByDate(any()))
                .thenReturn(menulist);
        this.mockMvc
                .perform(get("/menus/?day=2017-07-20")
                        .contentType(this.contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].menuId", is(0)))
                .andExpect(jsonPath("$[0].date", is("2017-06-27")));

    }
    @Test
    public void addDaylyMenu() throws Exception {
        this.menuId = 0;
        MenuEntity test = createMenuEntity();
        Mockito.when(this.mealManager.addMenu(any(MenuEntity.class)))
                .thenReturn(mockIdMenu(test));

        this.mockMvc
                .perform(post("/menus/").contentType(this.contentType)
                        .content(this.jsonConverter
                                .json(createMenuDto(createMenuEntity()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.date", is("2017-06-27")))
                .andExpect(jsonPath("$.menuId", is(0)));
    }
    private MealEntity mockAddMealToMenu(MealEntity mealEntity,
            MenuEntity menuEntity) {
        mealEntity.addToMenu(menuEntity);
        return mealEntity;
    }
    @Test
    public void addmealToMenu() throws Exception {
        this.menuId = 0;
        this.mealId = 0;
        MenuEntity testMenu = createMenuEntity();
        MealEntity testMeal = createMealEntity();
        Mockito.when(this.mealManager.getMeal(anyLong()))
                .thenReturn(mockMealId(testMeal));
        Mockito.when(this.mealManager.updateMeal(any(MealEntity.class)))
                .thenReturn(mockAddMealToMenu(testMeal, testMenu));
        Mockito.when(this.mealManager.getMenu(anyLong()))
                .thenReturn(mockIdMenu(testMenu));
        this.mockMvc.perform(put("/menus/0/?mealId=0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.date", is("2017-06-27")))
                .andExpect(jsonPath("$.menuId", is(0)))
                .andExpect(jsonPath("$.meals", Matchers.hasSize(1)));
    }
    @Test
    public void updateMenu() throws Exception {
        this.menuId = 0;
        MenuEntity testMenu = createMenuEntity();
        Mockito.when(this.mealManager.getMenu(anyLong()))
                .thenReturn(mockIdMenu(testMenu));
        Mockito.when(this.mealManager.updateMenu(any(MenuEntity.class)))
                .thenReturn(testMenu);

        this.mockMvc
                .perform(put("/menus/").contentType(this.contentType)
                        .content(this.jsonConverter
                                .json(createMenuDto(createMenuEntity()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.date", is("2017-06-27")))
                .andExpect(jsonPath("$.menuId", is(0)))
                .andExpect(jsonPath("$.meals", Matchers.hasSize(0)));
    }
    @Test
    public void updateMeal() throws Exception {
        this.mealId = 0;
        MealEntity testmeal = mockMealId(createMealEntity());
        Mockito.when(this.mealManager.getMeal(anyLong())).thenReturn(testmeal);
        Mockito.when(this.mealManager.updateMeal(any(MealEntity.class)))
                .thenReturn(testmeal);

        this.mockMvc
                .perform(put("/meals/").contentType(this.contentType)
                        .content(this.jsonConverter
                                .json(createMealDto(createMealEntity()))))
                .andExpect(status().isOk())

                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.mealId", is(0)))
                .andExpect(jsonPath("$.price", is(equalTo(5.0))));
    }
}
