package ar.com.carloscurotto.snmp.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SNMPReceiverExample example = new SNMPReceiverExample();
        example.run();
    }

}