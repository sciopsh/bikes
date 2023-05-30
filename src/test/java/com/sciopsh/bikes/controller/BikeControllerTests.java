package com.sciopsh.bikes.controller;

import com.sciopsh.bikes.model.Bike;
import com.sciopsh.bikes.repository.BikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class BikeControllerTests {

    @Mock
    BikeRepository repo;

    @InjectMocks
    BikeController controller;


    @Test
    public void TestsWork() {
        assertTrue(true);
    }

    @Test
    public void CreateBikeShouldCallTheRepo() {
        controller.createBike(new Bike());
        Mockito.verify(repo, Mockito.times(1)).save(any());
    }

    private static Stream<Arguments> getFilterKeys() {
        return Stream.of(
                Arguments.of("name", new int[]{1, 0, 0}),
                Arguments.of("Name", new int[]{1, 0, 0}),
                Arguments.of("NAME", new int[]{1, 0, 0}),
                Arguments.of("bIkE.NaMe", new int[]{1, 0, 0}),
                Arguments.of("mANufACtuREr", new int[]{0, 1, 0}),
                Arguments.of("bike.manufacturer", new int[]{0, 1, 0}),
                Arguments.of("type", new int[]{0, 0, 1}),
                Arguments.of("item.type", new int[]{0, 0, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("getFilterKeys")
    public void SearchBikesShouldCallTheCorrespondingMethod(String input, int[] calls) {
        controller.searchBikes(input, "", "");
        Mockito.verify(repo, Mockito.times(calls[0])).findAllByName(any(), any());
        Mockito.verify(repo, Mockito.times(calls[1])).findAllByManufacturer(any(), any());
        Mockito.verify(repo, Mockito.times(calls[2])).findAllByItems(any(), any());
    }

    @Test
    public void SearchBikesThrowsOnIllegalKey() {
        assertThrows(IllegalArgumentException.class, () -> controller.searchBikes("", "", ""));
    }

    private static Stream<Arguments> getOrders() {
        return Stream.of(
                Arguments.of("asc", Sort.Direction.ASC, "name"),
                Arguments.of("ascENDing", Sort.Direction.ASC, "name"),
                Arguments.of("DESC", Sort.Direction.DESC, "name"),
                Arguments.of("descENDing", Sort.Direction.DESC, "name"),
                Arguments.of(null, Sort.DEFAULT_DIRECTION, "name"),
                Arguments.of("", Sort.DEFAULT_DIRECTION, "name")
        );
    }

    @ParameterizedTest
    @MethodSource("getOrders")
    public void SearchBikesCorrectlyHandlesSortingArguments(String order, Sort.Direction expectedDirection,
                                                            String expectedKey) {
        controller.searchBikes("name", "", order);

        Mockito.verify(repo, Mockito.times(1))
                .findAllByName(any(), eq(Sort.by(new Sort.Order(expectedDirection, expectedKey))));
    }

    @Test
    public void SearchBikesThrowsOnIllegalOrder() {
        assertThrows(IllegalArgumentException.class, () -> controller.searchBikes("name", "", "asd"));
    }

    @Test
    public void GetBikeCallsTheCorrectMethod() {
        controller.getBike("123");
        Mockito.verify(repo, Mockito.times(1)).findById("123");
    }
}
