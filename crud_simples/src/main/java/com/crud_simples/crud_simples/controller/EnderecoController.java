package com.crud_simples.crud_simples.controller;

import com.crud_simples.crud_simples.dto.endereco.EnderecoAtualizacaoDTO;
import com.crud_simples.crud_simples.dto.endereco.EnderecoCriacaoDTO;
import com.crud_simples.crud_simples.dto.endereco.EnderecoResponseDTO;
import com.crud_simples.crud_simples.mapper.MapperEndereco;
import com.crud_simples.crud_simples.model.Endereco;
import com.crud_simples.crud_simples.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public EnderecoResponseDTO criar(@RequestBody @Valid EnderecoCriacaoDTO dto){
        Endereco endereco = enderecoService.criar(dto);
        return MapperEndereco.toResponse(endereco);
    }

    @GetMapping
    public List<EnderecoResponseDTO> listar(){
        return enderecoService.listar().stream()
                .map(MapperEndereco::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO buscar(@PathVariable Long id){
        Endereco endereco = enderecoService.buscar(id);
        return MapperEndereco.toResponse(endereco);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<EnderecoResponseDTO> buscarUsuario(@PathVariable Long id){
        try {
            Endereco endereco = enderecoService.buscarUsuario(id);
            return ResponseEntity.ok(MapperEndereco.toResponse(endereco));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public EnderecoResponseDTO atualizar(@PathVariable Long id,
                                         @RequestBody @Valid EnderecoAtualizacaoDTO dto){
        Endereco endereco = enderecoService.atualizar(id, dto);
        return MapperEndereco.toResponse(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
