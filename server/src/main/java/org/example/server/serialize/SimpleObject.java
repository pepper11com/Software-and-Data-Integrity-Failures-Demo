package org.example.server.serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class SimpleObject implements Serializable {
    private String message;

    public SimpleObject(String message) {
        this.message = message;
    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        // implementation
        in.defaultReadObject();
        System.out.println("[SimpleObject] readObject called");
    }

    public String getMessage() {
        return message;
    }
}
