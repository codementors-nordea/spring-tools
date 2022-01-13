package pl.codementors.validation.exceptions;

import lombok.Value;

@Value
public class ApplicationError {
    String errorCode;
    Integer statusCode;
    String explanation;
}
