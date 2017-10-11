package it.extrasys.tesi.tagsystem.corner_service.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.extrasys.tesi.tagsystem.corner_service.api.CornerDto;
import it.extrasys.tesi.tagsystem.corner_service.api.CornerDtoConverter;
import it.extrasys.tesi.tagsystem.corner_service.api.CornerRestClient;
import it.extrasys.tesi.tagsystem.corner_service.api.NfcReaderDto;
import it.extrasys.tesi.tagsystem.corner_service.api.NfcReaderDtoConverter;
import it.extrasys.tesi.tagsystem.corner_service.api.OrderDto;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.CornerEntity;
import it.extrasys.tesi.tagsystem.corner_service.db.jpa.entity.NfcReaderEntity;
import it.extrasys.tesi.tagsystem.corner_service.db.manager.CornerManager;
import it.extrasys.tesi.tagsystem.corner_service.db.manager.NfcReaderManager;

@RestController
public class CornerController {

  @Autowired
  private CornerManager cornerManager;

  @Autowired
  private NfcReaderManager readerManager;

  @Autowired
  private CornerRestClient restClient;

  @RequestMapping(value = "/corners", method = RequestMethod.GET)
  public List<CornerDto> getAllCorners() {
    return CornerDtoConverter.toDtoList(this.cornerManager.getAll());
  }

  @RequestMapping(value = "/corners/{id}", method = RequestMethod.GET)
  public CornerDto getCornerById(@PathVariable Long id) {
    return CornerDtoConverter.toDto(this.cornerManager.getById(id));
  }

  @RequestMapping(value = "/corners", method = RequestMethod.POST)
  public CornerDto addCorner(@RequestBody CornerDto cornerDto) {
    CornerEntity cornerEntity = CornerDtoConverter.toEntity(cornerDto);
    return CornerDtoConverter.toDto(this.cornerManager.add(cornerEntity));
  }

  @RequestMapping(value = "/readers", method = RequestMethod.POST)
  public NfcReaderDto addNfcReader(@RequestBody NfcReaderDto readerDto) {
    NfcReaderEntity readerEntity = NfcReaderDtoConverter.toEntity(readerDto);
    return NfcReaderDtoConverter.toDto(this.readerManager.add(readerEntity));
  }

  @RequestMapping(value = "/corners/{id}", method = RequestMethod.PUT)
  public CornerDto updateCorner(@PathVariable Long id, @RequestParam(required = false) String readerId,
      @RequestParam(required = false) Long mealId) {
    if (readerId != null) {
      this.cornerManager.updateReader(id, readerId);
    }
    if (mealId != null) {

      this.cornerManager.updateMealId(id, mealId);
    }
    return CornerDtoConverter.toDto(this.cornerManager.getById(id));

  }

  @RequestMapping(value = "/readers", method = RequestMethod.PUT)
  public NfcReaderDto updateNfcReader(@RequestBody NfcReaderDto readerDto) {
    NfcReaderEntity readerEntity = NfcReaderDtoConverter.toEntity(readerDto);
    readerDto.setReaderId(readerEntity.getReaderId());
    return readerDto;
  }

  @RequestMapping(value = "/readers", method = RequestMethod.GET)
  public NfcReaderDto getReader(@RequestParam String tag) {
    NfcReaderDto dto = new NfcReaderDto();
    dto.setReaderId(this.readerManager.getById(tag).getReaderId());
    return dto;
  }

  @RequestMapping(value = "/readers/{readerNfc}", method = RequestMethod.POST)
  public OrderDto callAddMealFromUser(@PathVariable String readerNfc, @RequestParam String userNfc) {

    CornerEntity cornerEntity = this.cornerManager.getByReader(readerNfc);
    return this.restClient.addMealToOrderFromCorner(userNfc, cornerEntity.getMealId());
  }
}
