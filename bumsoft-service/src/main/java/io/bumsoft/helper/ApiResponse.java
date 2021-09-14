package io.bumsoft.helper;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.response.ErrorResponse;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<D extends BumsoftDto> {

    public static <D> ResponseEntity ofCreate(Either<ErrorResponse, D> option) {
        return option.isRight() ?
                ResponseEntity.status(HttpStatus.CREATED).body(option.get()) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(option.getLeft());
    }

    public static <D> ResponseEntity ofRead(Either<ErrorResponse, D> option) {
        return option.isRight() ?
                ResponseEntity.status(HttpStatus.FOUND).body(option.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(option.getLeft());
    }

    public static <D> ResponseEntity ofUpdate(Either<ErrorResponse, D> option) {
        return option.isRight() ?
                ResponseEntity.status(HttpStatus.CREATED).body(option.get()) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(option.getLeft());
    }

    public static <D> ResponseEntity ofDelete(Either<ErrorResponse, D> option) {
        return option.isRight() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(option.get()) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(option.getLeft());
    }

    public static <D> ResponseEntity of(Either<ErrorResponse, D> option) {
        return option.isRight() ?
                ResponseEntity.ok(option.get()) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(option.getLeft());
    }

}
