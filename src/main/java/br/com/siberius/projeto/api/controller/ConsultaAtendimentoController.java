package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.AtendimentoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.AtendimentoInputModelDisassembler;
import br.com.siberius.projeto.api.model.AtendimentoModel;
import br.com.siberius.projeto.api.model.input.AtendimentoInputModel;
import br.com.siberius.projeto.api.openapi.controller.ConsultaAtendimentoControllerOpenApi;
import br.com.siberius.projeto.domain.model.Atendimento;
import br.com.siberius.projeto.domain.model.Consulta;
import br.com.siberius.projeto.domain.service.AtendimentoService;
import br.com.siberius.projeto.domain.service.ConsultaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/consultas/{consultaId}/atendimentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultaAtendimentoController implements ConsultaAtendimentoControllerOpenApi {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private AtendimentoModelAssembler assembler;

    @Autowired
    private AtendimentoInputModelDisassembler disassembler;

    @Override
    @PostMapping
    public AtendimentoModel adicionar(@PathVariable Long consultaId,
        @RequestBody @Valid AtendimentoInputModel atendimentoInputModel) {
        Consulta consulta = consultaService.buscarOuFalhar(consultaId);

        Atendimento atendimento = disassembler.toDomainObject(atendimentoInputModel);
        atendimento.setConsulta(consulta);

        return assembler.toModel(atendimentoService.salvar(atendimento));
    }

}
