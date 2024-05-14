package com.josejavier.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ClientDTO {

    private int id;


    private byte[] photo; // Cambia el tipo de dato de la propiedad photo a byte[]


    private String name;


    private String username;


    private String mail;


    private String password;


    private LocalDate date;


    private String phone;

    private String token;
}
