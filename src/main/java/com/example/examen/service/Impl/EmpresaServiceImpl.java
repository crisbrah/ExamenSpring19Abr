package com.example.examen.service.Impl;

import com.example.examen.Constants.Constants;
import com.example.examen.dao.EmpresaRepository;
import com.example.examen.entity.EmpresaEntity;
import com.example.examen.service.EmpresaService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor

public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;


    @Override
    public EmpresaEntity crear(EmpresaEntity empresaEntity) {
        EmpresaEntity empresa =new EmpresaEntity();
        empresa.setRazonSocial(empresaEntity.getRazonSocial());
        empresa.setTipoDocumento(empresaEntity.getTipoDocumento());
        empresa.setNumDocumento(empresaEntity.getNumDocumento());
        empresa.setCondicion(empresaEntity.getCondicion());
        empresa.setDireccion(empresaEntity.getDireccion());
        empresa.setDistrito(empresaEntity.getDistrito());
        empresa.setProvincia(empresaEntity.getProvincia());
        empresa.setDepartamento(empresaEntity.getDepartamento());
        empresa.setEsAgenteRetencion(empresaEntity.getEsAgenteRetencion());
        empresa.setEstado(Constants.STATUS_ACTIVE);
        empresa.setUsuaCrea(Constants.USU_ADMIN);
        empresa.setDateCreate(getTimestamp());
        return empresaRepository.save(empresa);
    }

    @Override
    public Optional<EmpresaEntity> buscarxId(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public List<EmpresaEntity> buscarAll() {
        return empresaRepository.findAll();
    }


    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }


    @Override

     public EmpresaEntity actualizar(Long id, EmpresaEntity empresaEntity) {
         Optional<EmpresaEntity> empresaRecuperada = empresaRepository.findById(id);
         if(empresaRecuperada.isPresent()){
             EmpresaEntity empresa=empresaRecuperada.get();
             empresa.setRazonSocial(empresaEntity.getRazonSocial());
             empresa.setTipoDocumento(empresaEntity.getTipoDocumento());
             empresa.setNumDocumento(empresaEntity.getNumDocumento());
             empresa.setCondicion(empresaEntity.getCondicion());
             empresa.setDireccion(empresaEntity.getDireccion());
             empresa.setDistrito(empresaEntity.getDistrito());
             empresa.setProvincia(empresaEntity.getProvincia());
             empresa.setDepartamento(empresaEntity.getDepartamento());
             empresa.setEsAgenteRetencion(empresaEntity.getEsAgenteRetencion());
             empresa.setEstado(Constants.STATUS_ACTIVE);
             empresa.setUsuaModif(Constants.USU_ADMIN);
             empresa.setDateModif(getTimestamp());
             return empresaRepository.save(empresa);
         }
         return null;
     }


    @Override
    public EmpresaEntity borrar(Long id) {
        Optional<EmpresaEntity> empresaRecuperada= empresaRepository.findById(id);
        if(empresaRecuperada.isPresent()){
            empresaRecuperada.get().setEstado(0);
            empresaRecuperada.get().setDateDelet(getTimestamp());
            empresaRecuperada.get().setUsuaDelet(Constants.USU_ADMIN);
            return empresaRepository.save(empresaRecuperada.get());
        }
        return null;
    }


}
