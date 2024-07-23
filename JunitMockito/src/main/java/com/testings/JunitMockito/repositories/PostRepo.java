package com.testings.JunitMockito.repositories;

import com.testings.JunitMockito.entities.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepo extends ListCrudRepository<Post,Integer> {
}
