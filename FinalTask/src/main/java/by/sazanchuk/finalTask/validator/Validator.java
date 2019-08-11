package by.sazanchuk.finalTask.validator;

public interface Validator<T> {
    String isValid(T entity);
}
