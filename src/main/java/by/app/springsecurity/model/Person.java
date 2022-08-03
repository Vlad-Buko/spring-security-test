package by.app.springsecurity.model;

import lombok.Data;
import lombok.Singular;

import javax.persistence.*;
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
// Убралии аннотацию
// Добавили @min
    @Min(value = 1920, message = "Значение не меньше 1920")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Год рождения не может быть пустым!")
    @Size(min = 1920, message = "Год рождения должен быть минимум 1900 года ")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;
}
