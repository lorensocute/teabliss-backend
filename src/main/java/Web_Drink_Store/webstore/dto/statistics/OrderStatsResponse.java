package Web_Drink_Store.webstore.dto.statistics;

public class OrderStatsResponse {

    private long totalOrders;
    private long pending;
    private long confirmed;
    private long shipping;
    private long completed;
    private long cancelled;

    public OrderStatsResponse(
            long totalOrders,
            long pending,
            long confirmed,
            long shipping,
            long completed,
            long cancelled
    ) {
        this.totalOrders = totalOrders;
        this.pending = pending;
        this.confirmed = confirmed;
        this.shipping = shipping;
        this.completed = completed;
        this.cancelled = cancelled;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public long getPending() {
        return pending;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public long getShipping() {
        return shipping;
    }

    public long getCompleted() {
        return completed;
    }

    public long getCancelled() {
        return cancelled;
    }
}