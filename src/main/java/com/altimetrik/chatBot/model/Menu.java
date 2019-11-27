package com.altimetrik.chatBot.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Menu
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-27T09:47:09.303Z")

public class Menu   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("count")
  private String count = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("description")
  private String description = null;

  public Menu id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * menu id
   * @return id
  **/
  @ApiModelProperty(value = "menu id")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Menu name(String name) {
    this.name = name;
    return this;
  }

  /**
   * battery order number
   * @return name
  **/
  @ApiModelProperty(value = "battery order number")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Menu count(String count) {
    this.count = count;
    return this;
  }

  /**
   * status invoice
   * @return count
  **/
  @ApiModelProperty(value = "status invoice")


  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public Menu type(String type) {
    this.type = type;
    return this;
  }

  /**
   * status description
   * @return type
  **/
  @ApiModelProperty(value = "status description")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Menu description(String description) {
    this.description = description;
    return this;
  }

  /**
   * created invoice date
   * @return description
  **/
  @ApiModelProperty(value = "created invoice date")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Menu menu = (Menu) o;
    return Objects.equals(this.id, menu.id) &&
        Objects.equals(this.name, menu.name) &&
        Objects.equals(this.count, menu.count) &&
        Objects.equals(this.type, menu.type) &&
        Objects.equals(this.description, menu.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, count, type, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Menu {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

