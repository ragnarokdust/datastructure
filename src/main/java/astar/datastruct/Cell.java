package astar.datastruct;

public class Cell {
    private Status status;

    public static enum Status {
        START, END, BARRIER, EMPTY;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}