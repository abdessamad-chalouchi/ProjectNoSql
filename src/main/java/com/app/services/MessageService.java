package com.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.entities.Client;
import com.app.entities.Message;
import com.app.repository.ClientRepository;
import com.app.repository.MessageRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;
    public List<Message> getMessages(Optional<String> id) {
        if(id.isPresent()){
            Optional<Client> c = clientRepository.findById(id.get());
            if(c.isEmpty()) return new ArrayList<Message>();
            Message msg = new Message();
            msg.setIdClient(c.get());
                return messageRepository.findAll(Example.of(msg));
        }
        return messageRepository.findAll();
    }
    public Page<Message> getMessages(Optional<String> id,Pageable p) {
        if(id.isPresent()){
            Optional<Client> c = clientRepository.findById(id.get());
            if(c.isEmpty()) return Page.empty(p);
            Message msg = new Message();
            msg.setIdClient(c.get());
                return messageRepository.findAll(Example.of(msg),p);
        }
        return messageRepository.findAll(p);
    }
    public Optional<Message> getMessage(String id) {
        return messageRepository.findById(id);
    }
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
    public boolean deleteMessage(Client c,String id) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent() && (c.isAdmin() || message.get().getIdClient().getId() == c.getId())){
            messageRepository.delete(message.get());
            return true;
        }
        return false;
    }
}
