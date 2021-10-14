package ru.geekbrains.rabbitmq.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;

public class MultiDirectSenderApp {
    private static final String EXCHANGE_NAME = "MultiDirect";
    public final Scanner SCANNER = new Scanner(System.in);

    public void startApp() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            publishNews(channel);
        }
    }

    private void publishNews(Channel channel) throws IOException {
        try (SCANNER) {
            System.out.println("Выберите действие:" +
                    "\n 1. Новости на тему PHP: php some message" +
                    "\n 2. Новости на тему C++: c++ some message" +
                    "\n 3. Новости на тему Java: java some message" +
                    "\n 4. Выход: exit");
            while (true) {

//                int action = SCANNER.nextInt();
                String action = SCANNER.nextLine();
                if (action.equals("php some message")) {
                    sendNewsPHP(channel);
                }

                if (action.equals("c++ some message")) {
                    sendNewsCPlus(channel);
                }

                if (action.equals("java some message")) {
                    sendNewsJava(channel);
                }

                if (action.equals("exit")) {
                    SCANNER.close();
                    return;
                }

//                switch (action) {
//                    case 1 -> {             // PHP News
//                        sendNewsPHP(channel);
//                    }
//
//                    case 2 -> {             // C++ News
//                        sendNewsCPlus(channel);
//                    }
//
//                    case 3 -> {             // Java News
//                        sendNewsJava(channel);
//                    }
//
//                    case 4 -> {             //Закрыть приложение
//                        SCANNER.close();
//                        return;
//                    }
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendNewsPHP(Channel channel) throws IOException {
        System.out.println("Добавьте новость по теме PHP:");
            String newsPHP = SCANNER.next();
            channel.basicPublish(EXCHANGE_NAME, "php", null, ("IT-блог, который публикует статьи на языке программирования PHP. " +
                    newsPHP).getBytes("UTF-8"));
        System.out.println("Сообщение отправлено. Выберите действие:");
    }

    private void sendNewsCPlus(Channel channel) throws IOException {
        System.out.println("Добавьте новость по теме C++:");
        String newsPHP = SCANNER.next();
        channel.basicPublish(EXCHANGE_NAME, "c++", null, ("IT-блог, который публикует статьи на языке программирования C++. " +
                newsPHP).getBytes("UTF-8"));
        System.out.println("Сообщение отправлено. Выберите действие:");
    }

    private void sendNewsJava(Channel channel) throws IOException {
        System.out.println("Добавьте новость по теме Java:");
        String newsPHP = SCANNER.next();
        channel.basicPublish(EXCHANGE_NAME, "java", null, ("IT-блог, который публикует статьи на языке программирования Java. " +
                newsPHP).getBytes("UTF-8"));
        System.out.println("Сообщение отправлено. Выберите действие:");
    }
}
