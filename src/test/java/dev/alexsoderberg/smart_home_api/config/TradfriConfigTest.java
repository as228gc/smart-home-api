package dev.alexsoderberg.smart_home_api.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TradfriConfigTest {

    private TradfriConfig config;

    @BeforeEach
    void setUp() {
        config = mock(TradfriConfig.class);
        when(config.getGatewayUrl()).thenReturn("http://mock-gateway");
        when(config.getApiKey()).thenReturn("mock-api-key");
    }

    @Test
    public void testMockedConfiguration() {
        assertEquals("http://mock-gateway", config.getGatewayUrl());
        assertEquals("mock-api-key", config.getApiKey());
    }
}

