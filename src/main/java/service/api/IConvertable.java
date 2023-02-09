package service.api;

@FunctionalInterface
public interface IConvertable <T, R> {

    R convert(T t);
}