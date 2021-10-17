package by.derovi.solid;

/**
 * Принцип разделения интерфейсов говорит о том, что слишком «толстые» интерфейсы необходимо разделять на более
 * маленькие и специфические, чтобы программные сущности маленьких интерфейсов знали только о методах,
 * которые необходимы им в работе. В итоге, при изменении метода интерфейса не должны меняться программные сущности,
 * которые этот метод не используют.
 */

interface ReadableResource {
    String read();
}

interface WritableResource {
    void write(String str);
}

class ReadOnlyFile implements ReadableResource {
    @Override
    public String read() {
        System.out.println("Reading!");
        return null;
    }
}

class WriteOnlyFile implements WritableResource {

    @Override
    public void write(String str) {
        System.out.println("Writing!");
    }
}


public class D_InterfaceSegregation {
    public static void main(String[] args) throws Exception {
        WritableResource file = new WriteOnlyFile();
//        System.out.println(file.read());
    }
}
