package com.g1.curso.controllers;

import com.g1.curso.dtos.CommitDTO;
import com.g1.curso.models.CommitModel;
import com.g1.curso.models.PostModel;
import com.g1.curso.repositories.CommitRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@CrossOrigin("${spring.url}")

public class CommitController {
    @Autowired
    CommitRepository commitRepository;
    @PostMapping("/commit/create")
    public ResponseEntity<CommitModel> createCommit(@RequestBody CommitDTO commitDTO){
        CommitModel commit = new CommitModel();
        BeanUtils.copyProperties(commitDTO,commit);
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        commit.setCreatedat(formattedDateTime);

        try {
            commitRepository.save(commit);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(commit);
    }
    @GetMapping("/commit/list")
    public ResponseEntity<List<CommitModel>> findAllCommits(){

        List<CommitModel> commits = commitRepository.findAll(Sort.by(Sort.Direction.DESC, "createdat"));

        return ResponseEntity.ok(commits);
    }
}
