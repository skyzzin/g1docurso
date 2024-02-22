package com.g1.curso.repositories;

import com.g1.curso.models.GlobalChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalChatRepository extends JpaRepository<GlobalChatModel, Long> {

}
