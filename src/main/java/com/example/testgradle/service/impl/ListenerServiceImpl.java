package com.example.testgradle.service.impl;

import com.example.testgradle.data.entity.ListenerEntity;
import com.example.testgradle.data.repository.ListenerRepository;
import com.example.testgradle.service.ListenerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListenerServiceImpl implements ListenerService {

    private ListenerRepository listenerRepository;

    @Autowired
    public ListenerServiceImpl(ListenerRepository listenerRepository) {
        this.listenerRepository = listenerRepository;
    }

    @Override
    public ListenerEntity getEntity(Long id) {
        return listenerRepository.findById(id).get();
    }

    @Override
    public void saveEntity(ListenerEntity listenerEntity) {
        listenerRepository.save(listenerEntity);
    }

    @Override
    public void updateEntity(ListenerEntity listenerEntity) {
        ListenerEntity foundListener = listenerRepository.findById(listenerEntity.getId()).get();
        foundListener.setName(listenerEntity.getName());

        listenerRepository.save(foundListener);
    }

    @Override
    public void removeEntity(ListenerEntity listenerEntity) {
        listenerRepository.delete(listenerEntity);
    }
}
