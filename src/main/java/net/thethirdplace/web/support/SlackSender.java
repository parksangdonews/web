package net.thethirdplace.web.support;

import net.thethirdplace.web.dto.SlackMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackSender {

    @Autowired
    RestTemplate restTemplate;
    @Value("${slack.url}")
    String url;

    public void send(String message) {
        SlackMsg msg = new SlackMsg(message);
        restTemplate.postForEntity(url, msg, String.class);
    }

}
