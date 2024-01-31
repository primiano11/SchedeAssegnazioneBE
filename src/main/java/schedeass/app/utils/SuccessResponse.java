package schedeass.app.utils;

import lombok.Data;

/**
 * Class used to manage the responses of the APIs.
 */

@Data
public class SuccessResponse<T> {
    private T result;
    private String message;

    /**
     * Method to assign the result object to the response
     *
     * @param result object of response to be returned
     */

    public SuccessResponse(T result) {
        this.result = result;
        this.message = "SUCCESS";
    }
}
