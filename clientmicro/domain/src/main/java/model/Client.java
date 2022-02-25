package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long clientId;
    private String firstName;
    private String lastName;
    private String idType;
    private Long idNumber;
    private Integer age;
    private String cityOfBirth;
    private List<Image> images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!idType.equals(client.idType)) return false;
        return idNumber.equals(client.idNumber);
    }

    @Override
    public int hashCode() {
        int result = idType.hashCode();
        result = 31 * result + idNumber.hashCode();
        return result;
    }
}