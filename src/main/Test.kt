package main

import java.util.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val sampleSize = 1000000
    val heap = Heap<Int>(Heap.Type.MIN)
    var arrayList = arrayListOf<Int>()

    println("Heap:")
    println("Adding $sampleSize ${measureTimeMillis {
        for (i in sampleSize downTo 0)
            heap.add(i)
    }.toSec()}s")

    println("Adding 1 more ${measureTimeMillis {
        heap.add(0)
    }.toSec()}s")

    println("Delete root ${measureTimeMillis {
        heap.poll()
    }.toSec()}s")

    println("Index of last value ${measureTimeMillis {
        heap.indexOf(sampleSize)
    }.toSec()}s")

    println("Index of value out of bounds ${measureTimeMillis {
        heap.indexOf(sampleSize + 1)
    }.toSec()}s")







    println("\nArrayList:")
    println("Adding $sampleSize ${measureTimeMillis {
        for (i in 0..sampleSize)
            arrayList.add(i)
    }.toSec()}s")

    println("Adding 1 more ${measureTimeMillis {
            arrayList.add(0)
    }.toSec()}s")

    println("Delete root ${measureTimeMillis {
        arrayList.remove(0)
    }.toSec()}s")

    println("Index of last value ${measureTimeMillis {
        arrayList.indexOf(sampleSize)
    }.toSec()}s")

    println("Index of value out of bounds ${measureTimeMillis {
        arrayList.indexOf(sampleSize + 1)
    }.toSec()}s")

}

fun Long.toSec(): Double = this.toDouble() / 1000