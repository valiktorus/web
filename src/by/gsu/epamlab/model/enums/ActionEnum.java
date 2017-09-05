package by.gsu.epamlab.model.enums;

public enum ActionEnum {
    ACTUAL, DELETED, FIXED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
