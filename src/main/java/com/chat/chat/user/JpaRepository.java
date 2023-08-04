package com.chat.chat.user;

import org.springframework.data.repository.CrudRepository;

public interface JpaRepository extends CrudRepository<ChatUser, Long> {
}
