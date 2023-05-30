package com.sciopsh.bikes.controller;

import com.sciopsh.bikes.model.Bike;
import com.sciopsh.bikes.repository.BikeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class BikeControllerTests {

    @Mock
    private BikeController bikeController;
    @Mock
    private BikeRepository bikeRepository;

    @Test
    public void TestsWork() {
        assertEquals(true, true);
    }
}
