package it.extrasys.tesi.tagsystem.corner_service.test.unity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
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

import it.extrasys.tesi.tagsystem.corner_service.CornerServiceApplication;
import it.extrasys.tesi.tagsystem.corner_service.api.CornerDto;
import it.extrasys.tesi.tagsystem.corner_service.api.CornerDtoConverter;
import it.extrasys.tesi.tagsystem.corner_service.api.NfcReaderDto;
import it.extrasys.tesi.tagsystem.corner_service.api.NfcReaderDtoConverter;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.CornerEntity;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;
import it.extrasys.tesi.tagsystem.corner_service.db.manager.CornerManager;
import it.extrasys.tesi.tagsystem.corner_service.db.manager.NfcReaderManager;
import it.extrasys.tesi.tagsystem.corner_service.test.JsonConverterImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CornerServiceApplication.class)
@WebAppConfiguration
public class CornerServiceUnityTest {

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
    private JsonConverterImpl jsonConverter;

    /** The conf managing. */
    @MockBean
    private CornerManager cornerManager;
    @MockBean
    private NfcReaderManager readerManager;

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
    private CornerDto cornerToDto(CornerEntity corner) {

        CornerDto dto = new CornerDto();
        dto.setCornerId(corner.getCornerId());
        dto.setMealId(corner.getMealId());
        dto.setReader(NfcReaderDtoConverter.toDto(corner.getReader()));
        return dto;
    }
    private CornerEntity createCornerEntity() {
        CornerEntity corner = new CornerEntity();
        corner.setMealId(1L);
        return corner;
    }
    private long cornerid = 0;
    private CornerEntity mockId(CornerEntity entity) {
        entity.setCornerId(this.cornerid++);
        return entity;
    }
    private int nfcId = 0;
    private NfcReaderEntity createNfcEntity() {
        NfcReaderEntity nfcReaderEntity = new NfcReaderEntity();
        nfcReaderEntity.setReaderId("prova" + this.nfcId++);
        return nfcReaderEntity;
    }
    private NfcReaderDto readerToDto(NfcReaderEntity reader) {
        NfcReaderDto dto = new NfcReaderDto();
        dto.setReaderId(reader.getReaderId());
        return dto;
    }
    private CornerEntity mockNfcReader(CornerEntity entity) {

        entity.setReader(createNfcEntity());
        return entity;
    }
    private CornerEntity mockUpdateMeal(CornerEntity entity) {
        entity.setMealId(2L);
        return entity;
    }
    @Test
    public void getAllCornersTest() throws Exception {
        this.cornerid = 0;
        this.nfcId = 0;
        CornerEntity test = mockId(createCornerEntity());
        List<CornerEntity> list = new ArrayList<>();
        list.add(test);
        Mockito.when(this.cornerManager.getAll()).thenReturn(list);
        this.mockMvc.perform(get("/corners/")).andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].cornerId", is(0)));

        test = mockNfcReader(mockId(createCornerEntity()));
        list = new ArrayList<>();
        list.add(test);
        Mockito.when(this.cornerManager.getAll()).thenReturn(list);
        this.mockMvc.perform(get("/corners/")).andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].cornerId", is(1)))
                .andExpect(jsonPath("$[0].reader.readerId", is("prova0")));
    }
    @Test
    public void getCornerByIdTest() throws Exception {
        this.cornerid = 0;
        this.nfcId = 0;
        CornerEntity test = mockId(createCornerEntity());

        Mockito.when(this.cornerManager.getById(anyLong())).thenReturn(test);
        this.mockMvc.perform(get("/corners/0")).andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.cornerId", is(0)))
                .andExpect(jsonPath("$.reader", is(nullValue())));

    }
    @Test
    public void addCornerTest() throws IOException, Exception {
        this.cornerid = 0;
        CornerEntity test = createCornerEntity();
        Mockito.when(this.cornerManager.add(any(CornerEntity.class)))
                .thenReturn(mockId(test));
        this.mockMvc
                .perform(post("/corners/").contentType(this.contentType)
                        .content(this.jsonConverter.json(CornerDtoConverter
                                .toDto(createCornerEntity()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.cornerId", is(0)));
    }

    @Test
    public void updateCornerTest() throws IOException, Exception {
        this.cornerid = 0;
        this.nfcId = 0;
        CornerEntity test = mockId(createCornerEntity());

        CornerEntity testUpdated = mockUpdateMeal(test);
        NfcReaderEntity testnfc = createNfcEntity();
        testUpdated.setReader(testnfc);
        Mockito.when(this.cornerManager.updateReader(anyLong(), anyString()))
                .thenReturn(null);
        Mockito.when(this.cornerManager.updateMealId(anyLong(), anyLong()))
                .thenReturn(null);
        Mockito.when(this.cornerManager.getById(anyLong()))
                .thenReturn(testUpdated);
        this.mockMvc
                .perform(put("/corners/0?mealId=2&readerId=prova0")
                        .contentType(this.contentType)
                        .content(this.jsonConverter
                                .json(CornerDtoConverter.toDto(test))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.cornerId", is(0)))
                .andExpect(jsonPath("$.mealId", is(2)))
                .andExpect(jsonPath("$.reader.readerId", is("prova0")));
    }
    @Test
    public void addNfcReaderTest() throws IOException, Exception {
        this.nfcId = 0;
        NfcReaderEntity test = createNfcEntity();
        Mockito.when(this.readerManager.add(any(NfcReaderEntity.class)))
                .thenReturn(test);
        this.mockMvc
                .perform(post("/readers/").contentType(this.contentType)
                        .content(this.jsonConverter.json(NfcReaderDtoConverter
                                .toDto(createNfcEntity()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.readerId", is("prova0")));
    }
    @Test
    public void updateNfcReaderTest() throws IOException, Exception {
        this.nfcId = 0;
        NfcReaderEntity test = createNfcEntity();
        test.setReaderId("prova1");
        Mockito.when(this.readerManager.update(any(NfcReaderEntity.class)))
                .thenReturn(test);
        this.mockMvc
                .perform(put("/readers/").contentType(this.contentType)
                        .content(this.jsonConverter.json(NfcReaderDtoConverter
                                .toDto(createNfcEntity()))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.readerId", is("prova1")));
    }
}
