package com.yorix.springpersistence.students;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @NonNull
    private Integer id;
    @NonNull
    private String name;

    public Student(@NonNull String name) {
        this.name = name;
    }
}
