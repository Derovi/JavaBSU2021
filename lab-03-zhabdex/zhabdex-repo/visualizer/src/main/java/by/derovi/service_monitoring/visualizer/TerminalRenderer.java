package by.derovi.service_monitoring.visualizer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import de.vandermeer.asciitable.AsciiTable;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

/**
 * @author @dranikpg
 */
public class TerminalRenderer {

    public static TerminalRenderer init(int monitorCount) throws java.io.IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = defaultTerminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        return new TerminalRenderer(terminal, screen, monitorCount);
    }

    Terminal terminal;
    Screen screen;
    volatile int monitorCount;
    volatile int currentPage;
    volatile boolean closed = false;
    Thread inputThread;

    String windowHeader;

    private TerminalRenderer(Terminal terminal, Screen screen, int monitorCount) {
        this.terminal = terminal;
        this.screen = screen;
        this.monitorCount = monitorCount;
        this.currentPage = 0;
        this.inputThread = new InputThread();
        inputThread.start();
        makeWindowHeader();
    }

    private class InputThread extends Thread {
        InputThread() {
            setDaemon(true);
        }
        @Override
        public void run() {
            while (!closed) {
                KeyStroke stroke = null;
                try {
                    stroke = screen.readInput();
                } catch (IOException e) {
                    if (!closed) e.printStackTrace();
                }
                if (stroke == null) continue;
                if (stroke.getKeyType().equals(KeyType.ArrowLeft)) {
                    currentPage = (currentPage + monitorCount - 1) % monitorCount;
                } else {
                    currentPage =  (currentPage + 1) % monitorCount;
                }
            }
        }
    }

    private void makeWindowHeader() {
        this.windowHeader = "Connected to zhabdex as " + System.getProperty("user.name")
            + " from " + System.getProperty("os.name") + "(" + System.getProperty("os.arch") + ")";
    }

    private String filled(String in, int w) {
        if (in.length() >= w) return in;
        return in + new String(new char[w - in.length()]).replace('\0', ' ');
    }

    private String[] buildTable(Table table, int w) {
        AsciiTable t = new AsciiTable();
        for (List<String> row: table.getRows()) {
            t.addRule();
            t.addRow(row);
        }
        t.addRule();
        return t.renderAsArray(w);
    }

    public void render(List<Table> tables) throws IOException {
        screen.clear();
        screen.doResizeIfNecessary();

        TextGraphics g = screen.newTextGraphics();
        int r = 0;
        int w = g.getSize().getColumns();
        int h = g.getSize().getRows();
        screen.setCursorPosition(new TerminalPosition(w, h));

        assert tables.size() == monitorCount;
        Table current = tables.get(currentPage);

        g.setBackgroundColor(TextColor.ANSI.GREEN);
        String monitorString = "Monitor: " + current.getTitle() + " (" + (currentPage + 1) + "/" + monitorCount + ")";
        g.putString(0, r++, filled(monitorString, w), EnumSet.of(SGR.BOLD));

        g.setBackgroundColor(TextColor.ANSI.BLACK);
        String[] table = buildTable(current, w);
        for (String line: table) {
            g.putString(0, r++, line);
        }

        g.setBackgroundColor(TextColor.ANSI.GREEN);
        g.putString(0, h-1, filled(windowHeader, w), EnumSet.of(SGR.BOLD));

        screen.refresh();
    }

    public void close() throws IOException {
        closed = true;
        screen.stopScreen();
        terminal.close();
    }

}
