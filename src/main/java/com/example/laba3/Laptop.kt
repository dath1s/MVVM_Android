package com.example.laba3

class Laptop {
    var id : Int = 0
    var model : String = ""
    var ram : Int = 4
    var proc : String = "Pentium"

    constructor(model : String, ram : Int, proc : String){
        this.model = model
        this.ram = ram
        this.proc = proc
    }
}