package nuricanozturk.dev.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static nuricanozturk.dev.PlanetTradeGame.PLAYER_COUNT;

public class LinkedList<T> implements Iterable<T>, Collection<T>
{
    private Node<T> m_head;
    private int m_size;
    private int m_currentIndex;
    private T currentItem;
    private int m_count;

    public LinkedList()
    {
        m_head = new Node<>(null);
        m_head.setNext(null);
        m_size = 0;
        m_currentIndex = 0;
    }

    public LinkedList(Collection<? extends T> collection)
    {
        m_head = new Node<>(null);
        m_head.setNext(null);
        m_size = 0;
        m_currentIndex = 0;

        collection.forEach(this::insertFirst);
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        c.forEach(this::insertFirst);
        return true;
    }

    public Node<T> getHead()
    {
        return m_head;
    }

    @Override
    public int size()
    {
        return m_size;
    }

    public boolean isEmpty()
    {
        return m_head == null;
    }

    public T Next()
    {
        return m_count++ % PLAYER_COUNT == 0 ? next() : currentItem;
    }

    public T next()
    {
        var p = m_head;

        T item = null;

        for (int i = 0; i <= m_currentIndex; item = p.getData(), p = p.getNext(), i++) ;

        m_currentIndex = m_currentIndex == m_size - 1 ? 0 : m_currentIndex + 1;

        currentItem = item;

        return item;
    }

    public void insertFirst(T data)
    {
        Node<T> node = new Node<>(data);

        if (isEmpty())
            m_head = node;
        else
        {
            Node<T> p = m_head;
            m_head = node;
            m_head.setNext(p);
        }

        m_size++;
    }

    public void insertLast(T data)
    {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET...");
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<>()
        {
            final Node<T> emptyNode = new Node<>(m_head.getData());
            Node<T> p = emptyNode;

            //non static initializer
            {
                emptyNode.setNext(m_head);
            }


            @Override
            public boolean hasNext()
            {
                return p.getNext().getNext() != null;
            }

            @Override
            public T next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("");

                p = p.getNext();

                return p.getData();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action)
    {
        for (T t : this)
            action.accept(t);
    }


//--------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean contains(Object o)
    {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }

    @Override
    public Object[] toArray()
    {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }

    @Override
    public <T1> T1[] toArray(T1[] a)
    {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }

    @Override
    public boolean add(T t)
    {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }

    @Override
    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }


    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET...");
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET...");
    }
}
