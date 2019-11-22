package com.jy


/*
 * created by Cherry on 2019-11-21
**/

//internal public private  protected;


class Observable  {

    var state : Int = 0
    set(value) {
        field = value
        notifyAllObserver()
    }

    private var observers : MutableList<Observer> = mutableListOf()



    fun addObserver(observer: Observer){
        observers.add(observer)
    }


   private fun notifyAllObserver(){
        for(observer in observers){
           observer.update()
        }
    }


}


fun main(){
    val observable  = Observable()
    observable.addObserver(object : Observer {
        override fun update() {
            println("Observable state update  = ${observable.state}" )
        }
    })
    observable.state = 2;
}


interface Observer {
    fun update()
}


