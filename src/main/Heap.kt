package main

import java.util.*
import kotlin.test.todo

class Heap<T : Comparable<T>>(val type: Type = Type.MIN): Iterable<T> {
    enum class Type {
        MIN, MAX
    }

    fun add(value: T) {
        heap.add(value)
        siftUp(heap.lastIndex)
    }

    fun get(index: Int): T = heap[index]
    fun all() = heap.toArray()
    fun peek(): T = heap[0]
    fun poll(): T = when {
        heap.size == 0 -> throw Exception("No elements")
        heap.size == 1 -> heap.removeAt(0)
        else -> {
            val buffer =  heap.get(0)
            heap.set(0,heap.removeAt(heap.lastIndex))
            siftDown(0)
            buffer
        }
    }


    fun clear(value: T) = heap.clear()
    fun addAll(values: Collection<T>) {
        todo {
            for (value in values)
                add(value)
        }
    }
    fun hasNext() = iterator().hasNext()
    fun indexOf(value: T): Int {
        for (index in 0 until size) {
            if (value.heapCompare2(get(index))) return -1
            if (get(index) == value) return index
        }
        return -1
    }

    val first get() = heap.first()
    val last get() = heap.last()
    val isEmpty get() = heap.isEmpty()
    val size get() = heap.size

    private var heap: ArrayList<T> = ArrayList()

    private fun getParent(index: Int) = heap[parent(index)]
    private fun getLeft(index: Int) = heap[left(index)]
    private fun getRight(index: Int) = heap[right(index)]
    private fun parent(index: Int) = (index - 1) / 2
    private fun left(index: Int) = (index * 2) + 1
    private fun right(index: Int) = (index * 2) + 2

    private fun swap(a: Int, b: Int) {
        heap[a] = heap[b].also {
            heap[b] = heap[a] }
    }

    private tailrec fun siftUp(index: Int) {
        if (index > 0) {
            if (get(index) heapCompare getParent(index)) {
                swap(index, parent(index))
                siftUp(parent(index))
            }
        }
    }

    private tailrec fun siftDown(index: Int) {
        if (left(index) >= heap.size) return
        val next = when {
            right(index) >= heap.size -> left(index)
            getLeft(index) heapCompare getRight(index) -> left(index)
            else -> right(index)
        }

        if (get(next) heapCompare get(index)) {
            swap(index,next)
            siftDown(next)
        }
    }

    private infix fun T.heapCompare(other: T): Boolean = when (type) {
            Type.MIN -> this < other
            Type.MAX -> this > other
    }
    private fun T.heapCompare2(other: T): Boolean {
        return if (type == Type.MIN) this < other
            else this > other
    }

    override fun iterator(): Iterator<T> = heap.iterator()
    override fun toString() = Arrays.toString(all())
}

