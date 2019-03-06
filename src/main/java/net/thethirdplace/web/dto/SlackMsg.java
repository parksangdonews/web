package net.thethirdplace.web.dto;

import lombok.Data;

@Data
public class SlackMsg {
    String text;

    public SlackMsg(String text) {
        this.text = text;
    }
}
