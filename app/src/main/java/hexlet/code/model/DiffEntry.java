package hexlet.code.model;

public record DiffEntry(String key, Object oldValue, Object newValue, DiffStatus status) {
}
