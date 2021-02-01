package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.openapi.controller.DashboardProfissionalControllerOpenApi;
import br.com.siberius.projeto.domain.repository.ConsultaRepository;
import br.com.siberius.projeto.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dashboard/profissional", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardProfissionalController implements DashboardProfissionalControllerOpenApi {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @GetMapping("/countConsultasFinalizadas")
    public Long countConsultaByStatusConsultaEnumFinalizado(Long profissionalId){
        return consultaRepository.countConsultaByStatusConsultaEnumFinalizado(profissionalId);
    }

    @Override
    @GetMapping("/countConsultasConfirmadas")
    public Long countConsultaByStatusConsultaEnumConfirmada(Long profissionalId){
        return consultaRepository.countConsultaByStatusConsultaEnumConfirmado(profissionalId);
    }

    @Override
    @GetMapping("/countConsultasAgendadas")
    public Long countConsultaByStatusConsultaEnumAgendada(Long profissionalId){
        return consultaRepository.countConsultaByStatusConsultaEnumAgendado(profissionalId);
    }

    @Override
    @GetMapping("/countPacientesCadastradosAtivado")
    public Long countPacienteCadastradoAtivado(Long profissionalId){
        return pacienteRepository.countPacienteByAtivado(profissionalId);
    }
}
