package com.nitish.springrestapi.services;

import com.nitish.springrestapi.api.v1.mapper.VendorMapper;
import com.nitish.springrestapi.api.v1.model.VendorDTO;
import com.nitish.springrestapi.api.v1.model.VendorListDTO;
import com.nitish.springrestapi.domain.Vendor;
import com.nitish.springrestapi.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper :: vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl("/api/v1/vendors/"+id);
                    return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO getAllVendors() {
         List<VendorDTO> vendors = vendorRepository.findAll()
                 .stream().map(vendor -> {
                     VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                     vendorDTO.setVendorUrl("/api/v1/vendors/"+vendor.getId());
                     return vendorDTO;
                 })
                 .collect(Collectors.toList());
         return new VendorListDTO(vendors);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }
                    return saveAndReturnDTO(vendor);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDto.setVendorUrl("/api/v1/vendors/"+savedVendor.getId());

        return returnDto;
    }
}
