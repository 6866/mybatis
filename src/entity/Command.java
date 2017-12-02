package entity;

import java.util.List;

public class Command {
  private Integer id;
  private String command;
  private String description;
  private List<Command_content> contents;

  public Command() {
      super();
  }

  public Command(Integer id, String command, String description, List<Command_content> contents) {
    this.id = id;
    this.command = command;
    this.description = description;
    this.contents = contents;
  }

  public List<Command_content> getContents() {
    return contents;
  }

  public void setContents(List<Command_content> contents) {
    this.contents = contents;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
