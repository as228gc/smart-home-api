package dev.alexsoderberg.smart_home_api.dto;

public class IkeaLightRequest {
  private String name;
  private int lightId;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLightId() {
    return this.lightId;
  }

  public void setLightId(int lightId){
    this.lightId = lightId;
  }
}
