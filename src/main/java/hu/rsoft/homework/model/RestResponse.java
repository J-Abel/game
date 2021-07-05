package hu.rsoft.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
public class RestResponse {

    @Enumerated(EnumType.STRING)
    private Result result;

    private Long playingTime;

    public static RestResponse greater() {
        return new RestResponse(Result.GREATER, null);
    }

    public static RestResponse lower() {
        return new RestResponse(Result.LOWER, null);
    }

    public static RestResponse equal(Long playingTime) {
        return new RestResponse(Result.EQUAL, playingTime);
    }

    public static RestResponse over() {
        return new RestResponse(Result.OVER, null);
    }
}
