package dto.response;

import java.time.LocalDateTime;
import java.util.Objects;

public class AboutStatisticDTOResponse {

    private final LocalDateTime creationTime;
    private final String message;

    public AboutStatisticDTOResponse(LocalDateTime creationTime, String message) {
        this.creationTime = creationTime;
        this.message = message;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AboutStatisticDTOResponse that = (AboutStatisticDTOResponse) o;
        return creationTime.equals(that.creationTime) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationTime, message);
    }

    @Override
    public String toString() {
        return "AboutStatisticDTOResponse{" +
                "creationTime=" + creationTime +
                ", message='" + message + '\'' +
                '}';
    }
}