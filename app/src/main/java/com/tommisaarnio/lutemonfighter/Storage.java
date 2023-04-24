package com.tommisaarnio.lutemonfighter;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    ArrayList<Lutemon> lutemons = new ArrayList<>();

    public Storage() {
    }

    public static Storage storage = null;


    public static Storage getInstance() {
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }

    public void saveLutemon(Context context) {
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lut.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();
        }   catch (IOException e){
            System.out.println("Ei onnistu");
        }
    }

    public void loadLutemon(Context context){
        try {
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput("lut.data"));
            lutemons = (ArrayList<Lutemon>) userReader.readObject();
            userReader.close();
        }   catch (IOException e){
            System.out.println("Ei onnistu");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}