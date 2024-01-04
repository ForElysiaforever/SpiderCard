package tools;

import entity.Game;

import java.io.*;

public class GameIO {
    public  void saveGame(Game game) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("game.dat"))) {
            outputStream.writeObject(game);
        }
    }

    public  Game loadGame() throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("game.dat"))) {
            return (Game) inputStream.readObject();
        }
    }
}
