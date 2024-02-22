package com.g1.curso.services;

import com.g1.curso.models.GlobalChatModel;
import com.g1.curso.repositories.GlobalChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMessageDB {

    @Autowired
    GlobalChatRepository globalChatRepository;
    public GlobalChatModel save(GlobalChatModel msg){
        return globalChatRepository.save(msg);
    }
}
