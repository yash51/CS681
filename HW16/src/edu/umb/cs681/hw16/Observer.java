package edu.umb.cs681.hw16;
@FunctionalInterface
public interface Observer {
    void update(Observable o, Object obj);
}
