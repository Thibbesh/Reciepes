package nl.abnamro.com.recipesweb.controller;

import nl.abnamro.com.recipesweb.exception.RecipeNotFoundException;
import nl.abnamro.com.recipesweb.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestControllerAdvice is global exception handler for recipe web services.
 */
@RestControllerAdvice
public class RecipeExceptionHandler {

    /**
     * exceptionHandler is take care of exception thrown by services or controller.
     * @param ex RecipeNotFoundException
     * @return errorResponse
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(RecipeNotFoundException ex) {
        ErrorResponse response =
                new ErrorResponse("Error_CODE-0001",
                        "No recipe found with Id " + ex.getId());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
