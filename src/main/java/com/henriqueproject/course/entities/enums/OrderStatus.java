package com.henriqueproject.course.entities.enums;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELLED(5);

    private int code;

    private OrderStatus(int code) {
        this.code = code;

    } // é necessário fazer um cconstrutor private para eesse tipo enumerado

    // e para torna-lo publico precisa colocar o get

    public int getCode() {
        return code;
    }

    // Vamos criar agora um métdo estático para converter o valor número em tipo enumerado
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) // vai percorrer todos valores do OrderStatus
            if (value.getCode() == code) { // e verifica se o valor do código é igual
                // o código do nome do enumerado, ele vai retornar o nome do enumerado
                return value;
            }
                throw new IllegalArgumentException(code + " Is not a valid code, please try againt");
             // essa exceção é para caso o cliente ou programador digite um valor invalido

    }
}

