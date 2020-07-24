package com.example.movies.service.mapper;

import com.example.movies.domain.Director;
import com.example.movies.service.dto.DirectorDTO;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper implements EntityMapper<Director, DirectorDTO> {

    @Override
    public Director toEntity(DirectorDTO directorDTO) {
        Director director = new Director();
        director.setFirstName(directorDTO.getFirstName());
        director.setLastName(directorDTO.getLastName());
        return director;
    }

    @Override
    public DirectorDTO toDto(Director director) {
        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setFirstName(director.getFirstName());
        directorDTO.setLastName(director.getLastName());
        return directorDTO;
    }
}
