package models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent implements Serializable {
    private static final long serialVersionUID = 1L;


    private String httpMethod;
    private String httpRoute;
    private int httpStatusCode;
    private String httpStatus;
    private long executionTimeMs;
    private String clientIp;
    private String userAgent;

}
