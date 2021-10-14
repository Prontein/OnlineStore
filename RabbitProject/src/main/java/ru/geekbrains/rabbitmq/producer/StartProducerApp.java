package ru.geekbrains.rabbitmq.producer;

public class StartProducerApp {
    public static void main(String[] args) throws Exception {
        MultiDirectSenderApp multiDirectSenderApp = new MultiDirectSenderApp();
        multiDirectSenderApp.startApp();
    }
}
