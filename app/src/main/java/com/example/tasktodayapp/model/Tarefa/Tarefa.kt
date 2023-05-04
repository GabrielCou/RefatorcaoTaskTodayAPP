package com.example.tasktodayapp.model.Tarefa

import java.util.Date

class Tarefa (val nome: String, val detalhes: String, val createDate: Date, val pzoFinal: Date) {

    var status = "pendente"
    get() {return field}
    set(value) {
        field = value
    }


}
