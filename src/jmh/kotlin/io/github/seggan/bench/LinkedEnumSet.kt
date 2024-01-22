package io.github.seggan.bench

import java.util.EnumSet
import java.util.HashSet
import java.util.LinkedList

class LinkedEnumSet<E : Enum<E>>(private val delegate: EnumSet<E>) : MutableSet<E> by delegate {

    private val links = LinkedList<E>()

    init {
        links.addAll(delegate)
    }

    companion object {
        inline fun <reified E : Enum<E>> of(vararg elements: E): LinkedEnumSet<E> {
            val set = LinkedEnumSet(EnumSet.noneOf(E::class.java))
            set.addAll(elements)
            return set
        }

        inline fun <reified E : Enum<E>> copyOf(elements: Collection<E>): LinkedEnumSet<E> {
            val set = LinkedEnumSet(EnumSet.noneOf(E::class.java))
            set.addAll(elements)
            return set
        }

        inline fun <reified E : Enum<E>> allOf(): LinkedEnumSet<E> {
            val set = LinkedEnumSet(EnumSet.allOf(E::class.java))
            set.addAll(set)
            return set
        }

        inline fun <reified E : Enum<E>> noneOf(): LinkedEnumSet<E> {
            val set = LinkedEnumSet(EnumSet.noneOf(E::class.java))
            set.addAll(set)
            return set
        }
    }

    override fun add(element: E): Boolean {
        if (delegate.add(element)) {
            links.add(element)
            return true
        }
        return false
    }

    override fun addAll(elements: Collection<E>): Boolean {
        if (delegate.addAll(elements)) {
            links.addAll(elements)
            return true
        }
        return false
    }

    override fun remove(element: E): Boolean {
        if (delegate.remove(element)) {
            links.remove(element)
            return true
        }
        return false
    }

    @Suppress("ConvertArgumentToSet")
    override fun removeAll(elements: Collection<E>): Boolean {
        if (delegate.removeAll(elements)) {
            links.removeAll(elements)
            return true
        }
        return false
    }

    @Suppress("ConvertArgumentToSet")
    override fun retainAll(elements: Collection<E>): Boolean {
        if (delegate.retainAll(elements)) {
            links.retainAll(elements)
            return true
        }
        return false
    }

    override fun iterator(): MutableIterator<E> = LinkedEnumSetIterator()

    private inner class LinkedEnumSetIterator : MutableIterator<E> {

        private val delegateIterator = links.iterator()
        private var last: E? = null

        override fun hasNext(): Boolean = delegateIterator.hasNext()

        override fun next(): E {
            last = delegateIterator.next()
            return last!!
        }

        override fun remove() {
            delegateIterator.remove()
            delegate.remove(last)
        }
    }
}