package com.pragma.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String idType;
    private Long idNumber;
    private Integer age;
    private String cityOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDto client = (ClientDto) o;

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
