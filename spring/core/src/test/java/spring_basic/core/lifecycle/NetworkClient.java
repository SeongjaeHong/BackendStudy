package spring_basic.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("Call the constructor, url = " + url);
        connect();
    }

    public void setUrl(String url) {
        this.url = url;
        System.out.println("Setting url");
    }

    public void connect() {
        System.out.println("Connet: " + url);
    }

    public void call(String msg) {
        System.out.println("Call: " + url + " msg = " +msg);
    }

    public void disconnet() {
        System.out.println("Disconnect: " + url);
    }

    @PostConstruct
    public void init() {
        connect();
        call("Initialization connection message");
    }

    @PreDestroy
    public void close() {
        disconnet();
    }
}
