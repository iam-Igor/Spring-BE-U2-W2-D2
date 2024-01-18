package ygorgarofalo.SpringBeU2W2D2.config;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSender {

    private String apiKey;
    private String domainName;


    public MailSender(@Value("${mailgun.apikey}") String apiKey, @Value("${mailgun.domainname}") String domainName) {
        this.apiKey = apiKey;
        this.domainName = domainName;
    }


    public void sendMail() {
        HttpResponse<JsonNode> res = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "My company <igorgarofalo@gmail.com>")
                .queryString("to", "igorgarofalo@live.it")
                .queryString("subject", "Registrazione avvenuta!")
                .queryString("text", "Grazie per esserti registrato")
                .asJson();
        System.out.println("email inviata");
    }
}
