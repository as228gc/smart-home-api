package dev.alexsoderberg.smart_home_api.service;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.pskstore.AdvancedPskStore;
import org.eclipse.californium.scandium.dtls.pskstore.AdvancedSinglePskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexsoderberg.smart_home_api.config.TradfriConfig;
import dev.alexsoderberg.smart_home_api.model.IkeaLight;

import java.net.InetSocketAddress;
import java.net.URI;

@Service
public class TradfriGatewayService {

  private final TradfriConfig config;

  @Autowired
  public TradfriGatewayService(TradfriConfig config) {
    this.config = config;
  }

  private CoapClient createSecureCoapClient(int lightId) {
    try {

      Configuration configuration = Configuration.createStandardWithoutFile();

      AdvancedPskStore pskStore = new AdvancedSinglePskStore(config.getIdentity(), config.getApiKey().getBytes());

      // Build DTLS Configuration with PSK (Pre-Shared Key)
      DtlsConnectorConfig dtlsConfig = DtlsConnectorConfig.builder(configuration)
          .setAddress(new InetSocketAddress(0)) // Bind to any available port
          .setAdvancedPskStore(pskStore)
          .build();

      // Create DTLS Connector
      DTLSConnector dtlsConnector = new DTLSConnector(dtlsConfig);

      // Set the CoAP URI
      String coapUri = String.format("coaps://%s:5684/15001/%d", config.getGatewayUrl(), lightId);

      // Create CoAP Client with DTLS Connector
      CoapClient client = new CoapClient(URI.create(coapUri));
      client.setEndpoint(new CoapEndpoint.Builder()
          .setConnector(dtlsConnector)
          .build());

      return client;

    } catch (Exception e) {
      throw new RuntimeException("Failed to create secure CoAP client: " + e.getMessage(), e);
    }
  }

  public boolean turnOnLight(IkeaLight light) {
    return sendCommandToGateway(light.getLightId(), true);
  }

  public boolean turnOffLight(IkeaLight light) {
    return sendCommandToGateway(light.getLightId(), false);
  }

  private boolean sendCommandToGateway(int lightId, boolean turnOn) {
    CoapClient client = createSecureCoapClient(lightId);

    String payload = String.format("{\"3311\":[{\"5850\":%d}]}", turnOn ? 1 : 0);

    try {
      CoapResponse response = client.put(payload, MediaTypeRegistry.APPLICATION_JSON);

      if (response != null && response.isSuccess()) {
        System.out.println("Command sent successfully: " + response.getResponseText());
        return true;
      } else {
        System.err
            .println("Failed to send command: " + (response != null ? response.getResponseText() : "No response"));
        return false;
      }
    } catch (Exception e) {
      System.err.println("Error sending CoAP request: " + e.getMessage());
      return false;
    } finally {
      client.shutdown();
    }
  }
}
