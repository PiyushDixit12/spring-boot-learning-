package com.testings.JunitMockito.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Post(@Id int id, int userId, String title, String body) {

}