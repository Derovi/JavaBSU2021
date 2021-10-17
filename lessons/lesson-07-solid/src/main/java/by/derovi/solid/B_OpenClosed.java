package by.derovi.solid;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Принцип откры́тости/закры́тости — принцип ООП, устанавливающий следующее положение:
 * «программные сущности должны быть открыты для расширения, но закрыты для изменения»;
 */

interface Event {}

@AllArgsConstructor
class ButtonClickEvent implements Event {
    enum ButtonType {
        LEFT, RIGHT
    }

    @Getter
    private final ButtonType buttonType;
}
class MouseDragEvent implements Event {}
class WindowClosedEvent implements Event {}
class ServerFailedException implements Event {}

class EventHandlers {
    static void buttonClicked(ButtonClickEvent event) {
        System.out.println(event.getButtonType().toString() + " button clicked!");
    }

    static void mouseDrag(MouseDragEvent event) {
        System.out.println("Mouse drag!");
    }

    static void windowClosed(WindowClosedEvent event) {
        System.out.println("Window closed!");
    }
}

public class B_OpenClosed {
    static class EventNotRegisteredException extends Exception {
        EventNotRegisteredException(Event event) {
            super("Handler for " + event.getClass().getSimpleName() + " not registered!");
        }
    }

    private static void callEvent(Event event) throws EventNotRegisteredException {
        if (event instanceof ButtonClickEvent e) {
            EventHandlers.buttonClicked(e);
        } else if (event instanceof MouseDragEvent e) {
            EventHandlers.mouseDrag(e);
        } else if (event instanceof WindowClosedEvent e) {
            EventHandlers.windowClosed(e);
        } else {
            throw new EventNotRegisteredException(event);
        }
    }

    private static void callEventFromSenior(Event event) throws EventNotRegisteredException {
        Method handler = Arrays.stream(EventHandlers.class.getDeclaredMethods())
            .filter(method -> method.getParameters().length > 0
                           && method.getParameters()[0].getType().isInstance(event))
            .findFirst()
            .orElseThrow(() -> new EventNotRegisteredException(event));

        try {
            handler.invoke(null, event);
        } catch (Exception ignored) {}
    }

    public static void main(String[] args) {
        List.of(
            new ButtonClickEvent(ButtonClickEvent.ButtonType.LEFT),
            new MouseDragEvent(),
            new ServerFailedException(),
            new WindowClosedEvent()
        ).forEach(event -> {
            try {
                callEventFromSenior(event);
            } catch (EventNotRegisteredException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
