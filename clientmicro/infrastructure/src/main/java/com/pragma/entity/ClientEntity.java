package com.pragma.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @Column(name = "clientId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private Long clientId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "idType", nullable = false)
    private String idType;

    @Column(name = "idNumber", nullable = false)
    private Long idNumber;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "cityOfBirth", nullable = false)
    private String cityOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity client = (ClientEntity) o;

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
