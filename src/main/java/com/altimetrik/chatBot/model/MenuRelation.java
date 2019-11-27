package com.altimetrik.chatBot.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MenuRelation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-27T09:47:09.303Z")

public class MenuRelation   {
  @JsonProperty("parentNode")
  private BigDecimal parentNode = null;

  @JsonProperty("childNode")
  private BigDecimal childNode = null;

  public MenuRelation parentNode(BigDecimal parentNode) {
    this.parentNode = parentNode;
    return this;
  }

  /**
   * parent node value
   * @return parentNode
  **/
  @ApiModelProperty(value = "parent node value")

  @Valid

  public BigDecimal getParentNode() {
    return parentNode;
  }

  public void setParentNode(BigDecimal parentNode) {
    this.parentNode = parentNode;
  }

  public MenuRelation childNode(BigDecimal childNode) {
    this.childNode = childNode;
    return this;
  }

  /**
   * child node value
   * @return childNode
  **/
  @ApiModelProperty(value = "child node value")

  @Valid

  public BigDecimal getChildNode() {
    return childNode;
  }

  public void setChildNode(BigDecimal childNode) {
    this.childNode = childNode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuRelation menuRelation = (MenuRelation) o;
    return Objects.equals(this.parentNode, menuRelation.parentNode) &&
        Objects.equals(this.childNode, menuRelation.childNode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parentNode, childNode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuRelation {\n");
    
    sb.append("    parentNode: ").append(toIndentedString(parentNode)).append("\n");
    sb.append("    childNode: ").append(toIndentedString(childNode)).append("\n");
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

