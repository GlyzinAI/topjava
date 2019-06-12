package ru.javawebinar.topjava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Meal " + uuid + " not exist", uuid);
    }
}
