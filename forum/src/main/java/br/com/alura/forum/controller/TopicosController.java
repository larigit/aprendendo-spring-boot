package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //DTO: mandando dados da api p cliente
    @GetMapping
    public List<TopicoDto> lista(String cursoNome){
        List<Topico> topicos;
        if(cursoNome == null) {
            topicos = topicoRepository.findAll();
        }else{
            topicos = topicoRepository.findByCurso_Nome(cursoNome);
        }
         return TopicoDto.converter(topicos);
    }

    //FORM: chegando do cliente p api
    @PostMapping //sucesso retorna 201 created
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm form){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        return ResponseEntity.created(uri);
    }

}
