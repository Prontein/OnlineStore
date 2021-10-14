package ru.geekbrains.rabbitmq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReceiverApp {
    private static final String EXCHANGE_NAME = "MultiDirect";
    public final Scanner SCANNER = new Scanner(System.in);

    public void startApp() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

        receiveNews(channel, queueName);

        System.out.println(" [*] Waiting for messages");
    }

    private void receiveNews(Channel channel, String queueName) throws IOException {
        List<String> topicList = new ArrayList<>();
        try (SCANNER) {
            System.out.println("Выберите действие:" +
                    "\n 1. Посмотреть свои подписки на новости: show_topic list" +
                    "\n 2. Добавить подписку: set_topic" +
                    "\n 3. Удалить подписку: delete_topic" +
                    "\n 4. Выход: exit");
            while (true) {
                String action = SCANNER.nextLine();
                if (action.equals("show_topic list")) {
                    showMyTopics(topicList);
                }

                if (action.equals("set_topic")) {
                    setTopicNews(channel,queueName,topicList);
                }

                if (action.equals("delete_topic")) {
                    deleteTopicNews(channel,queueName,topicList);
                }

                if (action.equals("exit")) {
                    SCANNER.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMyTopics(List<String> topicList) {
        if (topicList.isEmpty()) {
            System.out.println("У вас нет подписок!");
            return;
        }
        System.out.println("Список подписок:");
        for (String topic : topicList) {
            System.out.println("Вы подписаны на : " + topic);
        }
        System.out.println("Выберите действие:");
    }

    private void setTopicNews(Channel channel, String queueName, List<String> topicList) throws IOException {
        System.out.println("Выберите тему: php , c++, java");
        String setTopic = SCANNER.next();
        switch (setTopic) {
            case "php" -> {
                channel.queueBind(queueName, EXCHANGE_NAME, "php");
                topicList.add("php");
                System.out.println("Вы подписались на PHP");
            }

            case "c++" -> {
                channel.queueBind(queueName, EXCHANGE_NAME, "c++");
                topicList.add("c++");
                System.out.println("Вы подписались на C++");
            }

            case "java" -> {
                channel.queueBind(queueName, EXCHANGE_NAME, "java");
                topicList.add("java");
                System.out.println("Вы подписались на Java");
            }
        }
        System.out.println("Выберите действие:");
    }

    private void deleteTopicNews(Channel channel, String queueName, List<String> topicList) throws IOException {
        System.out.println("Выберите тему которая вам не интересна: php , c++, java");
        String setTopic = SCANNER.next();
        switch (setTopic) {
            case "php" -> {
                channel.queueUnbind(queueName, EXCHANGE_NAME, "php");
                topicList.remove("php");
                System.out.println("Вы отписались от PHP");
            }

            case "c++" -> {
                channel.queueUnbind(queueName, EXCHANGE_NAME, "c++");
                topicList.remove("c++");
                System.out.println("Вы отписались от C++");
            }

            case "java" -> {
                channel.queueUnbind(queueName, EXCHANGE_NAME, "java");
                topicList.remove("java");
                System.out.println("Вы отписались от Java");
            }
        }
        System.out.println("Выберите действие:");
    }
}
