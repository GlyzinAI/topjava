package ru.javawebinar.topjava.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Meal " + uuid + " already exist", uuid);
    }
}
