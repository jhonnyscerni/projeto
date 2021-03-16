package br.com.siberius.projeto.api.controller;

import br.com.siberius.projeto.api.assembler.FormaPagamentoModelAssembler;
import br.com.siberius.projeto.api.assembler.disassembler.FormaPagamentoModelDisassembler;
import br.com.siberius.projeto.api.model.FormaPagamentoModel;
import br.com.siberius.projeto.api.model.input.FormaPagamentoInputModel;
import br.com.siberius.projeto.api.openapi.controller.FormaPagamentoControllerOpenApi;
import br.com.siberius.projeto.domain.model.FormaPagamento;
import br.com.siberius.projeto.domain.repository.FormaPagamentoRepository;
import br.com.siberius.projeto.domain.service.FormaPagamentoService;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@RestController
@RequestMapping(value = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler assembler;

    @Autowired
    private FormaPagamentoModelDisassembler disassembler;


    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<FormaPagamentoModel> formasPagamentosDTO = assembler.toCollectionModel(formaPagamentoRepository.findAll());

        return ResponseEntity.ok()
//				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//				.cacheControl(CacheControl.noCache())
//              .cacheControl(CacheControl.noStore())
            .eTag(eTag)
            .body(formasPagamentosDTO);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataAtualizacao = formaPagamentoRepository
            .getDataAtualizacaoById(formaPagamentoId);

        if (dataAtualizacao != null) {
            eTag = String.valueOf(dataAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamentoModel formaPagamentoDTO = assembler.toModel(formaPagamentoService.buscarOuFalhar(formaPagamentoId));

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .eTag(eTag)
            .body(formaPagamentoDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInputModel formaPagamentoInputModel) {
        FormaPagamento formaPagamento =
            formaPagamentoService.salvar(disassembler.toDomainObject(formaPagamentoInputModel));

        return assembler.toModel(formaPagamento);
    }

    //CADASTRAR FORMA DE PAGAMENTO E ADICIONAR ESSA FORMA DE PAGAMENTO A TODOS OS RESTAURANTES

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) {
//        FormaPagamento formaPagamento = disassembler.getFormaPagamentoObject(formaPagamentoInput);
//
//        formaPagamento = formaPagamentoService.salvar(formaPagamento);
//
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//
//        for(Restaurante restaurante : restaurantes) {
//            restauranteService.associarFormaPagamento(restaurante.getId(), formaPagamento.getId());
//        }
//
//        return assembler.getFormaPagamentoDTO(formaPagamento);
//    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
        @RequestBody @Valid FormaPagamentoInputModel formaPagamentoInputModel) {

        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        disassembler.copyToDomainObject(formaPagamentoInputModel, formaPagamento);

        return assembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }

}