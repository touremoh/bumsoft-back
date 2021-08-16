package io.bumsoft.helper;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.exception.BumsoftException;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<D extends BumsoftDto> {

    public static <D> ResponseEntity ofCreate(Either<BumsoftException, D> option) {
        return option.isRight() ?
                ResponseEntity.status(HttpStatus.CREATED).body(option.get()) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(option.getLeft().getMessage());
    }

    public static <D> ResponseEntity ofRead(Either<BumsoftException, D> option) {
        return option.isRight() ?
                ResponseEntity.status(HttpStatus.FOUND).body(option.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(option.getLeft().getMessage());
    }

    public static <D> ResponseEntity ofUpdate(Either<BumsoftException, D> option) {
        return option.isRight() ?
                ResponseEntity.status(HttpStatus.CREATED).body(option.get()) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(option.getLeft().getMessage());
    }

    public static <D> ResponseEntity ofDelete() {
        return ResponseEntity.noContent().build();
    }

    public static <D> ResponseEntity of(Either<BumsoftException, D> option) {
        return option.isRight() ?
                ResponseEntity.ok(option.get()) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(option.getLeft().getMessage());
    }

}
