//package com.marian.tennis.api.tarifs.service;
//
//import com.marian.tennis.api.tarifs.entity.TarifsEntity;
//import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
//import com.marian.tennis.api.tarifs.repositories.TarifRepository;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(SpringRunner.class)
//public class TarifServiceTest {
//    @Mock
//    private TarifRepository tarifRepository;
//    @InjectMocks
//    TarifService tarifService;
//    private List<TarifsEntity> tarifs;
//    private TarifsEntity tarifsEntity;
//    private TarifsEntity tarifsEntity1;
//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();
//
//    @Before
//    public void setUp() {
//        tarifsEntity = TarifsEntity.builder()
//                .name("t1")
//                .startDate(LocalDate.of(2020, 03, 23))
//                .endDate(LocalDate.of(2020, 03, 24))
//                .startTime(LocalTime.of(22, 00))
//                .endTime(LocalTime.of(23, 00))
//                .prix(26)
//                .defaultTarif(false)
//                .actif(false)
//                .specialTarif(false)
//                .weekend(false)
//                .build();
//        tarifsEntity1 = TarifsEntity.builder()
//                .name("t2")
//                .startDate(LocalDate.of(2020, 04, 23))
//                .endDate(LocalDate.of(2020, 04, 24))
//                .startTime(LocalTime.of(8, 00))
//                .endTime(LocalTime.of(9, 00))
//                .prix(13)
//                .defaultTarif(false)
//                .actif(false)
//                .specialTarif(false)
//                .weekend(false)
//                .build();
//        tarifs = Arrays.asList(tarifsEntity, tarifsEntity1);
//
//    }
//
//    @Test
//    public void getTarifs() {
//        when(tarifRepository.findAll()).thenReturn(tarifs);
//        List<TarifsEntity> tarifs = tarifService.getTarifs();
//        assertThat(tarifs.size()).isEqualTo(2);
//        assertThat(tarifs.get(0).getName()).isEqualTo("t1");
//        assertThat(tarifs.get(0).getStartDate()).isEqualTo(LocalDate.of(2020, 03, 23));
//        assertThat(tarifs.get(0).getEndDate()).isEqualTo(LocalDate.of(2020, 03, 24));
//        assertThat(tarifs.get(0).getStartTime()).isEqualTo(LocalTime.of(22, 00));
//        assertThat(tarifs.get(0).getEndTime()).isEqualTo(LocalTime.of(23, 00));
//        assertThat(tarifs.get(0).getPrix()).isEqualTo(26);
//        assertThat(tarifs.get(0).getDefaultTarif()).isEqualTo(false);
//        assertThat(tarifs.get(0).getSpecialTarif()).isEqualTo(false);
//        assertThat(tarifs.get(0).isWeekend()).isEqualTo(false);
//        assertThat(tarifs.get(0).isActif()).isEqualTo(false);
//
//    }
//
//    @Test
//    public void getTarifByName_should_return_tarifs() {
//        when(tarifRepository.findByName("t1")).thenReturn(tarifs.get(0));
//        TarifsEntity t1 = tarifService.getTarif("t1");
//        assertThat(t1.getName()).isEqualTo("t1");
//        assertThat(t1.getStartDate()).isEqualTo(LocalDate.of(2020, 03, 23));
//        assertThat(t1.getEndDate()).isEqualTo(LocalDate.of(2020, 03, 24));
//        assertThat(t1.getStartTime()).isEqualTo(LocalTime.of(22, 00));
//        assertThat(t1.getEndTime()).isEqualTo(LocalTime.of(23, 00));
//        assertThat(t1.getPrix()).isEqualTo(26);
//        assertThat(t1.getDefaultTarif()).isEqualTo(false);
//        assertThat(t1.getSpecialTarif()).isEqualTo(false);
//        assertThat(t1.isWeekend()).isEqualTo(false);
//        assertThat(t1.isActif()).isEqualTo(false);
//    }
//
//    @Test
//    public void getTarifByName_should_throwNotFound() {
//        expectedException.expect(ResponseStatusException.class);
//        expectedException.expectMessage("404 NOT_FOUND \"tarif not found with this name");
//        when(tarifRepository.findByName("t1")).thenReturn(tarifs.get(0));
//        tarifService.getTarif("t2");
//
//    }
//
//    @Test
//    public void createTarif() {
//        RequestBodyTarif requestBodyTarif = RequestBodyTarif.builder()
//                .name("t1")
//                .startDate(LocalDate.of(2020, 03, 23))
//                .endDate(LocalDate.of(2020, 03, 24))
//                .startTime("22:00")
//                .endTime("23:00")
//                .prix(26f)
//                .defaultTarif(false)
//                .actif(false)
//                .specialTarif(false)
//                .weekend(false)
//                .build();
//
//        when(tarifRepository.save(tarifsEntity)).thenReturn(tarifsEntity);
//        TarifsEntity tarif = tarifService.createTarif(requestBodyTarif);
//        assertThat(tarif.getName()).isEqualTo("t1");
//        assertThat(tarif.getStartDate()).isEqualTo(LocalDate.of(2020, 03, 23));
//        assertThat(tarif.getEndDate()).isEqualTo(LocalDate.of(2020, 03, 24));
//        assertThat(tarif.getStartTime()).isEqualTo(LocalTime.of(22, 00));
//        assertThat(tarif.getEndTime()).isEqualTo(LocalTime.of(23, 00));
//        assertThat(tarif.getPrix()).isEqualTo(26);
//        assertThat(tarif.getDefaultTarif()).isEqualTo(false);
//        assertThat(tarif.getSpecialTarif()).isEqualTo(false);
//        assertThat(tarif.isWeekend()).isEqualTo(false);
//        assertThat(tarif.isActif()).isEqualTo(false);
//    }
//
//}