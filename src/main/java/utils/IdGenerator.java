package utils;

public class IdGenerator {

    private static Long userId = 1L;
    private static Long productId = 1L;
    private static Long orderId = 1L;
    private static Long basketId = 1L;

    public static Long getBasketId() {
        return basketId++;
    }

    public static Long getOrderId() {
        return orderId++;
    }

    public static Long getUserId() {
        return userId++;
    }

    public static Long getProductId() {
        return productId++;
    }
}
