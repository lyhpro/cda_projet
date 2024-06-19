package com.cdaprojet.gestion_personnel.service.contractType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.contractType.ContractType;
import com.cdaprojet.gestion_personnel.repository.ContractTypeRepository;

@Service
public class ContractTypeServiceImpl implements ContractTypeService {
    
    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Override
    public List<ContractType> getAllContractType() {
        return contractTypeRepository.findAll().stream().filter(contractType -> contractType.isEnabled()).toList();
    }
    
}
