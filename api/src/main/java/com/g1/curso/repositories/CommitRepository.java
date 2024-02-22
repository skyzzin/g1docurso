package com.g1.curso.repositories;

import com.g1.curso.models.CommitModel;
import com.g1.curso.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommitRepository extends JpaRepository<CommitModel, UUID> {

}
