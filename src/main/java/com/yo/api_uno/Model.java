package com.yo.api_uno;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//También llamado Entity

@Document(collection = "base")
public class Model {

    @Id
    private String id;
    private String nombre;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
