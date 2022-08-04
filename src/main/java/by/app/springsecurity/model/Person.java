package by.app.springsecurity.model;

import lombok.Data;
import lombok.Singular;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by Vladislav Domaniewski
 *
 * Создаём модель пользователя, и связываем с существующей Базой Данных
 */

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым!")
    @Size(min = 3, max = 77, message = "Имя должно быть максимум 77 символов!")
    @Column(name = "username")
    private String username;

    @Min(value = 1900, message = "Birth can't be low 1900!")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
