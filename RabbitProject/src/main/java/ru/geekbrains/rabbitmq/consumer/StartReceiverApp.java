package ru.geekbrains.rabbitmq.consumer;

public class StartReceiverApp {
    public static void main(String[] args) throws Exception {
        ReceiverApp receiverApp = new ReceiverApp();
        receiverApp.startApp();
    }
}
