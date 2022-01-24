public class Card {
    private int value;
    private String status;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Card(String status, int value) {
        this.value = value;
        this.status = status;
    }
}
