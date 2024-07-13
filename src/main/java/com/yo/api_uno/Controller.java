package com.yo.api_uno;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final Repository repositorio;

    public Controller(final Repository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Model>> devolverTodo() {
        List<Model> lista = repositorio.findAll();
        for (Model model : lista) {
            System.out.println(model.getId());
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /*Duda de que se supone que un cliente va a especificar ese id? un poco loco no? */

    @GetMapping("/{id}")
    public ResponseEntity<Model> devolverUno(@PathVariable String id) {
        Optional<Model> optionalModel = repositorio.findById(id);
        if (!optionalModel.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Model model = optionalModel.get();
            return new ResponseEntity<>(model, HttpStatus.OK);
        }

    }

    @PostMapping("/crear")
    public ResponseEntity<Model> crearRegistro(@RequestBody Model model) {
        repositorio.save(model);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Model> eliminarUno(@PathVariable String id) {
        Optional<Model> optionalModel = repositorio.findById(id);
        if (!optionalModel.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            repositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/borrar-todo")
    public ResponseEntity<String> borrarTodo() {
        repositorio.deleteAll();
        return new ResponseEntity<>("Todo borrado", HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Model> actualizarUno(@PathVariable String id, @RequestBody Model model){
        Optional<Model> optionalModel = repositorio.findById(id);
        if(optionalModel.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            Model modelActualizado = optionalModel.get();
            repositorio.save(modelActualizado);
            return new ResponseEntity<>(modelActualizado, HttpStatus.OK);
        }
    }

}
