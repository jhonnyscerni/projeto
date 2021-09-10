package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.openapi.controller.DashboardProfissionalControllerOpenApi;
import br.com.siberius.projeto.core.security.resourceserver.CheckSecurity;
import br.com.siberius.projeto.domain.model.dto.EstatisticaSexo;
import br.com.siberius.projeto.domain.model.dto.EstatisticaStatus;
import br.com.siberius.projeto.domain.repository.ConsultaRepository;
import br.com.siberius.projeto.domain.repository.PacienteRepository;
import br.com.siberius.projeto.domain.repository.SexoConsultaQueryService;
import br.com.siberius.projeto.domain.repository.StatusConsultaQueryService;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaSexoFilter;
import br.com.siberius.projeto.infrastructure.service.filter.EstatisticaStatusFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/dashboard/profissional", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardProfissionalController implements DashboardProfissionalControllerOpenApi {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private StatusConsultaQueryService statusConsultaQueryService;

    @Autowired
    private SexoConsultaQueryService sexoConsultaQueryService;

    @CheckSecurity.DashboardProfissionais.PodeConsultarDashboardProfissional
    @Override
    @GetMapping("/countConsultasFinalizadas")
    public Long countConsultaByStatusConsultaEnumFinalizado(Long profissionalId){
        return consultaRepository.countConsultaByStatusConsultaEnumFinalizado(profissionalId);
    }

    @CheckSecurity.DashboardProfissionais.PodeConsultarDashboardProfissional
    @Override
    @GetMapping("/countConsultasConfirmadas")
    public Long countConsultaByStatusConsultaEnumConfirmada(Long profissionalId){
        return consultaRepository.countConsultaByStatusConsultaEnumConfirmado(profissionalId);
    }

    @CheckSecurity.DashboardProfissionais.PodeConsultarDashboardProfissional
    @Override
    @GetMapping("/countConsultasAgendadas")
    public Long countConsultaByStatusConsultaEnumAgendada(Long profissionalId){
        return consultaRepository.countConsultaByStatusConsultaEnumAgendado(profissionalId);
    }

    @CheckSecurity.DashboardProfissionais.PodeConsultarDashboardProfissional
    @Override
    @GetMapping("/countConsultasCanceladas")
    public Long countConsultaByStatusConsultaEnumCanceladas(Long profissionalId){
        return consultaRepository.countConsultaByStatusConsultaEnumCancelado(profissionalId);
    }

    @CheckSecurity.DashboardProfissionais.PodeConsultarDashboardProfissional
    @Override
    @GetMapping("/countPacientesCadastradosAtivado")
    public Long countPacienteCadastradoAtivado(Long profissionalId){
        return pacienteRepository.countPacienteByAtivado(profissionalId);
    }

    @CheckSecurity.DashboardProfissionais.PodeConsultarDashboardProfissional
    @Override
    @GetMapping("/estatisticaStatus")
    public List<EstatisticaStatus> estatisticaStatus(EstatisticaStatusFilter filter,
                                                     @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        return statusConsultaQueryService.estatisticaStatus(filter, timeOffset);
    }

    @CheckSecurity.DashboardProfissionais.PodeConsultarDashboardProfissional
    @Override
    @GetMapping("/estatisticaSexo")
    public List<EstatisticaSexo> estatisticaSexo(EstatisticaSexoFilter filter, @RequestParam(required = false, defaultValue = "+00:00")  String timeOffset) {
        return sexoConsultaQueryService.estatisticaSexo(filter, timeOffset);
    }


}
