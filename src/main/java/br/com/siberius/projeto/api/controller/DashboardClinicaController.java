package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.openapi.controller.DashboardClinicaControllerOpenApi;
import br.com.siberius.projeto.domain.model.dto.EstatisticaSexo;
import br.com.siberius.projeto.domain.model.dto.EstatisticaStatus;
import br.com.siberius.projeto.domain.repository.*;
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
@RequestMapping(path = "/dashboard/clinica", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardClinicaController implements DashboardClinicaControllerOpenApi {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private StatusConsultaQueryService statusConsultaQueryService;

    @Autowired
    private SexoConsultaQueryService sexoConsultaQueryService;

    @Override
    @GetMapping("/countPacientesAtivo")
    public Long countPacientesAtivo(Long clinicaId){
        return pacienteRepository.countPacienteByAtivadoClinica(clinicaId);
    }

    @Override
    @GetMapping("/countProfissionaisAtivo")
    public Long countProfissionaisAtivo(Long clinicaId){
        return profissionalRepository.countProfissionaisByAtivadoClinica(clinicaId);
    }

    @Override
    @GetMapping("/countConsultasTotais")
    public Long countConsultasTotais(Long clinicaId){
        return consultaRepository.countConsultaTotaisClinica(clinicaId);
    }

    @Override
    @GetMapping("/countConsultasFinalizadas")
    public Long countConsultaByStatusConsultaEnumFinalizado(Long clinicaId){
        return consultaRepository.countConsultaByStatusConsultaEnumFinalizadoClinica(clinicaId);
    }

    @Override
    @GetMapping("/estatisticaStatus")
    public List<EstatisticaStatus> estatisticaStatus(EstatisticaStatusFilter filter,
                                                     @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        return statusConsultaQueryService.estatisticaStatus(filter, timeOffset);
    }

    @Override
    @GetMapping("/estatisticaSexo")
    public List<EstatisticaSexo> estatisticaSexo(EstatisticaSexoFilter filter, @RequestParam(required = false, defaultValue = "+00:00")  String timeOffset) {
        return sexoConsultaQueryService.estatisticaSexo(filter, timeOffset);
    }


}
