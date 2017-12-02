package entity;

public class Micro_message {
  private Long id;
  private String command;//指令
  private String description;//描述
  private String content;//内容

  @Override
  public String toString() {
    return "Micro_message{" +
            "id=" + id +
            ", command='" + command + '\'' +
            ", description='" + description + '\'' +
            ", content='" + content + '\'' +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
