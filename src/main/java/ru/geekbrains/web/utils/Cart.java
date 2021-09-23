package ru.geekbrains.web.utils;

import lombok.Data;
import ru.geekbrains.web.dtos.OrderItemDTO;
import ru.geekbrains.web.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDTO> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public boolean add(Long productId) {
        for (OrderItemDTO i : items) {
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        items.add(new OrderItemDTO(product));
        recalculate();
    }

    public void decrement (Long productId) {
        Iterator<OrderItemDTO> iter = items.iterator();
        while (iter.hasNext()) {
        OrderItemDTO i = iter.next();
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove (Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDTO i : items) {
            totalPrice += i.getPrice();
        }
    }

    public void merge(Cart another) {
        for (OrderItemDTO anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDTO myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
