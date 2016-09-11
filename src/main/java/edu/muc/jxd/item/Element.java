package edu.muc.jxd.item;

/**
 * Created by gwd on 9/10/2016.
 */
public class Element<T> {

    protected T t;

    public Element(T t){
        this.t=t;
    }

    public Element(){}


    public boolean setElement(T t){
        this.t=t;
        return true;
    }

    public T getElement(){
        return t;
    }

    public int plastic(ElmentNumeralization<T> elmentNumeralization){
       return elmentNumeralization.numberalization(t);
    }

}
