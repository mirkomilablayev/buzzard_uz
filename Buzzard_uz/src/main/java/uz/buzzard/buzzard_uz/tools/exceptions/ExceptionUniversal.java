package uz.buzzard.buzzard_uz.tools.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionUniversal extends Throwable {
    public ExceptionUniversal(String error, HttpStatus conflict) {
    }
}
