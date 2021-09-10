package br.com.siberius.projeto.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper ModelMapper() {
        /**
         * Customizando o Mapeamento de propriedade com ModelMapper
         *
         * modelMapper.createTypeMap(Restaurante.class , RestauranteDTO.class)
         *         .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
         */

        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration()
//            .setFieldMatchingEnabled(true)
//            .setFieldAccessLevel(AccessLevel.PRIVATE)
//            .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }
}

